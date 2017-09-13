package com.Smileyes.nsfw.info.dao;

import java.util.List;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.entity.Info;

/*
 * InfoDao接口
 * 
 * @author Smileyes
 *
 */
public interface InfoDao extends BaseDao<Info> {

	@Deprecated
	// 通过标题查询
	public List<Info> findByTitle(String title);

	@Deprecated
	// 条件查询
	public List<Info> finByConditions(QueryHelper qh);

}
