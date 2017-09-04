package com.Smileyes.test.service;

import com.Smileyes.test.entity.Person;

public interface TestService {
	void add(Person person);

	void delete(String id);

	void update(Person person);

	Person find(String id);
}
