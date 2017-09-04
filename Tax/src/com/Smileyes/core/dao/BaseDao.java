package com.Smileyes.core.dao;

import java.io.Serializable;
import java.util.List;

/*
 * 核心Dao接口，包含通用的数据的CRUD方法
 * 
 * @author Smileyes
 *
 */
public interface BaseDao<T> {
	// 增加
	public void add(T t);

	// 删除
	public void delete(Serializable id);

	// 修改
	public void update(T t);

	// 查询单个
	public T findById(Serializable id);

	// 查询所有
	public List<T> list();
}
