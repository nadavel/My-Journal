package application;

import java.sql.Date;

public class Post {
	private String type;
	private Date date;
	private String text;
	
	public Post(String type, Date date, String text) {
		this.type = type;
		this.date = date;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public Date getDate() {
		return date;
	}

	public String getText() {
		return text;
	}
}
