package com.Smileyes.nsfw.complain.dao.impl;

import com.Smileyes.core.dao.impl.BaseDaoImpl;
import com.Smileyes.nsfw.complain.dao.ComplainDao;
import com.Smileyes.nsfw.complain.entity.Complain;
import com.Smileyes.nsfw.complain.entity.Reply;

/*
 * 处理投诉操作的DAO
 * 
 * @author Smileyes
 *
 */

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {
	// 添加受理信息
	public void addReply(Reply reply) {
		this.getHibernateTemplate().save(reply);
	}
}
