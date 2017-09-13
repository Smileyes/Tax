package com.Smileyes.core.service.impl;

import java.io.Serializable;
import java.util.List;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.service.BaseService;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.entity.Info;

/*
 * 基本服务类的抽象实现类
 * 
 * @author Smileyes
 *s
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 添加
	public void add(T entity) {
		baseDao.add(entity);
	}

	// 删除
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	// 修改
	public void update(T entity) {
		baseDao.update(entity);
	}

	// 通过主键id查询
	public T findById(Serializable id) {
		return (T) baseDao.findById(id);
	}

	// 查询所有
	public List<T> list() {
		return baseDao.list();
	}

	// 分页显示
	/*
	 * 分页查询
	 */
	public PageResult getPageResult(QueryHelper queryHelper, PageResult pageResult) {
		// 总记录
		long totalNum = baseDao.queryNums(queryHelper);
		pageResult.setTotalNum(totalNum);
		// 每页数量
		int pageSize = pageResult.getPageSize();
		// 当前页数
		int pageNum = pageResult.getPageNum();
		// 总页数:初始为0
		long totoalPage = 0;
		// 若总记录数不为0
		if (totalNum != 0) {
			// 能整除为商，不能整除则商+1
			totoalPage = (totalNum % pageSize == 0) ? (totalNum / pageSize)
					: (totalNum / pageSize + 1);
		}
		pageResult.setTotalPage(totoalPage);
		// 查询结果
		pageResult.setItems(this.baseDao.queryByCondition(queryHelper, pageResult));
		return pageResult;
	}

	// 条件查询所有
	public List<T> queryByCondition(QueryHelper queryHelper) {
		// 查询结果
		return this.baseDao.queryByCondition(queryHelper, null);
	}
}
