package com.Smileyes.nsfw.info.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.Smileyes.core.dao.impl.BaseDaoImpl;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.dao.InfoDao;
import com.Smileyes.nsfw.info.entity.Info;

/*
 * 处理角色操作的DAO
 * 
 * @author Smileyes
 *
 */

public class InfoDaoImpl extends BaseDaoImpl<Info> implements InfoDao {
	@Deprecated
	// 通过标题模糊查询
	public List<Info> findByTitle(String title) {
		Query query = getSession()
				.createQuery("FROM Info WHERE title like ? ORDER BY title ASC ")
				.setParameter(0, title);
		return query.list();
	}

	@Deprecated
	// 条件查询
	public List<Info> finByConditions(QueryHelper qh) {
		Query query = getSession().createQuery(qh.getHql());
		List<Object> list = qh.getParameters();
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		return query.list();
	}

}
