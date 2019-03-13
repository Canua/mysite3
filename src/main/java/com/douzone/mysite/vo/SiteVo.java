package com.douzone.mysite.vo;

public class SiteVo {
	private String title;
	private String welcome;
	private String urlprofile;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getUrlprofile() {
		return urlprofile;
	}
	public void setUrlprofile(String urlprofile) {
		this.urlprofile = urlprofile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SiteVo [title=" + title + ", welcome=" + welcome + ", urlprofile=" + urlprofile + ", description="
				+ description + "]";
	}
	
	
}
