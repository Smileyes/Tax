package com.Smileyes.nsfw.info.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Smileyes.core.action.BaseAction;
import com.Smileyes.core.constant.Constant;
import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.entity.Info;
import com.Smileyes.nsfw.info.service.InfoService;
import com.Smileyes.nsfw.user.entity.User;

/*
 * 信息InfoAction
 * 
 * @author Smileyes
 *
 */
@Controller
@Scope("prototype")
public class InfoAction extends BaseAction {
	@Resource
	private InfoService infoService;
	private Info info;

	// 添加信息前
	public String addUI() throws Exception {
		// 获得所有信息分类的集合
		ServletActionContext.getContext().getContextMap().put("infoTypeMap",
				Info.INFO_TYPE_MAP);
		info = new Info();
		// 信息创建时间
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}

	// 添加信息
	public String add() throws Exception {
		if (info != null && StringUtils.isNotBlank(info.getTitle())) {
			infoService.add(info);
			Map<String, Object> session = ServletActionContext.getContext()
					.getSession();
			User user = (User) session.get(Constant.USER);
			Log log = LogFactory.getLog(this.getClass());
			log.info(user.getName() + "添加了一条信息，对应的标题是：" + info.getTitle());
		}
		return "list";
	}

	// 删除信息
	public String delete() throws Exception {
		if (info != null & StringUtils.isNotBlank(info.getId())) {
			infoService.delete(info.getId());
		}
		return "list";
	}

	// 删除选中的信息
	public String deleteSelect() throws Exception {
		if (selectedRow != null && selectedRow.length != 0) {
			for (String id : selectedRow) {
				infoService.delete(id);
			}
		}
		return "list";
	}

	// 修改信息前
	public String editUI() throws Exception {
		if (info != null & info.getId() != null) {
			info = infoService.findById(info.getId());
			// 获得所有信息分类的集合
			ServletActionContext.getContext().getContextMap().put("infoTypeMap",
					Info.INFO_TYPE_MAP);
			return "editUI";
		} else {
			return "list";
		}
	}

	// 修改信息
	public String update() throws Exception {
		if (info != null && StringUtils.isNoneEmpty(info.getId())) {
			// 保存新信息
			infoService.update(info);
			Map<String, Object> session = ServletActionContext.getContext()
					.getSession();
			User user = (User) session.get(Constant.USER);
			Log log = LogFactory.getLog(this.getClass());
			log.info(user.getName() + "修改了标题为\"" + info.getTitle() + "的信息");
		}
		return "list";
	}

	// 分页显示
	public String listUI() throws Exception {
		// 新建查询助手,按照新建时间倒序排列
		if (pageResult == null) {
			// 当前页为第一页
			pageResult = new PageResult(1);
		}
		// 添加数据来源与排序
		QueryHelper qh = new QueryHelper(Info.class).addOrderByProperty("createTime",
				QueryHelper.ORDER_DSEC);
		// 添加条件
		if (StringUtils.isNotBlank(search)) {
			qh.addCondition("title like ?","%"+ search.trim()+"%", QueryHelper.CONDITIONS_NULL);
		}
		// 每页显示3条数据
		pageResult.setPageSize(3);
		// 查询数据，获取pageResult的其它数据
		pageResult = this.infoService.getPageResult(qh, pageResult);
		// 获得所有信息的分类集合
		ServletActionContext.getContext().getContextMap().put("infoTypeMap",
				Info.INFO_TYPE_MAP);
		return "listUI";
	}


	// 改变信息状态
	public void changeState() {
		if (info != null && StringUtils.isNoneBlank(info.getId())) {
			Info info2 = this.infoService.findById(info.getId());
			info2.setState(info.getState());
			this.infoService.update(info2);
		}
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}