
package com.Smileyes.core.entity;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Description: 分页对象类
 * @author Smileyes
 * 
 */
public class PageResult {
	// 总页数
	private long totalPage;
	// 总记录数
	private long totalNum;
	// 当前页数
	private int pageNum;
	// 当前页记录集合
	private List items;
	// 每页条数
	private int pageSize;

	public PageResult() {
	}

	/*
	 * 通过跳转页数新建分页对象
	 */
	public PageResult(int pageNum) {
		this.pageNum = pageNum;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
