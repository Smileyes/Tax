<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	pageContext.setAttribute("basePath", request.getContextPath() + "/");
%>
<html>
<head>
<%@include file="/common/header.jsp"%>

<title>我要投诉</title>
</head>
<link href="${basePath }js/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${basePath }js/umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${basePath }js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${basePath }js/umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="${basePath }js/umeditor/lang/zh-cn/zh-cn.js"></script>
<body>
	<form id="form" name="form" action=""
		method="post" enctype="multipart/form-data">
		<div class="vp_d_1">
			<div style="width: 1%; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="vp_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要投诉
						</div>
					</div>
					<div class="tableH2">我要投诉</div>
					<table id="baseInfo" width="100%" align="center" class="list"
						border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="tdBg" width="250px">投诉标题：</td>
							<td><s:textfield name="complain.comTitle" /></td>
						</tr>
						<tr>
							<td class="tdBg">被投诉人部门：</td>
							<td><s:select id="comDept" name="complain.toComDept"
									list="#{'部门A':'部门A','部门B':'部门B'}" headerKey=""
									headerValue="请选择" /></td>
						</tr>
						<tr>
							<td class="tdBg">被投诉人姓名：</td>
							<td><s:select id="toComName" list="#{ }" headerKey="#"
									headerValue="请选择" name="complain.toComName" />
						</tr>
						<tr>
							<td class="tdBg">投诉内容：</td>
							<td><s:textarea id="editor" name="complain.toComContent"
									cssStyle="width:90%;height:160px;" /></td>
						</tr>
						<tr>
							<td class="tdBg">是否匿名投诉：</td>
							<td><s:radio name="complain.isNm"
									list="#{'0':'非匿名投诉','1':'匿名投诉' }" value="0" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="button"
								class="btnB2" value="保存" onclick="doSubmit()"/> &nbsp;&nbsp;&nbsp;&nbsp; <input
								type="button" onclick="javascript:window.close()" class="btnB2"
								value="关闭" /></td>
						</tr>
					</table>
					<s:hidden name="complain.comCompany"
						value="%{#session.SYS_USER.dept}" />
					<s:hidden name="complain.comName" value="%{#session.SYS_USER.name}" />
					<s:hidden name="complain.comMobile"
						value="%{#session.SYS_USER.mobile}" />


				</div>
			</div>
		</div>
		<div style="width: 1%; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</form>
</body>
<script type="text/javascript">
	var um = UM.getEditor('editor');
	//加载部门下的员工
	$("#comDept").change(function() {
		var $dept = $(this).children(":selected").text();
		var toCompName = $("#toComName");
		toCompName.children(":gt(0)").remove();
		if ($dept != "请选择") {
			$.ajax({
				"url" : "home_findUserJson2.action",
				"data" : {"complain.toComDept" : $dept
					},
				method : "post",
				dataType : "json",
				success : function(data) {
				// 插件方式
				/* if (data != null
						&& data != undefined
						&& data.result.msg == "success") {
					var toCompName = $("#toComName");
					toCompName.children(":gt(0)")
							.remove();
					$.each(data.result.userList,
									function(index,
											user) {
										toCompName
												.append("<option value='" 
								+ user.name + "'>"
														+ user.name
														+ "</option>");
									});
				} else {
					alert("获取用户失败，请重试！");
				} */
				//常规输出流方式
					if (data != null&& data != undefined && data.msg == "success") {
						$.each(data.userList,function(index,user) {
										toCompName.append("<option value='" + user.name + "'>"
												+ user.name+ "</option>");
									});
					} else {
						alert("获取用户失败，请重试！");
					} 
				},
				error : function() {
					alert("操作失败！");
				}
			});
		}

	});
	//添加投诉信息
	function doSubmit(){
		$.ajax({
			"url":"home_complainAdd.action",
			data:$("form").serialize(),
			async:false,
			success:function(backData){
				var data=eval("("+backData+")");
				if(data.msg=="success"){
					alert("投诉成功");
					//更新父页面
					window.opener.parent.location.reload(true);
					//关闭本页
					window.close();
				}else{
					alert("投诉失败");
				}
			},error:function(){
				alert("投诉失败");
			}
		});
	}
</script>

</html>