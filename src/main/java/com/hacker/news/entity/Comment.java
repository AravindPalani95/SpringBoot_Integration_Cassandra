package com.hacker.news.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.hacker.news.util.json.CustomJsonViews;

public class Comment implements Serializable{

	private static final long serialVersionUID = 2784073288597037740L;
	
	private String text;
	
	private String by;
	
	private int id;
	
	private List<Integer> kids;
	
	private String parent;
	
	private int time;
	
	private String type;
	
	private int profileYears;
	
	@JsonView(CustomJsonViews.View.class)
	@JsonProperty("comment")
	public String getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public List<Integer> getKids() {
		return kids;
	}

	public void setKids(List<Integer> kids) {
		this.kids = kids;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonView(CustomJsonViews.View.class)
	public int getProfileYears() {
		return profileYears;
	}

	public void setProfileYears(int profileYears) {
		this.profileYears = profileYears;
	}

}
