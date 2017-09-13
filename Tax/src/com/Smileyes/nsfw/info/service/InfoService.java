package com.Smileyes.nsfw.info.service;

import java.io.Serializable;
import java.util.List;

import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.exception.ServiceException;
import com.Smileyes.core.service.BaseService;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.entity.Info;

/*
 * 用于处理信息的Service服务类
 * 
 * @author Smileyes
 *
 */
public interface InfoService extends BaseService<Info> {
	@Deprecated
	/**
	 * 
	 * @Title: findByTitle
	 * @Description: TODO(条件查询)
	 * @param title
	 * @return设定文件
	 * @return List<Info> 返回查询结果集合
	 */
	public List<Info> findByTitle(String title);

	/**
	 * @Title: findByConditions
	 * @Description: TODO(条件查询)
	 * @param qh
	 *            条件查询工具
	 * @return 返回查询的结果集合
	 */
	public List<Info> findByConditions(QueryHelper qh);

	/**
	 * 
	 * @Title: getPageResult
	 * @Description: TODO(分页查询)
	 * @param queryHelper
	 *            查询助手
	 * @param pr
	 *            查询的一页结果
	 * @return 查询的分页结果
	 */
	public PageResult getPageResult(QueryHelper queryHelper, PageResult pr);
}
