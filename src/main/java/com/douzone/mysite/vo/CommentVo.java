package com.douzone.mysite.vo;

public class CommentVo {
	private long no;
	private String comment;
	private String writeDate;
	private long boardNo;
	private long userNo;
	private String name;
	private String password;
	private int g_no;
	private int o_no;
	private int depth;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getG_no() {
		return g_no;
	}

	public void setG_no(int g_no) {
		this.g_no = g_no;
	}

	public int getO_no() {
		return o_no;
	}

	public void setO_no(int o_no) {
		this.o_no = o_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", comment=" + comment + ", writeDate=" + writeDate + ", boardNo=" + boardNo
				+ ", userNo=" + userNo + ", name=" + name + ", password=" + password + ", g_no=" + g_no + ", o_no="
				+ o_no + ", depth=" + depth + "]";
	}

	
}
