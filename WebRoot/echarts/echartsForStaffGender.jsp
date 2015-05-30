<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>统计男女员工所占比例</title>
    
	<meta content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="easyui/jquery-easyui/jquery-2.1.1.min.js">
    </script>
    <script type="text/javascript" src="echarts/js/echarts-all.js">
    </script>
  </head>
  
  <body>
     <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="line" style="height:400px"></div>
  </body>
  <script type="text/javascript" src="echarts/js/echartsForStaffGender.js">
  </script>
</html>
