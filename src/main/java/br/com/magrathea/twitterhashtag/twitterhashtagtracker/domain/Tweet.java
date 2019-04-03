package br.com.magrathea.twitterhashtag.twitterhashtagtracker.domain;

import java.util.Date;
import java.util.List;

/**
 * Tweets collected by us  
 * @author kico
 */
public class Tweet {

	private String _id;
	private Date createdAt;
	private long statusId;
	private Date publishDate;
	private String author;
	private String message;
	private List<String> hashtagsFound;
	
	public Tweet() {
		super();
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

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getHashtagsFound() {
		return hashtagsFound;
	}

	public void setHashtagsFound(List<String> hashtagsFound) {
		this.hashtagsFound = hashtagsFound;
	}
}
