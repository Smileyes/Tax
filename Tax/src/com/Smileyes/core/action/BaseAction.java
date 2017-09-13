package com.Smileyes.core.action;

import java.io.File;

import com.Smileyes.core.entity.PageResult;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 控制层的Action基类
 * 
 * @author Smileyes
 *
 */
public abstract class BaseAction extends ActionSupport {
	// 上传的文件
	protected File upload;
	protected String uploadContentType;
	protected String uploadFileName;
	// 复选项目
	protected String[] selectedRow;
	// 根据名称查询
	protected String search;
	// 分页查询结果
	protected PageResult pageResult;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	
}
