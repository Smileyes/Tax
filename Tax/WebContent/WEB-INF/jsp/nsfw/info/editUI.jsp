<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>信息发布管理</title>

<script type="text/javascript" charset="utf-8"
	src="${basePath }js/umeditor/umeditor.min.js"></script>
<link href="${basePath }js/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${basePath }js/umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${basePath }js/umeditor/umeditor.config.js"></script>

<script type="text/javascript"
	src="${basePath }js/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body class="rightBody">
	<form id="form" name="form" action="${basePath}nsfw/info_update.action"
		method="post" enctype="multipart/form-data">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>信息发布管理</strong>&nbsp;-&nbsp;修改信息
						</div>
					</div>
					<div class="tableH2">修改信息</div>
					<table id="baseInfo" width="100%" align="center" class="list"
						border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="tdBg" width="200px">信息分类：</td>
							<td><s:select name="info.type" list="#infoTypeMap" /></td>
							<td class="tdBg" width="200px">来源：</td>
							<td><s:textfield name="info.source" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">信息标题：</td>
							<td colspan="3"><s:textfield name="info.title"
									cssStyle="width:90%" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">信息内容：</td>
							<td colspan="3"><s:textarea id="editor" name="info.content"
									cssStyle="width:90%;height:160px;" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">备注：</td>
							<td colspan="3"><s:textarea name="info.memo" cols="90"
									rows="3" /></td>
						</tr>
						<tr>
							<td class="tdBg" width="200px">创建人：</td>
							<td><s:property value="info.creator" /> <s:hidden
									name="info.creator" value="%{info.creator}" /></td>
							<td class="tdBg" width="200px">创建时间：</td>
							<td><s:date name="info.createTime"
									format="yyyy-MM-dd HH:mm:ss" /> <s:hidden
									name="info.createTime" value="%{info.createTime}"></s:hidden></td>
						</tr>
					</table>
					<s:hidden name="info.id" />
					<s:hidden name="info.state" />
					<div class="tc mt20">
						<input type="submit" class="btnB2" value="保存" />
						&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
							onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	var editor = UM.getEditor('editor');
</script>
</html>