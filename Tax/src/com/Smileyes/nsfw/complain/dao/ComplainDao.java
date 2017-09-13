package com.Smileyes.nsfw.complain.dao;

import java.util.List;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.complain.entity.Complain;
import com.Smileyes.nsfw.complain.entity.Reply;

/*
 * ComplainDao接口
 * 
 * @author Smileyes
 *
 */
public interface ComplainDao extends BaseDao<Complain> {
	// 添加受理信息
	void addReply(Reply reply);

}
