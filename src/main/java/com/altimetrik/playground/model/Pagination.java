package com.altimetrik.playground.model;

public class Pagination {
//	page=1, pages=1, per_page=50, total=1

	private int page;
	private int pages;
	private int per_pages;
	private int total;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPer_pages() {
		return per_pages;
	}
	public void setPer_pages(int per_pages) {
		this.per_pages = per_pages;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
