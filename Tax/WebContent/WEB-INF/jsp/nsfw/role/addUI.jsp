<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>角色管理</title>
</head>
<script type="text/javascript">
	//保存前的检查
	function beforeSubmit() {
		var canAdd = true;
		//检查角色名
		canAdd=verifyName();
		//检查权限
		var privilegeNum=$(":checked[name='selectedRow']").length;
		if(privilegeNum==0){
			canAdd=false;
			alert("权限未勾选");
		}
		if (canAdd !=false) {
			document.forms[0].action = "nsfw/role_add.action";
			document.forms[0].submit();
		}
	}
	// 检查角色名
	function verifyName() {
		var $name = $(":text[name='role.name']");
		var name = $name.val();
		if ($.trim(name) == "") {
			$name.next("span").remove();
			$name
					.after("<span style='color: red;' class='addError'>名称未填写</span>");
			$name.val("");
			$name.focus();
			return false;
		} else {
			var validate = false;
			$
					.ajax({
						"url" : ${basePath}+"nsfw/role_verifyName.action",
						"data" : {
							'role.name' : name
						},
						"mehtod" : "post",
						"dataType" : "text",
						"async" : false,
						"success" : function(msg) {
							if (msg == "true") {
								$name.next("span").remove();
								$name
										.after("<span style='color: red;' class='addError'>该角色已存在，请修改角色名称！</span>");
								$name.focus();
							} else {
								$name.next("span").remove();
								validate = true;
							}
						}
					});
			if (!validate) {
				return validate;
			}
		}
	}
</script>
<body class="rightBody">
	<form id="form" name="form" action="" method="post"
		enctype="multipart/form-data">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>角色管理</strong>&nbsp;-&nbsp;新增角色
						</div>
					</div>
					<div class="tableH2">新增角色</div>
					<table id="baseInfo" width="100%" align="center" class="list"
						border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="tdBg" width="200px">角色名称：</td>
							<td><s:textfield name="role.name" onchange="verifyName()" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">角色权限：</td>
							<td><s:checkboxlist list="privilegeMap" name="selectedRow"></s:checkboxlist></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">状态：</td>
							<td><s:radio list="#{'1':'有效','0':'无效'}" name="role.state"
									value="1" /></td>
						</tr>
					</table>

					<div class="tc mt20">
						<input type="button" class="btnB2" value="保存"
							onclick="beforeSubmit()" /> &nbsp;&nbsp;&nbsp;&nbsp; <input
							type="button" onclick="javascript:history.go(-1)" class="btnB2"
							value="返回" />
					</div>
				</div>
			</div>
		</div>


	</form>
</body>
</html>