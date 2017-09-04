package com.Smileyes.test.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Smileyes.test.entity.Person;
import com.Smileyes.test.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class TestAction extends ActionSupport {
	@Resource
	private TestService testService;

	public String execute() throws Exception {
		System.out.println("连接");
		this.testService.add(new Person("小强"));
//		// this.testService.delete("4028f6815dfe198a015dfe1b04560001");
//		this.testService.update(new Person("4028f6815dfe198a015dfe1c3cd90003", "小明"));//
//		Person person = this.testService.find("4028f6815dfe198a015dfe1c3cd90003");
//		System.out.println(person.getName());
		return SUCCESS;
	}

}
