package com.Smileyes.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

//import com.Smileyes.test.dao.TestDao;
import com.Smileyes.test.core.TestBaseDao;
import com.Smileyes.test.entity.Person;
import com.Smileyes.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource(name = "testDao")
	private TestBaseDao testDao;

	public void add(Person person) {
		this.testDao.add(person);
		// int i = 1 / 0;
	}

	public void delete(String id) {
		this.testDao.delete(id);
	}

	public void update(Person person) {
		this.testDao.update(person);
	}

	public Person find(String id) {

		return this.testDao.findById(id);
	}

}
