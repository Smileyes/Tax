package com.Smileyes.test.dao.impl;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.Smileyes.test.dao.TestDao;
import com.Smileyes.test.entity.Person;

//@Repository("testDao")
public class TestDaoImpl extends HibernateDaoSupport implements TestDao {
	// @Resource
	// private SessionFactory sessionFactory;

	public void add(Person person) {
		this.getHibernateTemplate().save(person);
		// int i = 1 / 0;
	}

	public void delete(String id) {
		this.getHibernateTemplate().delete(find(id));
	}

	public void update(Person person) {
		this.getHibernateTemplate().update(person);
	}

	public Person find(String id) {
		return this.getHibernateTemplate().get(Person.class, id);
	}

}
