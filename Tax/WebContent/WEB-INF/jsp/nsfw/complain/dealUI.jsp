<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>投诉受理管理</title>
</head>
<body class="rightBody">
	<form id="form" name="form" action="complain_deal.action" method="post"
		enctype="multipart/form-data">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>投诉受理管理</strong>&nbsp;-&nbsp;投诉受理
						</div>
					</div>
					<div class="tableH2">
						投诉详细信息
						<s:if test="%{complain.state==0}">
							<span style="color: red;">(未受理)</span>
						</s:if>
						<s:else>
							<span style="color: green;">(已受理)</span>
						</s:else>

					</div>
					<table id="baseInfo" width="100%" align="center" class="list"
						border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" align="center">投诉人信息</td>
						</tr>
						<tr>
							<td class="tdBg" width="250px">是否匿名投诉：</td>
							<td><s:property value="complain.isNm==1?'是':'否'" /></td>
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
							<td><s:date name="complain.comTime"
									format="yyyy-MM-dd HH:mm" /></td>
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
							<td><s:property value="complain.comTitle" /></td>
						</tr>
						<tr>
							<td class="tdBg">投诉内容：</td>
							<td><s:property value="complain.toComContent" escapeHtml="false"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center">受理信息</td>
						</tr>
						<s:if test="%{complain.state==0}">
							<td colspan="2" align="center">无</td>
						</s:if>
						<s:else>
							<s:iterator value="complain.replies" status="sta">
								<tr>
									<td colspan="2">
										<fieldset style="border: solid 1px #c0c0c0; margin-top: 5px;">
											<legend style="color: green; font-weight: bold;">
												回复
												<s:property value="sta.count" />
												&nbsp;
											</legend>
											<div
												style="width: 100%; text-align: center; color: #ccc; maring-top: 5px;">
												回复部门：
												<s:property value="repDept" />
												&nbsp;&nbsp; 回复人：
												<s:property value="repName" />
												&nbsp;&nbsp; 回复时间：
												<s:date name="repTime" format="yyyy-MM-dd HH:mm" />
											</div>
											<div
												style="width: 100%; maring-top: 10px; font-size: 13px; padding-left: 5px;">
												<s:property value="repContent" escape="false" />
											</div>
										</fieldset>
									</td>
								</tr>
							</s:iterator>
						</s:else>

						<tr>
							<td colspan="2" align="center">受理操作</td>
						</tr>
						<tr>
							<td class="tdBg">回复部门：</td>
							<td><s:property value="#session.SYS_USER.dept" /> <s:hidden
									name="reply.repDept" value="%{#session.SYS_USER.dept}"></s:hidden>
								<s:hidden name="complain.comId" value="%{complain.comId}" /></td>
						</tr>
						<tr>
							<td class="tdBg">回复人：</td>
							<td><s:property value="#session.SYS_USER.name" /> <s:hidden
									name="reply.repName" value="%{#session.SYS_USER.name}"></s:hidden>
							</td>

						</tr>

						<tr>
							<td class="tdBg" width="200px">回复内容：</td>
							<td><s:textarea name="reply.repContent" cols="90" rows="8" /></td>
						</tr>
					</table>

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
</html>