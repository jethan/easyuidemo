<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
	<head>
		<base href="<%=basePath%>">
	
	    <meta content="text/html;charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>

		<link rel="stylesheet" type="text/css" href="easyui/style/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/style/icon.css">
		<link rel="stylesheet" type="text/css" href="easyui/style/demo.css">
		<link rel="stylesheet" type="text/css" href="easyui/style/color.css">


		<script type="text/javascript" src="easyui/jquery-easyui/jquery-2.1.1.min.js">
</script>
		<script type="text/javascript" src="easyui/jquery-easyui/jquery.easyui.min.js">
</script>
		<script type="text/javascript" src="easyui/jquery-easyui/easyui-lang-zh_CN.js">
</script>
        <script type="text/javascript" src="easyui/js/default.js">
</script>
	</head>
	
  <body class="easyui-layout" >
    <div region="north" border="true" split="true" style="overflow: hidden; height: 80px;">
        <div class="top-bg">
        <%@ include file="default/north.jsp"%>
        </div>
    </div>
    <div region="west" split="true" title="导航菜单" iconCls="icon-home" style="width: 200px;">
        <%@ include file="default/west.jsp"%>
    </div>
    <div id="mainPanle" region="center" title="主页" iconCls="icon-ok" style="overflow: hidden;">
        <%@ include file="default/center.jsp"%>
    </div>
    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">
    <br/>
        <%@ include file="default/sourth.jsp"%>
    </div>
  </body>
  
</html>
