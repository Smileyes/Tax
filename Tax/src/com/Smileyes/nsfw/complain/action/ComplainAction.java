package com.Smileyes.nsfw.complain.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Smileyes.core.action.BaseAction;
import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.complain.entity.Complain;
import com.Smileyes.nsfw.complain.entity.Reply;
import com.Smileyes.nsfw.complain.service.ComplainService;

import net.sf.json.JSONObject;

/*
 * 信息ComplainAction
 * 
 * @author Smileyes
 *
 */
@Controller
@Scope("prototype")
public class ComplainAction extends BaseAction {
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private String startTime;
	private String endTime;
	private Reply reply;
	private String year;

	// 分页显示
	public String listUI() throws Exception {
		if (pageResult == null) {
			pageResult = new PageResult(1);
		}
		pageResult.setPageSize(3);
		QueryHelper queryHelper = new QueryHelper(Complain.class)
				.addOrderByProperty("state", QueryHelper.ORDER_ASC)
				.addOrderByProperty("comTime", QueryHelper.ORDER_DSEC);
		// 搜索条件
		if (complain != null) {
			// 状态
			if (StringUtils.isNotBlank(complain.getState())) {
				queryHelper.addCondition("state = ?", complain.getState(),
						QueryHelper.CONDITIONS_AND);
			}
			// 标题
			if (StringUtils.isNotBlank(complain.getComTitle())) {
				queryHelper.addCondition("comTitle like ?",
						"%" + complain.getComTitle() + "%", QueryHelper.CONDITIONS_AND);
			}

		}
		// 起始日期
		if (StringUtils.isNotBlank(startTime)) {
			queryHelper.addCondition("comTime >=?",
					DateUtils.parseDate(startTime + ":00", "yyyy-MM-dd HH:mm:ss"),
					QueryHelper.CONDITIONS_AND);
		}
		// 结束日期
		if (StringUtils.isNotBlank(endTime)) {
			queryHelper.addCondition("comTime <=?",
					DateUtils.parseDate(endTime + ":59", "yyyy-MM-dd HH:mm:ss"),
					QueryHelper.CONDITIONS_AND);
		}
		pageResult = this.complainService.getPageResult(queryHelper, pageResult);
		// 获得状态集合
		ServletActionContext.getContext().getContextMap().put("complainStateMap",
				Complain.COM_STATE_MAP);
		return "listUI";
	}

	public Complain getComplain() {
		return complain;
	}

	// 投诉受理
	public String dealUI() throws Exception {
		if (complain != null && StringUtils.isNotBlank(complain.getComId())) {
			complain = complainService.findById(complain.getComId());
			// 匿名投诉
			if ("1".equals(complain.getIsNm())) {
				complain.setComCompany("***");
				complain.setComName("***");
				String mobile = complain.getComMobile().substring(0, 3) + "********";
				complain.setComMobile(mobile);
			}

			return "dealUI";
		} else {
			return "list";
		}
	}

	// 添加受理信息
	public String deal() throws Exception {
		if (reply != null && complain != null
				&& StringUtils.isNotBlank(complain.getComId())) {
			complain = complainService.findById(complain.getComId());
			complain.setState(Complain.REPLY);
			reply.setComplain(complain);
			reply.setRepTime(new Timestamp(new Date().getTime()));
			this.complainService.addReply(reply);
		}
		return "list";
	}

	// 批量处理该时刻前未处理的信息
	public void dealOldComplain() throws Exception {
		QueryHelper qh = new QueryHelper(Complain.class)
				.addCondition("state=?", Complain.UNREPLY, QueryHelper.CONDITIONS_NULL)
				.addCondition("comTime<=?", new Date(), QueryHelper.CONDITIONS_AND);
		List<Complain> list = complainService.queryByCondition(qh);
		for (Complain comp : list) {
			comp.setState(Complain.OUTOFDATE);
			complainService.update(comp);
		}
	}

	// 投诉信息统计页面
	public String annualStatisticChartUI() throws Exception {
		// 新建年份集合
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		List<Integer> yearList = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			yearList.add(year - i);
		}
		Map<String, Object> request = ServletActionContext.getContext().getContextMap();
		request.put("year", year);
		request.put("yearList", yearList);
		return "annualStatisticChartUI";
	}

	// 投诉信息统计
	public void annualStatisticChart() throws Exception {
		System.out.println("连接");
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(year)) {
			json.put("msg", "success");
			int yearNum = Integer.parseInt(year);
			// 查询结果
			List<Object[]> list = complainService.annualStatic(yearNum);
			// 返回给浏览器的结果集合
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> resultMap = null;
			// 查询结果不为空
			if (list != null) {
				// 遍历查询结果
				for (int i = 0; i < list.size(); i++) {
					resultMap = new HashMap<String, Object>();
					resultMap.put("label", i + 1 + "");
					if (list.get(i)[1] == null) {
						resultMap.put("value", list.get(i)[1]);
					}
					resultMap.put("color", "008ee4");
					resultMap.put("stepSkipped", false);
					resultMap.put("appliedSmartLabel", true);
					result.add(resultMap);
				}
			}
			json.put("result", result);
		} else {
			json.put("msg", "error");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pt = response.getWriter();
		System.out.println(json.toString());
		pt.write(json.toString());
		pt.close();
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}