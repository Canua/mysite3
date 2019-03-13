package com.douzone.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String contents;
	private String writeDate;
	private Long hit;
	private Integer g_no;
	private Integer o_no;
	private Long depth;
	private Long user_no;
	private String name;
	private Integer pageNum;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
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

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public Integer getG_no() {
		return g_no;
	}

	public void setG_no(Integer g_no) {
		this.g_no = g_no;
	}

	public Integer getO_no() {
		return o_no;
	}

	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}

	public Long getDepth() {
		return depth;
	}

	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public Long getUser_no() {
		return user_no;
	}

	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", writeDate=" + writeDate
				+ ", hit=" + hit + ", g_no=" + g_no + ", o_no=" + o_no + ", depth=" + depth + ", user_no=" + user_no
				+ ", name=" + name + ", pageNum=" + pageNum + ", userName=" + userName + "]";
	}

}
