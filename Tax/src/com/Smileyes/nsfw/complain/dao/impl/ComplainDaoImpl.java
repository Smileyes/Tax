package com.Smileyes.nsfw.complain.dao.impl;

import java.util.List;

import org.hibernate.Query;

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

	// 查询某年中每个月的投诉数量
	public List<Object[]> groupByMonth(int year) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT monthNum,c2 FROM months m ");
		sb.append(
				"LEFT JOIN (SELECT  MONTH(comTime) c1,COUNT(comId) c2 FROM complain  WHERE YEAR(comTime)=? GROUP BY MONTH(comTime)) c ");
		sb.append("ON monthNum=c1 ");
		sb.append("ORDER BY monthNum ");
		String sql=sb.toString();
		Query query = this.getSession().createSQLQuery(sb.toString());
		query.setParameter(0, year);
		List list = query.list();
		return query.list();
	}
}
