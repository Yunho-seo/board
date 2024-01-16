package model;

import java.sql.Timestamp;

public class BoardVO {
	private int num;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private int count;
	public BoardVO() { }
	
	public BoardVO(String title, String content, String writer, Timestamp regdate, int count) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.count = count;
	}
	
	public BoardVO(int num, String title, String content, String writer, Timestamp regdate, int count) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.count = count;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", count=" + count + "]";
	}
}
