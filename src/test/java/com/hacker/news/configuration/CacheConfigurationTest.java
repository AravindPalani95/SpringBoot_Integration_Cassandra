package com.hacker.news.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import com.hacker.news.constants.CommonConstants;

@SpringBootTest
public class CacheConfigurationTest {

	@Autowired
	private CacheManager cacheManager;
	
	@Test
	public void testCacheManager(){
		Assert.isTrue(cacheManager.getCacheNames().contains(CommonConstants.CACHE_STORIES),
				"CacheManager does not containt cache stories");
	}
	
}
