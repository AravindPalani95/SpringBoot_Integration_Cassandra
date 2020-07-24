package com.hacker.news.service;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.hacker.news.constants.CommonConstants;
import com.hacker.news.dao.HackerDao;
import com.hacker.news.entity.Comment;
import com.hacker.news.entity.Story;
import com.hacker.news.entity.User;
import com.hacker.news.task.StoryTask;

@Service
public class HackerService {
	
	public static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StoryTask storyTask;
	
	@Autowired
	private TaskScheduler taskScheduler;
	
	@Autowired
	private HackerDao hackerDao;
	
	@Value("${spring.hackernews.beststory.url}")
	private String bestStoryURL;
	
	@Value("${spring.hackernews.item.url}")
	private String itemURL;
	
	@Value("${spring.hackernews.user.url}")
	private String userURL;
	
	@Value("${spring.cache.time}")
	private int cacheTime;

	@Cacheable(value =  CommonConstants.CACHE_STORIES, key = "#root.methodName")
	public List<Story> getBestStory(){
		log.info("Inside best Story call");
		ResponseEntity<List<Integer>> bestStoryEntity = restTemplate.exchange(
					bestStoryURL , HttpMethod.GET, null, new ParameterizedTypeReference<List<Integer>>() {});
		List<Integer> bestStoryIdList = bestStoryEntity.getBody();
		bestStoryIdList = bestStoryIdList.stream().limit(10).collect(Collectors.toList());
		log.info("best 10 story :" + bestStoryIdList);
		List<Story> storyList = bestStoryIdList.stream().map( storyId ->{
							UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(itemURL)
									.buildAndExpand(storyId);
							ResponseEntity<Story> storyEntity = restTemplate.getForEntity(uriComponents.toUriString(),
									Story.class);
							return storyEntity.getBody();
							}).collect(Collectors.toList());
		hackerDao.saveAll(storyList);
		log.info("story saved in cassandra");
		this.scheduleStroyEviction();
		return storyList;
	}
	
	public List<Story> getPastStories(){
		log.info("past story method invoked");
		return hackerDao.findAll();
	}
	
	public List<Comment> getComments(int storyId){
		log.info("comment method invoked");
		Optional<Story> story = hackerDao.findById(storyId);
		List<Comment> commentList = new ArrayList<Comment>();;
		if(story.isPresent()){
			log.info("story with ", storyId ," found");
			List<Integer> kids = story.get().getKids();
			kids = kids.stream().limit(10).collect(Collectors.toList());
			log.info("Kids :" + kids);
			commentList = kids
					.stream()
					.map(id -> {
						try {
							UriComponents uriComponents = UriComponentsBuilder
									.fromHttpUrl(itemURL).buildAndExpand(id);
							ResponseEntity<Comment> commentEntity = restTemplate
									.getForEntity(uriComponents.toUriString(),
											Comment.class);
							Comment comment = commentEntity.getBody();
							UriComponents uriComponentsUser = UriComponentsBuilder
									.fromHttpUrl(userURL).buildAndExpand(
											comment.getBy());
							ResponseEntity<User> userEntity = restTemplate
									.getForEntity(
											uriComponentsUser.toUriString(),
											User.class);
							User user = userEntity.getBody();
							int profileYears = this.getUserProfileYear(user
									.getCreated());
							log.info("profile in year:"+ profileYears);
							comment.setProfileYears(profileYears);
							return comment;
						} catch (Exception e) {
							log.error(e.getMessage());
							return null;
						}
					}).collect(Collectors.toList());
		}
		return commentList;
	}
	
	public void scheduleStroyEviction(){
		log.info("storyTask scheduled");
		taskScheduler.schedule(storyTask, Date.from(
				LocalTime.now()
				.plusMinutes(cacheTime)
				.atDate(LocalDate.now())
				.atZone(ZoneId.systemDefault())
				.toInstant()));
	}
	
	public int getUserProfileYear(int created){
		LocalDate profileDate = Instant.ofEpochMilli(created).atZone(ZoneId.systemDefault()).toLocalDate();
		int profileCreated = profileDate.getYear();
		int currentYear = LocalDate.now().getYear();
		log.info("User Profile year :" + profileDate.toString() + " current year:"+ currentYear);
		return currentYear - profileCreated;
	}
	
}
