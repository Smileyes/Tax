package com.Smileyes.home.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.Smileyes.core.constant.Constant;
import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.complain.entity.Complain;
import com.Smileyes.nsfw.complain.service.ComplainService;
import com.Smileyes.nsfw.info.entity.Info;
import com.Smileyes.nsfw.info.service.InfoService;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/*
 * 系统主页Action
 * 
 * @author Smileyes
 *
 */
@Controller
public class HomeAction extends ActionSupport {
	@Resource
	private ComplainService complainService;
	@Resource
	private UserService userService;
	@Resource
	private InfoService infoService;
	private PageResult comPageResult;
	private PageResult infoPageResult;
	private Complain complain;
	private Map<String, Object> result;
	private Info info;

	/*
	 * 返回主页
	 */
	public String home() throws Exception {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		User user = (User) session.get(Constant.USER);
		// 获得投诉集合
		if (comPageResult == null) {
			comPageResult = new PageResult(1);
		}
		comPageResult.setPageSize(8);
		QueryHelper queryHelper = new QueryHelper(Complain.class)
				.addOrderByProperty("state", QueryHelper.ORDER_ASC)
				.addOrderByProperty("comTime", QueryHelper.ORDER_DSEC)
				.addCondition("comName=?", user.getName(), QueryHelper.CONDITIONS_NULL);
		// 获得前几条投诉
		comPageResult = this.complainService.getPageResult(queryHelper, comPageResult);
		// 获得投诉状态集合
		ServletActionContext.getContext().getContextMap().put("complainStateMap",
				Complain.COM_STATE_MAP);

		// 获得信息集合
		if (infoPageResult == null) {
			infoPageResult = new PageResult(1);
		}
		QueryHelper qh = new QueryHelper(Info.class)
				.addOrderByProperty("state", QueryHelper.ORDER_ASC)
				.addOrderByProperty("createTime", QueryHelper.ORDER_DSEC).addCondition(
						"state=?", Info.INFO_VALID_STATE, QueryHelper.CONDITIONS_NULL);
		// 获得前几条信息
		infoPageResult.setPageSize(5);
		infoPageResult = this.complainService.getPageResult(qh, infoPageResult);
		// 获得所有信息分类的集合
		ServletActionContext.getContext().getContextMap().put("infoTypeMap",
				Info.INFO_TYPE_MAP);
		return "home";
	}

	/*
	 * 投诉界面
	 */
	public String complainAddUI() throws Exception {
		// 获得状态集合
		ServletActionContext.getContext().getContextMap().put("complainStateMap",
				Complain.COM_STATE_MAP);
		return "complainAddUI";
	}

	/*
	 * 添加投诉
	 */
	public void complainAdd() throws Exception {
		JSONObject json = new JSONObject();
		if (complain != null) {
			complain.setComTime(new Date());
			complain.setState(Complain.UNREPLY);
			complainService.add(complain);
			json.accumulate("msg", "success");
		} else {
			json.accumulate("msg", "failed");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pt = response.getWriter();
		pt.write(json.toString());
		pt.close();
	}

	// 使用struts—json-plugin生成json对象
	public String findUserJson() throws Exception {
		if (complain != null && StringUtils.isNotBlank(complain.getToComDept())) {
			// 获得所有员工
			String dept = complain.getToComDept();
			QueryHelper qh = new QueryHelper(User.class);
			qh.addCondition("dept=?", dept, QueryHelper.CONDITIONS_NULL);
			List<User> userList = this.userService.queryByCondition(qh);
			// 添加到json中
			result = new HashMap<String, Object>();
			result.put("msg", "success");
			result.put("userList", userList);
		}
		return SUCCESS;
	}

	// 使用传统的输出流，输出json语句
	public void findUserJson2() throws Exception {
		if (complain != null && StringUtils.isNotBlank(complain.getToComDept())) {
			// 获得所有员工
			String dept = complain.getToComDept();
			QueryHelper qh = new QueryHelper(User.class);
			qh.addCondition("dept=?", dept, QueryHelper.CONDITIONS_NULL);
			List<User> userList = this.userService.queryByCondition(qh);
			// 添加到json中
			JSONObject json = new JSONObject();
			json.put("msg", "success");
			json.put("userList", userList);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pt = response.getWriter();
			pt.write(json.toString());
			pt.close();
		}
	}

	// 查看投诉详情
	public String complainViewUI() throws Exception {
		if (complain != null && StringUtils.isNoneBlank(complain.getComId())) {
			complain = complainService.findById(complain.getComId());
		}
		// 获得投诉状态集合
		ServletActionContext.getContext().getContextMap().put("complainStateMap",
				Complain.COM_STATE_MAP);
		return "complainViewUI";

	}

	// 查看信息详情
	public String infoViewUI() throws Exception {
		if (info != null && StringUtils.isNoneBlank(info.getId())) {
			info = infoService.findById(info.getId());
		}
		// 获得所有信息分类的集合
		ServletActionContext.getContext().getContextMap().put("infoTypeMap",
				Info.INFO_TYPE_MAP);
		return "infoViewUI";

	}

	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public PageResult getComPageResult() {
		return comPageResult;
	}

	public void setComPageResult(PageResult comPageResult) {
		this.comPageResult = comPageResult;
	}

	public PageResult getInfoPageResult() {
		return infoPageResult;
	}

	public void setInfoPageResult(PageResult infoPageResult) {
		this.infoPageResult = infoPageResult;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}
