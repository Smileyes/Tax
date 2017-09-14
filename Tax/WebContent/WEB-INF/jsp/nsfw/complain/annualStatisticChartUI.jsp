<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>年度投诉统计图</title>
</head>
<script type="text/javascript"
	src="${basePath }js/fusionchart/js/fusioncharts.js"></script>
<script type="text/javascript"
	src="${basePath }js/fusionchart/js/themes/fusioncharts.theme.ocean.js?cacheBust=56"></script>
<body>
	<br>
	<s:select id="year" list="#request.yearList"
		onchange="doAnnualStatistic()" headerValue="#request.year"></s:select>
	<br>
	<div id="chartContainer"  align="center"></div>
</body>
<script type="text/javascript">
	$(doAnnualStatistic());
	//更新折线图
	function doAnnualStatistic() {
		//获取选中年份
		var year = $("#year option:selected").val();
		$.ajax({
			"url" : "complain_annualStatisticChart.action",
			"data" : {
				"year" : year
			},
			method : "post",
			dataType : "json",
			success : function(data) {
				if (data.msg == "success") {
				    var fusioncharts = new FusionCharts({
				        type: 'line',
				        renderAt: 'chartContainer',
				        width: '600',
				        height: '400',
				        dataFormat: 'json',
				        dataSource: {
				            "chart": {
				                "caption": year+"年投诉数量统计折线图",
				                "numberprefix": "",
				                "bgcolor": "FFFFFF",
				                "showalternatehgridcolor": "0",
				                "plotbordercolor": "008ee4",
				                "plotborderthickness": "3",
				                "showvalues": "0",
				                "divlinecolor": "CCCCCC",
				                "showcanvasborder": "0",
				                "tooltipbgcolor": "00396d",
				                "tooltipcolor": "FFFFFF",
				                "tooltipbordercolor": "00396d",
				                "numdivlines": "2",
				                "yaxisvaluespadding": "20",
				                "anchorbgcolor": "008ee4",
				                "anchorborderthickness": "0",
				                "showshadow": "0",
				                "anchorradius": "4",
				                "chartrightmargin": "25",
				                "canvasborderalpha": "0",
				                "showborder": "0"
				            },
				            "data": data.result				      
				        }
				    });
				    fusioncharts.render();
					
				} else {
					alert("更新失败");
				}
			},
			error : function() {
				alert("更新失败");
			}
		});

	}
</script>
</html>
