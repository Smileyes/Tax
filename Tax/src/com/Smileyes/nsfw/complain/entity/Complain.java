package com.Smileyes.nsfw.complain.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @ClassName: Complain
 * @Description: 投诉类
 * @author Smileyes
 * @date 2017年9月8日 上午11:50:15
 *
 */
public class Complain implements java.io.Serializable {

	private String comId;
	private String comCompany;
	private String comName;
	private String comMobile;
	private String isNm;
	private Date comTime;
	private String comTitle;
	private String toComDept;
	private String toComName;
	private String toComContent;
	private String state;
	private Set<Reply> replies = new HashSet<Reply>();
	// 过期
	public final static String OUTOFDATE = "2";
	// 已受理
	public final static String REPLY = "1";
	// 未受理
	public final static String UNREPLY = "0";

	// 状态集合
	public static Map<String, String> COM_STATE_MAP;
	static {
		COM_STATE_MAP = new HashMap<String, String>();
		COM_STATE_MAP.put(UNREPLY, "未受理");
		COM_STATE_MAP.put(REPLY, "已受理");
		COM_STATE_MAP.put(OUTOFDATE, "已过期");
	}

	public Complain() {
	}

	public Complain(String comId, String comCompany, String comName, String comMobile,
			Date comTime, String comTitle, String toComDept, String toComContent,
			String state) {
		this.comId = comId;
		this.comCompany = comCompany;
		this.comName = comName;
		this.comMobile = comMobile;
		this.comTime = comTime;
		this.comTitle = comTitle;
		this.toComDept = toComDept;
		this.toComContent = toComContent;
		this.state = state;
	}

	public Complain(String comId, String comCompany, String comName, String comMobile,
			String isNm, Date comTime, String comTitle, String toComDept,
			String toComName, String toComContent, String state, Set replies) {
		this.comId = comId;
		this.comCompany = comCompany;
		this.comName = comName;
		this.comMobile = comMobile;
		this.isNm = isNm;
		this.comTime = comTime;
		this.comTitle = comTitle;
		this.toComDept = toComDept;
		this.toComName = toComName;
		this.toComContent = toComContent;
		this.state = state;
		this.replies = replies;
	}

	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getComCompany() {
		return this.comCompany;
	}

	public void setComCompany(String comCompany) {
		this.comCompany = comCompany;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComMobile() {
		return this.comMobile;
	}

	public void setComMobile(String comMobile) {
		this.comMobile = comMobile;
	}

	public String getIsNm() {
		return this.isNm;
	}

	public void setIsNm(String isNm) {
		this.isNm = isNm;
	}

	public Date getComTime() {
		return this.comTime;
	}

	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}

	public String getComTitle() {
		return this.comTitle;
	}

	public void setComTitle(String comTitle) {
		this.comTitle = comTitle;
	}

	public String getToComDept() {
		return this.toComDept;
	}

	public void setToComDept(String toComDept) {
		this.toComDept = toComDept;
	}

	public String getToComName() {
		return this.toComName;
	}

	public void setToComName(String toComName) {
		this.toComName = toComName;
	}

	public String getToComContent() {
		return this.toComContent;
	}

	public void setToComContent(String toComContent) {
		this.toComContent = toComContent;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set getReplies() {
		return replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}
