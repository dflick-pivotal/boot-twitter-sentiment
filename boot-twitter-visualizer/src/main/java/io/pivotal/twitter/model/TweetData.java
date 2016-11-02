package io.pivotal.twitter.model;

import javax.persistence.*;

import java.io.Serializable;
 
@Entity
@Table(name = "tweet_data")
public class TweetData implements Serializable{
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
	public String tweet;
	
	@Column(nullable = false)
	public String createdAt;
	
	@Column(nullable = false)
	public int sentiment;

	public TweetData() {
		super();
	}
	
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public int getSentiment() {
		return sentiment;
	}
	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}

}
