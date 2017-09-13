package com.Smileyes.nsfw.info.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/*
 * Info信息实体类
 * 
 * @author Smileyes
 *
 */
public class Info {
	// 信息有效
	public final static String INFO_VALID_STATE = "1";
	// 信息停用
	public final static String INFO_INVALID_STATE = "0";

	// 信息类型：通知公告、政策速递、纳税指导、其它
	private final static String INFO_TYPE_TZGG = "tzgg";
	private final static String INFO_TYPE_ZCSD = "zcsd";
	private final static String INFO_TYPE_NSZD = "nszd";
	private final static String INFO_TYPE_OTHERS = "others";
	// 信息类型的集合
	public final static Map<String, String> INFO_TYPE_MAP;
	static {
		INFO_TYPE_MAP = new HashMap<String, String>();
		INFO_TYPE_MAP.put(INFO_TYPE_TZGG, "通知公告");
		INFO_TYPE_MAP.put(INFO_TYPE_ZCSD, "政策速递");
		INFO_TYPE_MAP.put(INFO_TYPE_NSZD, "纳税指导");
		INFO_TYPE_MAP.put(INFO_TYPE_OTHERS, "其它");
	}

	// 主键编号
	private String id;
	// 类型：常量
	private String type;
	// 来源
	private String source;
	// 标题
	private String title;
	// 内容
	private String content;
	// 备注
	private String memo;
	// 发送者
	private String creator;
	// 发送时间
	private Timestamp createTime;
	// 信息状态
	private String state;

	public Info() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}