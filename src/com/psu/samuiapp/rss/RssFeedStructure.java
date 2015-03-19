package com.psu.samuiapp.rss;

import java.net.URL;

import android.R.string;

public class RssFeedStructure {

	private long articleId;
	private long feedId;
	private String title;
	private String description;
	private String imgLink;
	private String pubDate;
	private URL url;
	private String encodedContent;
	
	private String link;
	private  String category;
	private String author;


	public String getDescription() {
		return description;
	}

	public void setDescription(String describe) {
		this.description = describe;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getFeedId() {
		return feedId;
	}

	/**
	 * @param feedId
	 *            the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setImage(String description) {
		this.description = description;

		if (description.contains("<img ")) {
			String img = description.substring(description.indexOf("<img "));
			String cleanUp = img.substring(0, img.indexOf(">") + 1);
			img = img.substring(img.indexOf("src=") + 5);
			int indexOf = img.indexOf("'");
			if (indexOf == -1) {
				indexOf = img.indexOf("\"");
			}
			img = img.substring(0, indexOf);

			// setImgLink(img);

			this.description = this.description.replace(cleanUp, "");
		}
	}

	/**
	 * @return the description
	 */
	public String getImage() {
		return description;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * @param encodedContent
	 *            the encodedContent to set
	 */
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}

	/**
	 * @return the encodedContent
	 */
	public String getEncodedContent() {
		return encodedContent;
	}

	/**
	 * @param imgLink
	 *            the imgLink to set
	 */
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	/**
	 * @return the imgLink
	 */
	public String getImgLink() {
		return imgLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	

}
