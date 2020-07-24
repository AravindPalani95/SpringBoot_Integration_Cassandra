package com.hacker.news.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.hacker.news.constants.CommonConstants;

@Service
public class CommonService {
	
	public static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@CacheEvict(value = CommonConstants.CACHE_STORIES)
	public void evictStories() {
		log.info("Story Evicted");
	}

}
