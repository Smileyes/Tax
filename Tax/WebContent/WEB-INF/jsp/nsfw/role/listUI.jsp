<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>角色管理</title>
<script type="text/javascript">
	//新增
	function doAdd() {
		document.forms[0].action = "nsfw/role_addUI.action";
		document.forms[0].submit();
	}
	//编辑
	function doEdit(id) {
		document.forms[0].action = "nsfw/role_editUI.action?role.id=" + id;
		document.forms[0].submit();
	}

	//删除
	function doDelete(id) {
		document.forms[0].action = "nsfw/role_delete.action?role.id=" + id;
		document.forms[0].submit();
	}
	//批量删除
	function doDeleteAll() {
		document.forms[0].action = "nsfw/role_deleteSelect.action";
		document.forms[0].submit();
	}
	//搜索
	function doSearch() {
		document.forms[0].action = "nsfw/role_listUI.action";
		document.forms[0].submit();
	}
	//按下Enter搜索
	function doEnter() {
		var event = window.event;
		if (event.which == 13) {
			doSearch();
		}
	}

	//全选、全反选
	function doSelectAll() {
		// jquery 1.6 前
		//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
		//prop jquery 1.6+建议使用
		$("input[name=selectedRow]").prop("checked",
				$("#selAll").is(":checked"));
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
							<b></b><strong>角色管理 </strong>
						</div>
					</div>
					<div class="search_art">
						<li>角色名称：<s:textfield name="search" cssClass="s_text"
								id="roleName" cssStyle="width:160px;" onkeydown="doEnter()" />
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
								<td width="120" align="center">序号</td>
								<td width="120" align="center">角色名称</td>
								<td align="center">权限</td>
								<td width="80" align="center">状态</td>
								<td width="120" align="center">操作</td>
							</tr>
							<s:if test="pageResult.totalNum==0">
								<tr>
									<td colspan="6" align="center"><strong
										style="font-size: 20px;">对不起，找不到你想要的数据！</strong></td>
								</tr>

							</s:if>
							<s:else>
								<s:iterator value="pageResult.items" status="stat">
									<tr bgcolor="f8f8f8">
										<td align="center"><input type="checkbox"
											name="selectedRow" value="<s:property value='id' />" /></td>
										<td align="center"><s:property value="#stat.count" /></td>
										<td align="center"><s:property value="name" /></td>
										<td align="center"><s:iterator value="rolePrivileges">
												<s:property value="#privilegeMap[code]" />
											</s:iterator></td>
										<td align="center"><s:property value="state==1?'有效':'无效'" /></td>
										<td align="center"><a
											href="javascript:doEdit('<s:property value="id" />')">编辑</a>
											<a href="javascript:doDelete('<s:property value="id" />')">删除</a></td>
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
	<script type="text/javascript">
		//翻页与跳转页面
		function doGoPage(pageNum, totalPage) {
			if (isNaN(pageNum)) {
				alert("请输入数字！！！");
			} else if (pageNum > totalPage || pageNum <= 0) {
				alert("该页不存在！！！");
			} else {
				document.forms[0].action = "role_listUI.action?pageResult.pageNum="
						+ pageNum;
				document.forms[0].submit();
			}
		}
	</script>
</body>
</html>