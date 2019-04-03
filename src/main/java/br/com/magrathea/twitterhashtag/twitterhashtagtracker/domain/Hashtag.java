package br.com.magrathea.twitterhashtag.twitterhashtagtracker.domain;

import java.util.Date;

/**
 * Hashtags that we will track
 * @author kico
 */
public class Hashtag {
	
	public Hashtag() {
		super();
	}

	public Hashtag(String hashtag) {
		super();
		this.hashtag = hashtag;
	}

	private String hashtag;
	private String _id;
	private Date createdAt;
	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
