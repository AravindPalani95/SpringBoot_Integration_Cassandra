package com.hacker.news.controller;

import io.swagger.annotations.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.news.constants.CommonConstants;
import com.hacker.news.entity.Comment;
import com.hacker.news.entity.Story;
import com.hacker.news.service.HackerService;

@Api(tags = CommonConstants.SWAGGER_TAG_HACKER)
@RestController
public class HackerController implements IHackerController{
	
	@Autowired
	private HackerService hackerService;

	public ResponseEntity<List<Story>> getBestStory() {
		List<Story> storyList = null;
		ResponseEntity<List<Story>> responseEntity = null;
		try{
			storyList = hackerService.getBestStory();
			responseEntity = new ResponseEntity<List<Story>>(storyList,HttpStatus.OK);
		}
		catch(Exception e){
			responseEntity = new ResponseEntity<List<Story>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<List<Story>> getPastStory() {
		List<Story> pastStoryList = null;
		ResponseEntity<List<Story>> responseEntity = null;
		try{
			pastStoryList = hackerService.getPastStories();
			responseEntity = new ResponseEntity<List<Story>>(pastStoryList,HttpStatus.OK);
		}
		catch(Exception e){
			responseEntity = new ResponseEntity<List<Story>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<List<Comment>> getComments(int storyId) {
		List<Comment> commentList = null;
		ResponseEntity<List<Comment>> responseEntity = null;
		try{
			commentList = hackerService.getComments(storyId);
			responseEntity = new ResponseEntity<List<Comment>>(commentList,HttpStatus.OK);
		}
		catch(Exception e){
			responseEntity = new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
