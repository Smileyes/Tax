package com.Smileyes.nsfw.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.service.impl.BaseServiceImpl;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.dao.InfoDao;
import com.Smileyes.nsfw.info.entity.Info;
import com.Smileyes.nsfw.info.service.InfoService;

/*
 * InfoService的实现类
 * 
 * @author Smileyes
 *
 */
@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {
	private  InfoDao infoDao;

	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}

	public InfoDao getInfoDao() {
		return infoDao;
	}

	/*
	 * 标题模糊查询
	 */
	public List<Info> findByTitle(String title) {
		return infoDao.findByTitle(title);
	}

	/*
	 * 条件查询
	 */

	public List<Info> findByConditions(QueryHelper qh) {

		return infoDao.finByConditions(qh);
	}

	

}
