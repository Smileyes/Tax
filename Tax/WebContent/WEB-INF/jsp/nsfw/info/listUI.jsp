<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<%@include file="/common/header.jsp"%>

<title>信息发布管理</title>
<script type="text/javascript">
	//新增
	function doAdd() {
		document.forms[0].action = "nsfw/info_addUI.action";
		document.forms[0].submit();
	}
	//编辑
	function doEdit(id) {
		document.forms[0].action = "nsfw/info_editUI.action?info.id=" + id;
		document.forms[0].submit();
	}

	//删除
	function doDelete(id) {
		document.forms[0].action = "nsfw/info_delete.action?info.id=" + id;
		document.forms[0].submit();
	}
	//批量删除
	function doDeleteAll() {
		document.forms[0].action = "nsfw/info_deleteSelect.action";
		document.forms[0].submit();
	}
	//搜索
	function doSearch() {
		document.forms[0].action = "nsfw/info_listUI.action";
		document.forms[0].submit();
	}
	//按下Enter搜索
	function doEnter() {
		var event = window.event;
		if (event.which == 13) {
			doSearch();
		}
	}
	//改变信息状态
	function changeState(id, state) {
		$.ajax({
			url : "nsfw/info_changeState.action",
			data : {
				"info.id" : id,
				"info.state" : state
			},
			method : "post",
			success : function() {
				if (state == 1) {
					$("#state_" + id).html("发布");
					$("#change_" + id).html(
							'<a href="javascript:changeState(\'' + id
									+ '\',0)">停用</a>');
				} else {
					$("#state_" + id).html("停用");
					$("#change_" + id).html(
							'<a href="javascript:changeState(\'' + id
									+ '\',1)">发布</a>');
				}
			},
			error : function() {
				alert("修改失败，请联系网站管理员！！");
			}
		});
	}

	//全选、全反选
	function doSelectAll() {
		// jquery 1.6 前
		//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
		//prop jquery 1.6+建议使用
		$("input[name=selectedRow]").prop("checked",
				$("#selAll").is(":checked"));
	}

	//翻页与跳转页面
	function doGoPage(pageNum, totalPage) {
		if (isNaN(pageNum)) {
			alert("请输入数字！！！");
		} else if (pageNum > totalPage || pageNum <= 0) {
			alert("该页不存在！！！");
		} else {
			document.forms[0].action = "info_listUI.action?pageResult.pageNum="
					+ pageNum;
			document.forms[0].submit();
		}
	}
</script>
</head>
<body class="rightBody">
	<form name="form1" action="" method="post">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>信息发布管理</strong>
						</div>
					</div>
					<div class="search_art">
						<li>信息标题：<s:textfield name="search" cssClass="s_text"
								id="infoTitle" cssStyle="width:160px;" onkeydown="doEnter()" />
						</li>
						<li><input type="button" class="s_button" value="搜 索"
							onclick="doSearch()" /></li>
						<li style="float: right;"><input type="button" value="新增"
							class="s_button" onclick="doAdd()" />&nbsp; <input type="button"
							value="删除" class="s_button" onclick="doDeleteAll()" />&nbsp;</li>
					</div>

					<div class="t_list" style="margin: 0px; border: 0px none;">
						<table width="100%" border="0">
							<tr class="t_tit">
								<td width="30" align="center"><input type="checkbox"
									id="selAll" onclick="doSelectAll()" /></td>
								<td align="center">信息标题</td>
								<td width="120" align="center">信息分类</td>
								<td width="120" align="center">创建人</td>
								<td width="140" align="center">创建时间</td>
								<td width="80" align="center">状态</td>
								<td width="120" align="center">操作</td>
							</tr>
							<s:if test="pageResult.totalNum==0">
								<tr>
									<td colspan="7" align="center"><strong
										style="font-size: 30px;">对不起，不存在相关数据</strong></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="pageResult.items" status="st">
									<tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if>>
										<td align="center"><input type="checkbox"
											name="selectedRow" value="<s:property value='id'/>" /></td>
										<td align="center"><s:property value="title" /></td>
										<td align="center"><s:property value="#infoTypeMap[type]" />
										</td>
										<td align="center"><s:property value="creator" /></td>
										<td align="center"><s:date name="createTime"
												format="yyyy-MM-dd HH:mm" /></td>
										<td align="center" id="state_<s:property value='id'/>"><s:property
												value="state==1?'发布':'停用'" /></td>
										<td align="center"><span
											id="change_<s:property value='id'/>"> <s:if
													test="state==1">
													<a
														href="javascript:changeState('<s:property value='id'/>',0)">停用</a>
												</s:if> <s:else>
													<a
														href="javascript:changeState('<s:property value='id'/>',1)">发布</a>
												</s:else></span> <a href="javascript:doEdit('<s:property value='id'/>')">编辑</a>
											<a href="javascript:doDelete('<s:property value='id'/>')">删除</a>
										</td>
									</tr>
								</s:iterator>
							</s:else>
						</table>
					</div>
				</div>
				<%@include file="/common/pageNavigator.jsp"%>
			</div>
		</div>
	</form>

</body>
</html>