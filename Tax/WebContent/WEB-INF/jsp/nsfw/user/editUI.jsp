<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>

<title>用户管理</title>

</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<body class="rightBody">
	<form id="form" name="form" action="nsfw/user_update.action" method="post"
		enctype="multipart/form-data">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户
						</div>
					</div>
					<div class="tableH2">编辑用户</div>
					<table id="baseInfo" width="100%" align="center" class="list"
						border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="tdBg" width="200px">所属部门：</td>
							<td><s:select name="user.dept"
									list="#{'部门A':'部门A','部门B':'部门B'}" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">头像：</td>
							<td><s:if test="user.headImg!=null&&user.headImg!=''">
									<img src="${basePath}<s:property value='user.headImg'/>"
										width="100" height="100" />
									<s:hidden name="user.headImg" value="%{user.headImg}"></s:hidden>
								</s:if> <input type="file" name="upload" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">用户名：</td>
							<td><s:textfield name="user.name" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">帐号：</td>
							<td><s:textfield name="user.account" onchange="verifyAccount()"/></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">密码：</td>
							<td><s:textfield name="user.password" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">性别：</td>
							<td><s:radio list="#{'true':'男','false':'女'}"
									name="user.gender" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">角色：</td>
							<td><s:checkboxlist list="roleList" name="selectedRow" listKey="id" listValue="name"></s:checkboxlist></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">电子邮箱：</td>
							<td><s:textfield name="user.email" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">手机号：</td>
							<td><s:textfield name="user.mobile" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">生日：</td>
							<td><s:textfield id="birthday" name="user.birthday"
									readonly="true"
									onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
									<s:param name="value">
										<s:date name="user.birthday" format="yyyy-MM-dd" />
									</s:param>
								</s:textfield></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">状态：</td>
							<td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">备注：</td>
							<td><s:textarea name="user.memo" cols="75" rows="3" /></td>
						</tr>
					</table>
					<s:hidden name="user.id"></s:hidden>
					<div class="tc mt20">
						<input type="button" class="btnB2" value="保存" onclick="beforeSubmit()"/>
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
							onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	//输入帐号后验证
	function verifyAccount() {
		var $account = $(":text[name='user.account']");
		var account = $account.val();
		var id=$(":hidden[name='user.id']").val();
		if ($.trim(account) == "") {
			$account.next("span").remove();
			$account
					.after("<span style='color: red;' class='addError'>帐号未填写</span>");
			$account.val("");
			$account.focus();
			return false;
		} else {
			var validate = false;
			$.ajax({
					"url" : "${basePath}nsfw/user_verifyAccount.action",
					"data" : {
						'user.account':account,
						'user.id':id
					},
					"mehtod" : "post",
					"dataType" : "text",
					"async" : false,
					"success" : function(msg) {
						if (msg == "true") {
							$account.next("span").remove();
							$account.after("<span style='color: red;' class='addError'>该账号已存在，请修改帐号名称！</span>");
							$account.focus();
						} else {
							$account.next("span").remove();
							validate = true;
						}
					}
				});
			if (!validate) {
				return validate;
			}
		}
	}
	//登录前验证
	function beforeSubmit() {
		//删除之前的错误信息
		$(".addError").remove();
		//用于判断是否可以登录
		var canSubmit = true;
		// 检查用户名
		var name = $(":text[name='user.name']").val();
		if ($.trim(name) == "") {
			$(":text[name='user.name']").after(
					"<span style='color: red;'class='addError'>用户名未填写</span>");
			canSubmit = false;
		}
		// 检查帐号
		canSubmit = verifyAccount();
		// 检查密码
		var password = $(":text[name='user.password']").val();
		var pwdReg = /^\w{6,15}$/;
		if (!pwdReg.test($.trim(password))) {
			$(":text[name='user.password']").after("<span style='color: red;' class='addError'>密码位数应在6~15位之间，且只包含英文与数字</span>");
			canSubmit = false;
		}
		//检查邮箱
		var email = $(":text[name='user.email']").val();
		var emailReg = /^(\w+)@(\w+)\.(\w{2,6})$/;
		if (!emailReg.test($.trim(email))) {
			$(":text[name='user.email']").after(
					"<span style='color: red;' class='addError'>邮箱格式错误</span>");
			canSubmit = false;
		}
		//检查角色
		var role=$("[name=selectedRow]:checked").length;
		if(role==0){
			$("[name=selectedRow]").last().parent().append(
			"<span style='color: red;' class='addError'>角色未选择</span>");
	canSubmit = false;
		}
		//检查手机号
		var mobile = $(":text[name='user.mobile']").val();
		var mobileReg = /^1[0-9]{10}$/;
		if (!mobileReg.test($.trim(mobile))) {
			$(":text[name='user.mobile']")
					.after(
							"<span style='color: red;' class='addError'>手机号格式错误</span>");
			canSubmit = false;
		}
		//检查生日
		var birthday = $(":text[name='user.birthday']").val();
		if (birthday == "") {
			$(":text[name='user.birthday']").after(
					"<span style='color: red;' class='addError'>请选择生日</span>");
			canSubmit = false;
		}
		if (canSubmit!=false) {
			document.forms[0].submit();
		}
	}
</script>
</html>