package com.hacker.news.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.hacker.news.constants.HackerPathConstants;
import com.hacker.news.entity.Comment;
import com.hacker.news.entity.Story;
import com.hacker.news.util.json.CustomJsonViews;

@RequestMapping(value = HackerPathConstants.PATH_HACKER)
public interface IHackerController {
	
	@RequestMapping(value = HackerPathConstants.PATH_BEST_STORY, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(CustomJsonViews.View.class)
	public ResponseEntity<List<Story>> getBestStory();
	
	@RequestMapping(value = HackerPathConstants.PATH_PAST_STORY, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(CustomJsonViews.View.class)
	public ResponseEntity<List<Story>> getPastStory();
	
	@RequestMapping(value = HackerPathConstants.PATH_COMMENTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(CustomJsonViews.View.class)
	public ResponseEntity<List<Comment>> getComments(@PathVariable(HackerPathConstants.VAR_STORY_ID) int storyId);

}
