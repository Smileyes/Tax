package com.Smileyes.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.utils.QueryHelper;

/*
 * BaseDao的实现类
 * 
 * @author Smileyes
 *
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private SessionFactory sessionFactory;
	private Class clazz;
	private String tableName;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		tableName = clazz.getSimpleName();
	}

	// 添加
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	// 删除
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	// 修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	// 查询单个
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	// 查询所有
	public List<T> list() {
		return this.getSession().createQuery("FROM " + tableName).list();
	}

	// 获得session
	public Session getSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	// 分页查询
	public List<T> queryByCondition(QueryHelper queryHelper, PageResult pageResult) {
		String hql = queryHelper.getHql();
		Query query = this.getSession().createQuery(hql);
		List<Object> list = queryHelper.getParameters();
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		if (pageResult != null) {
			// 当前页数
			int pageNum = pageResult.getPageNum();
			// 每页数量
			int size = pageResult.getPageSize();
			// 设置查询起点
			query.setFirstResult(size * (pageNum - 1));
			// 设置查询大小
			query.setMaxResults(size);
		}
		return query.list();
	}

	// 查询所有
	public List<T> queryByCondition(QueryHelper queryHelper) {
		String hql = queryHelper.getHql();
		Query query = this.getSession().createQuery(hql);
		List<Object> list = queryHelper.getParameters();
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		return query.list();
	}

	// 查询记录数量
	public long queryNums(QueryHelper queryHelper) {
		Query query = this.getSession().createQuery(queryHelper.getCountHql());
		List<Object> list = queryHelper.getParameters();
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		return (Long) query.list().get(0);
	}
}
