package com.Smileyes.nsfw.complain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Smileyes.core.service.impl.BaseServiceImpl;
import com.Smileyes.nsfw.complain.dao.ComplainDao;
import com.Smileyes.nsfw.complain.entity.Complain;
import com.Smileyes.nsfw.complain.entity.Reply;
import com.Smileyes.nsfw.complain.service.ComplainService;

/*
 * ComplainService的实现类
 * 
 * @author Smileyes
 *
 */
@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain>
		implements ComplainService {
	private ComplainDao complainDao;

	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

	public void addReply(Reply reply) {
		this.complainDao.addReply(reply);
	}

	// 投诉统计
	public List<Object[]>  annualStatic(int year) {
		return this.complainDao.groupByMonth(year);
	}

}
