package com.hacker.news.configuration;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class DBConfiguration {
	
	@Value("${spring.cassandra.secure-connect-bundle}")
	private String secureConnectBundlePath;
	
	@Value("${spring.cassandra.username}")
	private String username;
	
	@Value("${spring.cassandra.password}")
	private String password;
	
	@Value("${spring.cassandra.keyspace}")
	private String keyspaceName;

	@Bean
	public CqlSession cqlSession() throws IOException{
		final CqlSession cqlSession = CqlSession.builder()
				.withCloudSecureConnectBundle(Paths.get(new ClassPathResource(secureConnectBundlePath).getURI()))
				.withAuthCredentials(username, password)
				.withKeyspace(keyspaceName)
				.build();
		return cqlSession;
	}
	
	@Bean
	public CassandraTemplate cassandraTemplate() throws IOException{
		return new CassandraTemplate(cqlSession());
	}
	
}
