<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>投诉受理管理</title>

</head>
<script type="text/javascript"
	src="${basePath}js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//搜索
	function doSearch() {
		document.forms[0].action = "complain_listUI.action";
		document.forms[0].submit();
	}
	//受理
	function doDeal(id) {
		document.forms[0].action = "complain_dealUI.action?complain.comId="
				+ id;
		document.forms[0].submit();
	}
	//翻页与跳转页面
	function doGoPage(pageNum, totalPage) {
		if (isNaN(pageNum)) {
			alert("请输入数字！！！");
		} else if (pageNum > totalPage || pageNum <= 0) {
			alert("该页不存在！！！");
		} else {
			document.forms[0].action = "complain_listUI.action?pageResult.pageNum="
					+ pageNum;
			document.forms[0].submit();
		}
	}
	//统计
	function doAnnualStatistic() {
		document.forms[0].action = "complain_annualStatisticChartUI.action";
		document.forms[0].submit();
	}
</script>
<body class="rightBody">
	<form name="form1" action="" method="post">
		<div class="p_d_1">
			<div class="p_d_1_1">
				<div class="content_info">
					<div class="c_crumbs">
						<div>
							<b></b><strong>投诉受理管理</strong>
						</div>
					</div>
					<div class="search_art">
						<li>投诉标题：<s:textfield name="complain.comTitle"
								cssClass="s_text" cssStyle="width:160px;" />
						</li>
						<li>投诉时间：<s:textfield id="startTime" name="startTime"
								cssClass="s_text" cssStyle="width:160px;" readonly="true"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" />
							- <s:textfield id="endTime" name="endTime" cssClass="s_text"
								cssStyle="width:160px;" readonly="true"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" />
						</li>
						<li>状态：<s:select name="complain.state"
								list="#complainStateMap" headerKey="" headerValue="全部" />
						</li>
						<li><input type="button" class="s_button" value="搜 索"
							onclick="doSearch()" /></li>
						<li style="float: right;"><input type="button" value="统计"
							class="s_button" onclick="doAnnualStatistic()" />&nbsp;</li>
					</div>

					<div class="t_list" style="margin: 0px; border: 0px none;">
						<table width="100%" border="0">
							<tr class="t_tit">
								<td align="center">投诉标题</td>
								<td width="120" align="center">被投诉部门</td>
								<td width="120" align="center">被投诉人</td>
								<td width="140" align="center">投诉时间</td>
								<td width="100" align="center">受理状态</td>
								<td width="100" align="center">操作</td>
							</tr>
							<s:if test="pageResult.totalNum==0">
								<tr>
									<td align="center" style="font-size: 20px;" colspan="6"><strong>对不起，找不到您想要的数据！</strong></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="pageResult.items" status="st">
									<tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if>>
										<td align="center"><s:property value="comTitle" /></td>
										<td align="center"><s:property value="toComDept" /></td>
										<td align="center"><s:property value="toComName" /></td>
										<td align="center"><s:date name="comTime"
												format="yyyy-MM-dd HH:mm" /></td>
										<td align="center"><s:property
												value="#complainStateMap[state]" /></td>
										<td align="center"><s:if test="state!=2">
												<a href="javascript:doDeal('<s:property value='comId'/>')">受理</a>
											</s:if></td>
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