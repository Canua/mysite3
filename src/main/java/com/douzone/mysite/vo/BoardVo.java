package com.douzone.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String contents;
	private String writeDate;
	private long hit;
	private long g_no;
	private long o_no;
	private long depth;
	private long user_no;
	private String name;
	private int pageNum;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public long getG_no() {
		return g_no;
	}

	public void setG_no(long g_no) {
		this.g_no = g_no;
	}

	public long getO_no() {
		return o_no;
	}

	public void setO_no(long o_no) {
		this.o_no = o_no;
	}

	public long getDepth() {
		return depth;
	}

	public void setDepth(long depth) {
		this.depth = depth;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", writeDate=" + writeDate
				+ ", hit=" + hit + ", g_no=" + g_no + ", o_no=" + o_no + ", depth=" + depth + ", user_no=" + user_no
				+ ", name=" + name + ", pageNum=" + pageNum + "]";
	}

}
