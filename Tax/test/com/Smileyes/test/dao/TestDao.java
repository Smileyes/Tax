package com.Smileyes.test.dao;

import com.Smileyes.test.entity.Person;

public interface TestDao {
	void add(Person person);

	void delete(String id);

	void update(Person person);

	Person find(String id);
}
