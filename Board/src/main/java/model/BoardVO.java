package model;

import java.sql.Timestamp;

public class BoardVO {
	private int num;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private int regcount;
	private String pw;
	public BoardVO() { }
	
	public BoardVO(String title, String content, String writer, Timestamp regdate, int regcount, String pw) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.regcount = regcount;
		this.pw = pw;
	}
	
	public BoardVO(int num, String title, String content, String writer, Timestamp regdate, int regcount, String pw) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.regcount = regcount;
		this.pw = pw;
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
	public int getRegcount() {
		return regcount;
	}
	public void setRegcount(int regcount) {
		this.regcount = regcount;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", regcount=" + regcount + ", pw=" + pw + "]";
	}
}