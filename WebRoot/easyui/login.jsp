<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>员工管理登录</title>
    <base href="<%=basePath%>" />
    <jsp:include page="inc.jsp"></jsp:include>
    <script language="JavaScript">   
    if (window != top)   
    top.location.href = location.href;   
    </script>  
  </head>
  
  <body>
    <div id="loginDialog"  buttons="#dlg-buttons" style="padding:15px; overflow:hidden">
      <form id="loginForm" method="post">
      <table class="common-table">
        <tr>
          <th>用户名：</th>
          <td>
            <input type="text" class="easyui-validatebox txt" required="true" name="userName"/> 
          </td>
        </tr>
        <tr>
          <th>密 &nbsp;码：</th>
          <td>
            <input type="password" class="easyui-validatebox txt" required="true" name="password"/>
          </td>
        </tr>
     </table>
     </form>
      <div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="login()" style="width: 90px">登陆</a>
			<a href="javascript:void(0)" class="easyui-linkbutton c5"
				iconCls="icon-cancel" onclick="cancel()"
				style="width: 90px">取消</a>
		</div>
    </div>
  </body>
  <script type="text/javascript" src="easyui/js/login.js"></script>
</html>