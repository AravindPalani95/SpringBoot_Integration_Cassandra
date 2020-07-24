package com.hacker.news.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	private static final long serialVersionUID = -4319404731291090996L;
	
	private String about;
	
	private int created;
	
	private int delay;
	
	private String id;
	
	private int karma;
	
	private List<Integer> submitted;

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

	public List<Integer> getSubmitted() {
		return submitted;
	}

	public void setSubmitted(List<Integer> submitted) {
		this.submitted = submitted;
	}

}
