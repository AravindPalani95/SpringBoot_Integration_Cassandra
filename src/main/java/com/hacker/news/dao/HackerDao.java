package com.hacker.news.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.hacker.news.entity.Story;

@Repository
public interface HackerDao extends CassandraRepository<Story, Integer>{

}
