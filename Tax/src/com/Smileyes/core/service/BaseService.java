package com.Smileyes.core.service;

import java.io.Serializable;
import java.util.List;

import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.utils.QueryHelper;

/*
 * 基本服务类接口
 * 
 * @author Smileyes
 *
 */
public interface BaseService<T> {
	// 添加
	public void add(T entity);

	// 删除
	public void delete(Serializable id);

	// 修改
	public void update(T entity);

	// 通过主键id查询
	public T findById(Serializable id);

	// 查询所有
	public List<T> list();

	// 分页显示
	public PageResult getPageResult(QueryHelper queryHelper, PageResult pageResult);

	// 条件查询显示所有
	public List<T> queryByCondition(QueryHelper queryHelper);
}
