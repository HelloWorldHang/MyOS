package com.os.bean;

public class PageTab {
	private int pageId;
	private int pageFrameId;
	public PageTab(int pageId, int pageFrameId) {
		super();
		this.pageId = pageId;
		this.pageFrameId = pageFrameId;
	}
	@Override
	public String toString() {
		return "PageTab [pageId=" + pageId + ", pageFrameId=" + pageFrameId + "]";
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getPageFrameId() {
		return pageFrameId;
	}
	public void setPageFrameId(int pageFrameId) {
		this.pageFrameId = pageFrameId;
	}
	

}
