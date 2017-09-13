package com.Smileyes.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: QueryHelper
 * @Description: 用于处理条件查询
 * @author Smileyes
 *
 */
public class QueryHelper {
	// 查询表来源
	private String fromCaluse = "";
	// 查询条件
	private String whereCaluse = "";
	// 查询参数集合
	private List<Object> parameters = new ArrayList<Object>();
	// 排序语句
	private String orderCaluse = "";
	// 排序条件:正序
	public final static String ORDER_ASC = " ASC ";
	// 排序条件:倒序
	public final static String ORDER_DSEC = " DESC ";
	// 多条件与
	public final static String CONDITIONS_AND = " AND ";
	// 多条件或
	public final static String CONDITIONS_OR = " OR ";
	// 单条件
	public final static String CONDITIONS_NULL = "";

	public QueryHelper(Class clazz) {
		fromCaluse += "FROM " + clazz.getSimpleName();
	}

	/**
	 * 
	 * @Title: addCondition
	 * @Description: TODO(添加条件)
	 * @param item
	 *            条件,例如：name like ？
	 * @param parameter
	 *            条件的值, 例如：%+JACK+%;
	 * @param andOr
	 */
	public QueryHelper addCondition(String item, Object parameter, String andOr) {
		// 不是第一个条件
		if (whereCaluse.length() > 1) {
			whereCaluse += andOr + item;
			// 第一个条件
		} else {
			whereCaluse += " WHERE " + item;
		}
		parameters.add(parameter);
		return this;
	}

	/**
	 * 
	 * @Title: addOrderByProperty
	 * @Description: TODO(排序)
	 * @param property排序条件剑
	 * @param order排序方式，如正序AESC
	 */
	public QueryHelper addOrderByProperty(String property, String order) {
		// 不是第一个条件
		if (orderCaluse.length() > 1) {
			orderCaluse += "," + property + " " + order;
			// 第一个条件
		} else {
			orderCaluse += " ORDER BY " + property + " " + order;
		}
		return this;
	}

	/*
	 * 获得语句
	 */
	public String getHql() {
		return fromCaluse + whereCaluse + orderCaluse;
	}

	/*
	 * 获得条件查询的参数
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	public String getCountHql() {
		return "SELECT COUNT(* ) " + fromCaluse + whereCaluse;
	}
}
