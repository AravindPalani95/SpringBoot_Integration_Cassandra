package com.hacker.news.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hacker.news.dao.HackerDao;
import com.hacker.news.entity.Story;

@RunWith(SpringRunner.class)
public class HackerServiceTest {
	
	@MockBean
	private HackerDao hackerDao;
	
	@SuppressWarnings("deprecation")
	public void getBestStory(){
		Story mockStory = new Story();
		mockStory.setBy("mgav");
		mockStory.setId(23929044);
		mockStory.setScore(966);
		mockStory.setTime(1595521578);
		mockStory.setTitle("Amazon met with startups about investing, then launched competing products");
		mockStory.setUrl("https://www.wsj.com/articles/amazon-tech-startup-echo-bezos-alexa-investment-fund-11595520249");
		
		List<Story> mockStoryList = new ArrayList<Story>();
		mockStoryList.add(mockStory);
		
		Mockito.when(hackerDao.saveAll(Mockito.anyIterableOf(Story.class))).thenReturn(mockStoryList);
		List<Story> storyList = hackerDao.saveAll(mockStoryList);
		
		Assert.assertArrayEquals(storyList.toArray(), mockStoryList.toArray());
		
		
	}
	
	public void getPastStories(){
		Story mockStory = new Story();
		mockStory.setBy("mgav");
		mockStory.setId(23929044);
		mockStory.setScore(966);
		mockStory.setTime(1595521578);
		mockStory.setTitle("Amazon met with startups about investing, then launched competing products");
		mockStory.setUrl("https://www.wsj.com/articles/amazon-tech-startup-echo-bezos-alexa-investment-fund-11595520249");
		
		List<Story> mockStoryList = new ArrayList<Story>();
		mockStoryList.add(mockStory);
		
		Mockito.when(hackerDao.findAll()).thenReturn(mockStoryList);
		List<Story> storyList = hackerDao.findAll();
		
		Assert.assertArrayEquals(storyList.toArray(), mockStoryList.toArray());
		
	}
	
	@Test
	public  void getUserProfileYear(){
		LocalDate profileDate = LocalDate.now().minusYears(2);
		int profileCreated = profileDate.getYear();
		int currentYear = LocalDate.now().getYear();
		Assert.assertTrue("Profile created data cannot be less than zero",(currentYear-profileCreated) > 0);
	}
	
}
