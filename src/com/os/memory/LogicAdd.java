package com.os.memory;

public class LogicAdd {
	private  int id;
	private int pagedisplace;
	public LogicAdd() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPagedisplace() {
		return pagedisplace;
	}
	public void setPagedisplace(int pagedisplace) {
		this.pagedisplace = pagedisplace;
	}
	@Override
	public String toString() {
		return "LogicAdd [id=" + id + ", pagedisplace=" + pagedisplace + "]";
	}
	

}
