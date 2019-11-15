package com.os.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyProcess {
	/**
	 *进程类
	 */

	//静态变量实现id自增
	private static int next_id = 0;
	//进程id
	private int id;
	//页表
	private List<PageTab> pageTabList;
	//进程名字
	private String name;
	//请求存储空间，单位kB
	private int requiredMemorySize;
	//状态	0：终止	1：就绪	2：运行 3:等待
	private int status;
	//到达时间
	private int timeOfArrival;
	//优先级，值越大优先级越低
	private int priority;
	//等待时间（暂定）
	private int ax;
	//进程剩余运行时间
	private int times;
	// 标志是否在内存中
	private boolean flag;


	//构造代码块,给所有对象进行初始化
	{
		this.id = ++MyProcess.next_id;
		this.status = 1;
		this.requiredMemorySize = 1; // 初始化就绪态
		pageTabList = new ArrayList<>();
		this.flag = false; // 初始为装入内存
	}
	//构造函数
	public MyProcess(){

	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void addPageTab(PageTab pageTab){
		this.pageTabList.add( pageTab );
	}


	public static int getNext_id() {
		return next_id;
	}

	public int getId() {
		return id;
	}

	public List<PageTab> getPageTabList() {
		return pageTabList;
	}

	public String getName() {
		return name;
	}

	public int getRequiredMemorySize() {
		return requiredMemorySize;
	}

	public int getStatus() {
		return status;
	}


	public int getPriority() {
		return priority;
	}

	public int getAx() {
		return ax;
	}

	public int getTimes() {
		return times;
	}

	public static void setNext_id(int next_id) {
		MyProcess.next_id = next_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPageTabList(List<PageTab> pageTabList) {
		this.pageTabList = pageTabList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRequiredMemorySize(int requiredMemorySize) {
		this.requiredMemorySize = requiredMemorySize;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setAx(int ax) {
		this.ax = ax;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getTimeOfArrival() {
		return timeOfArrival;
	}

	public void setTimeOfArrival(int timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}

	@Override
	public String toString() {
		return "MyProcess{" +
				"id=" + id +
				", pageTabList=" + pageTabList +
				", name='" + name + '\'' +
				", requiredMemorySize=" + requiredMemorySize +
				", status=" + status +
				", timeOfArrival=" + timeOfArrival +
				", priority=" + priority +
				", ax=" + ax +
				", times=" + times +
				'}';
	}
}
