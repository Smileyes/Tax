<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	pageContext.setAttribute("basePath", request.getContextPath() + "/");
%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript" src="${basePath}js/jquery/jquery.min.js"></script>
<link href="${basePath}css/skin1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//全选、全反选
	function doSelectAll() {
		// jquery 1.6 前
		//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
		//prop jquery 1.6+建议使用
		$("input[name=selectedRow]").prop("checked",
				$("#selAll").is(":checked"));
	}
	//添加
	function doAdd() {
		window.location.href = "user_addUI.action";
	}
	//删除
	function doDelete(id) {
		document.forms[0].action = "user_delete?user.id=" + id;
		document.forms[0].submit();
	}
	//修改
	function doEdit(id) {
		document.forms[0].action = "user_editUI?user.id=" + id;
		document.forms[0].submit();
	}
	//删除选中
	function doDeleteAll() {
		document.forms[0].action = "user_deleteSelect.action";
		document.forms[0].submit();
	}
	//搜索
	function doSearch() {
		document.forms[0].action = "user_doSearch.action?search="
				+ $("#search").val();
		document.forms[0].submit();
	}
	//导出
	function doExportExcel() {
		window.open("user_exportExcel.action");
	}
	//导入
	function doImportExcel() {
		document.forms[0].action = "user_importExcel.action";
		document.forms[0].submit();
	}
</script>
</head>
<body class="rightBody">
	<form name="form1" action="" method="post"
		enctype="multipart/form-data">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>用户管理</strong>
						</div>
					</div>
					<div class="search_art">
						<li>用户名：<s:textfield name="search" cssClass="s_text"
								id="search" cssStyle="width:160px;" placeholder="请输入用户名或账号" />
						</li>
						<li><input type="button" class="s_button" value="搜索"
							onclick="doSearch()" /></li>
						<li style="float: right;"><input type="button" value="新增"
							class="s_button" onclick="doAdd()" />&nbsp; <input type="button"
							value="删除" class="s_button" onclick="doDeleteAll()" />&nbsp; <input
							type="button" value="导出" class="s_button"
							onclick="doExportExcel()" />&nbsp; <input type="file"
							name="headImg" /> <input type="button" value="导入"
							class="s_button" onclick="doImportExcel()" />&nbsp;</li>
					</div>

					<div class="t_list" style="margin: 0px; border: 0px none;">
						<table width="100%" border="0">
							<tr class="t_tit">
								<td width="30" align="center"><input type="checkbox"
									id="selAll" onclick="doSelectAll()" /></td>
								<td width="140" align="center">序号</td>
								<td width="140" align="center">用户名</td>
								<td width="140" align="center">帐号</td>
								<td width="160" align="center">所属部门</td>
								<td width="80" align="center">性别</td>
								<td align="center">电子邮箱</td>
								<td width="100" align="center">操作</td>
							</tr>
							<s:if test="userList.size()==0">
								<tr>
									<td colspan="8" align="center"><b> 对不起，没有你想要找的数据。</b></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="userList" var="user" status="stat">
									<tr bgcolor="f8f8f8" id="">
										<td align="center"><input type="checkbox"
											name="selectedRow" value='<s:property value='id'/>' /></td>
										<td align="center" class="index"><s:property
												value="#stat.count" /></td>
										<td align="center"><s:property value="name" /></td>
										<td align="center"><s:property value="account" /></td>
										<td align="center"><s:property value="dept" /></td>
										<td align="center"><s:property value="gender?'男':'女'" /></td>
										<td align="center"><s:property value="email" /></td>
										<td align="center"><a
											href="javascript:doEdit('<s:property value='id' />')">编辑</a>
											<a href="javascript:doDelete('<s:property value='id' />')">删除</a></td>
									</tr>
								</s:iterator>
							</s:else>
						</table>
					</div>
				</div>
				<div class="c_pate" style="margin-top: 5px;">
					<table width="100%" class="pageDown" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td align="right">总共1条记录，当前第 1 页，共 1 页 &nbsp;&nbsp; <a
								href="#">上一页</a>&nbsp;&nbsp;<a href="#">下一页</a> 到&nbsp;<input
								type="text" style="width: 30px;"
								onkeypress="if(event.keyCode == 13){doGoPage(this.value);}"
								min="1" max="" value="1" /> &nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>

</body>
</html>