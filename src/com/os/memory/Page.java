package com.os.memory;

public class Page {
	private int nextId = 0;
	private int id;
	private int status; // 0未使用 1已使用
	final int size=2048;

	public Page() {
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
	public long getSize() {
		return size;
	}
	@Override
	public String toString() {
		return "Page [id=" + id + ", size=" + size + "]";
	}


}
