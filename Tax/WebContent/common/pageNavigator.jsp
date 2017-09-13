<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
	<s:if test="pageResult.totalPage>0">
		<div class="c_pate" style="margin-top: 5px;">
			<table width="100%" class="pageDown" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right">总共<s:property value="pageResult.totalNum" />条记录，当前第<s:property
							value="pageResult.pageNum" /> 页，共 <s:property
							value="pageResult.totalPage" /> 页 &nbsp;&nbsp; <s:if
							test="pageResult.pageNum!=1">
							<a
								href="javascript:doGoPage('<s:property value="pageResult.pageNum-1"/>')">上一页</a>&nbsp;&nbsp;<a
								href="#">
						</s:if> <s:if test="pageResult.pageNum!=pageResult.totalPage">
							<a
								href="javascript:doGoPage('<s:property value="pageResult.pageNum+1"/>')">下一页</a>&nbsp;&nbsp;<a
								href="#">
						</s:if> 到&nbsp;<input type="text" style="width: 30px;"
						onkeypress="if(event.keyCode == 13){doGoPage(this.value,<s:property value="pageResult.totalPage"/>);}"
						min="1" max="" value="<s:property value='pageResult.pageNum'/>" />
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
	</s:if>
</body>
</html>