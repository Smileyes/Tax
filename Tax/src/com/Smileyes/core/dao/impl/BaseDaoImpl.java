package com.Smileyes.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.Smileyes.core.dao.BaseDao;

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
		return this.getSessionFactory().getCurrentSession()
				.createQuery("FROM " + tableName).list();
	}
}
