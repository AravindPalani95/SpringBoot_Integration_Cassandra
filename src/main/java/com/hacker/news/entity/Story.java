package com.hacker.news.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.hacker.news.util.json.CustomJsonViews;

@Table
@ApiModel
public class Story implements Serializable{
	
	private static final long serialVersionUID = 4157542923917868750L;

	@PrimaryKey
	@Column
	@ApiModelProperty
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String url;
	
	@Column
	private int score;
	
	@Column
	private int time;
	
	@Column(value = "name")
	private String by;
	
	@Column
	private int descendants;
	
	@Column
	@CassandraType(type = Name.LIST, typeArguments = Name.INT)
	private List<Integer> kids;

	@Column
	private String type;

	@JsonView(CustomJsonViews.View.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonView(CustomJsonViews.View.class)
	@JsonProperty("name")
	public String getBy() {
		return by;
	}
	
	@JsonProperty("by")
	public void setBy(String by) {
		this.by = by;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public int getDescendants() {
		return descendants;
	}

	public void setDescendants(int descendants) {
		this.descendants = descendants;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public List<Integer> getKids() {
		return kids;
	}

	public void setKids(List<Integer> kids) {
		this.kids = kids;
	}

	@JsonView(CustomJsonViews.Internal.class)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonView(CustomJsonViews.View.class)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonView(CustomJsonViews.View.class)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonView(CustomJsonViews.View.class)
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@JsonView(CustomJsonViews.View.class)
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public String toString(){
		return "[ storyId = "+ this.id + ", title = "+ this.title + ", url = "+ this.url+", score = "+ 
				this.score + ", time = "+ this.time + ", user = "+ this.by +" ]";
	}

}
