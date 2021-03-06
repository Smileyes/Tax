package com.Smileyes.nsfw.complain.service;

import java.util.List;

import com.Smileyes.core.service.BaseService;
import com.Smileyes.nsfw.complain.entity.Complain;
import com.Smileyes.nsfw.complain.entity.Reply;

/*
 * 用于处理投诉的Service服务类
 * 
 * @author Smileyes
 *
 */
public interface ComplainService extends BaseService<Complain> {
	// 添加受理信息
	public void addReply(Reply reply);

	// 投诉统计
	public List<Object[]> annualStatic(int year);

}
