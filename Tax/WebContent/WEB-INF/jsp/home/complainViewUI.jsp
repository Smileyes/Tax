<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	pageContext.setAttribute("basePath", request.getContextPath() + "/");
%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>投诉信息</title>
</head>
<body class="rightBody">
	<div class="vp_d_1">
		<div style="width: 1%; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div class="vp_d_1_1">
			<div class="content_info">
				<div class="c_crumbs">
					<div>
						<b></b><strong>工作主页</strong>&nbsp;-&nbsp;投诉信息
					</div>
				</div>

				<div class="tableH2">
					投诉详细信息<span
						style="
					<s:if test='complain.state==0'>color:red</s:if>
					<s:if test='complain.state==1'>color: green</s:if>
					<s:if test='complain.state==2'>color: orange</s:if>
					">(<s:property
							value="#complainStateMap[complain.state]" />)
					</span>
				</div>
				<table id="baseInfo" width="100%" align="center" class="list"
					border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2" align="center">投诉人信息</td>
					</tr>
					<tr>
						<td class="tdBg" width="250px">是否匿名投诉：</td>
						<td><s:property value="complain.isNm==1?'匿名':'实名'" /></td>
					</tr>
					<tr>
						<td class="tdBg">投诉人单位：</td>
						<td><s:property value="complain.comCompany" /></td>
					</tr>
					<tr>
						<td class="tdBg">投诉人姓名：</td>
						<td><s:property value="complain.comName" /></td>
					</tr>
					<tr>
						<td class="tdBg">投诉人手机：</td>
						<td><s:property value="complain.comMobile" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">投诉信息</td>
					</tr>
					<tr>
						<td class="tdBg">投诉时间：</td>
						<td><s:property value="complain.comTime" /></td>
					</tr>
					<tr>
						<td class="tdBg">被投诉部门：</td>
						<td><s:property value="complain.toComDept" /></td>
					</tr>
					<tr>
						<td class="tdBg">被投诉人：</td>
						<td><s:property value="complain.toComName" /></td>
					</tr>
					<tr>
						<td class="tdBg">投诉标题：</td>
						<td><s:property value="complain.ComTitle" /></td>
					</tr>
					<tr>
						<td class="tdBg">投诉内容：</td>
						<td><s:property value="complain.toComContent"
								escapeHtml="false" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">受理信息</td>
					</tr>
					<s:iterator value="complain.replies" status="sta">
						<tr>
							<td colspan="2">
								<fieldset style="border: solid 1px #c0c0c0; margin-top: 5px;">
									<legend style="color: green; font-weight: bold;">
										回复
										<s:property value="#sta.count" />
										&nbsp;
									</legend>
									<div
										style="width: 100%; text-align: center; color: #ccc; maring-top: 5px;">
										回复部门：
										<s:property value="repDept" />
										&nbsp;&nbsp; 回复人：
										<s:property value="repName" />
										&nbsp;&nbsp; 回复时间：
										<s:date name="repTime" />
									</div>
									<div
										style="width: 100%; maring-top: 10px; font-size: 13px; padding-left: 5px;">
										<s:property value="repContent" escapeHtml="false" />
									</div>
								</fieldset>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="2" align="center"><input type="button"
							value="关闭" onclick="javaScript:window.close()"></td>
					</tr>
				</table>
			</div>
		</div>
		<div style="width: 1%; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
</body>
</html>