package com.hacker.news.task;

import java.lang.invoke.MethodHandles;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hacker.news.service.CommonService;

@Component
public class StoryTask implements Runnable{
	
	public static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private CommonService commonService;

	@Override
	public void run() {
		log.info("Story Task Called : " , LocalTime.now());
		commonService.evictStories();
	}

}
