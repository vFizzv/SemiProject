package com.myboot01.web.vo;

import java.util.Date;

public class ArticleVO {
	private int level;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private Date writeDate;
	private String id;
	
	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleVO(int level, int articleNO, int parentNO, String title, String content, Date writeDate, String id) {
		super();
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.id = id;
	}



	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ArticleVO [level=" + level + ", articleNO=" + articleNO + ", parentNO=" + parentNO + ", title=" + title
				+ ", content=" + content + ", writeDate=" + writeDate + ", id=" + id + "]";
	}
	
	

	
	
	

}
