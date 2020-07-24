package com.hacker.news.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hacker.news.entity.Comment;
import com.hacker.news.entity.Story;
import com.hacker.news.service.HackerService;

@RunWith(SpringRunner.class)
@WebMvcTest(HackerController.class)
public class HackerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HackerService hackerService;
	
	@Test
	public void getBestStory() throws Exception {
		Story mockStory = new Story();
		mockStory.setBy("mgav");
		mockStory.setId(23929044);
		mockStory.setScore(966);
		mockStory.setTime(1595521578);
		mockStory.setTitle("Amazon met with startups about investing, then launched competing products");
		mockStory.setUrl("https://www.wsj.com/articles/amazon-tech-startup-echo-bezos-alexa-investment-fund-11595520249");
		
		List<Story> mockStoryList = new ArrayList<Story>();
		mockStoryList.add(mockStory);
		
		JSONObject mockJson = new JSONObject();
		mockJson.put("name", "mgav");
		mockJson.put("id", 23929044);
		mockJson.put("score", 966);
		mockJson.put("time",1595521578);
		mockJson.put("title", "Amazon met with startups about investing, then launched competing products");
		mockJson.put("url", "https://www.wsj.com/articles/amazon-tech-startup-echo-bezos-alexa-investment-fund-11595520249");
		JSONArray mockJsonArr = new JSONArray();
		mockJsonArr.put(mockJson);
		String mockResult = mockJsonArr.toString();
		
		Mockito.when(hackerService.getBestStory()).thenReturn(mockStoryList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/hacker/best-stories").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(mockResult, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void getPastStory() throws Exception {
		Story mockPastStory = new Story();
		mockPastStory.setBy("mgav");
		mockPastStory.setId(23929044);
		mockPastStory.setScore(966);
		mockPastStory.setTime(1595521578);
		mockPastStory.setTitle("Amazon met with startups about investing, then launched competing products");
		mockPastStory.setUrl("https://www.wsj.com/articles/amazon-tech-startup-echo-bezos-alexa-investment-fund-11595520249");
		
		List<Story> mockPastStoryList = new ArrayList<Story>();
		mockPastStoryList.add(mockPastStory);
		
		JSONObject mockJson = new JSONObject();
		mockJson.put("name", "mgav");
		mockJson.put("id", 23929044);
		mockJson.put("score", 966);
		mockJson.put("time",1595521578);
		mockJson.put("title", "Amazon met with startups about investing, then launched competing products");
		mockJson.put("url", "https://www.wsj.com/articles/amazon-tech-startup-echo-bezos-alexa-investment-fund-11595520249");
		JSONArray mockJsonArr = new JSONArray();
		mockJsonArr.put(mockJson);
		String mockResult = mockJsonArr.toString();
		
		Mockito.when(hackerService.getPastStories()).thenReturn(mockPastStoryList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/hacker/past-stories").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(mockResult, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void getComment() throws Exception {
		
		Comment mockComment = new Comment();
		mockComment.setText("Hacker news is awesome");
		mockComment.setProfileYears(40);
		
		List<Comment> mockCommentList = new ArrayList<Comment>();
		mockCommentList.add(mockComment);
		
		JSONObject mockJson = new JSONObject();
		mockJson.put("comment", "Hacker news is awesome");
		mockJson.put("profileYears", 40);
		JSONArray mockJsonArr = new JSONArray();
		mockJsonArr.put(mockJson);
		String mockResult = mockJsonArr.toString();
		
		Mockito.when(hackerService.getComments(Mockito.anyInt())).thenReturn(mockCommentList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/hacker/comments/235467").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(mockResult, result.getResponse().getContentAsString(), false);
		
	}

}
