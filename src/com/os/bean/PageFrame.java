package com.os.bean;

public class PageFrame {
	private  int nextId;
	private int id;
	final int size = 2048;
	private int status;
	public PageFrame() {
		this.id = ++nextId;
		this.status = 0;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}

}
