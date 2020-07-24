package com.hacker.news.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBConfigurationTest {
	
	@Value("${spring.cassandra.secure-connect-bundle}")
	private String secureConnectBundlePath;
	
	@Value("${spring.cassandra.username}")
	private String username;
	
	@Value("${spring.cassandra.password}")
	private String password;
	
	@Value("${spring.cassandra.keyspace}")
	private String keyspaceName;

	@Test
	public void testCassandraConfigs(){
		Assert.assertNotEquals("secure bundle path cannot be empty","", secureConnectBundlePath);
		Assert.assertNotEquals("cassandra username cannot be empty", username);
		Assert.assertNotEquals("cassandra password cannot be empty", password);
		Assert.assertNotEquals("cassandra keyspace cannot be empty", keyspaceName);
	}
}
