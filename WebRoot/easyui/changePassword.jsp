<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.demo.model.Admin" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Admin admin = (Admin) session.getAttribute("loginUser");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<base href="<%=basePath%>" />
	<jsp:include  page="inc.jsp"></jsp:include>
  </head>
  
  <body>
    <form id="changePasswordForm" buttons="#dlg-buttons" method="post">
    <input type="hidden" name="id" value="<%=admin.getUserNo() %>" />
    <table class="common-table">
  	  <tr>
  	    <th>用&nbsp;户&nbsp;名：</th>
  	    <td>
  	    <input type="text" name="userName" readonly="true" value="<%=admin.getUserName() %>" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <th>原&nbsp;密&nbsp;码：</th>
  	    <td>
  	      <input type="password" class="easyui-validatebox txt" required="true" name="oldPassword" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <th>密&nbsp; &nbsp; 码：</th>
  	    <td>
  	      <input type="password" class="easyui-validatebox txt" required="true" name="password" id="newUserPwd" />
  	    </td>
  	  </tr>
  	  <tr>
  	    <th>确认密码：</th>
  	    <td>
  	      <input type="password" class="easyui-validatebox txt" required="true" name="con_password" validType="checkRePwd['#newUserPwd']" />
  	    </td>
  	  </tr>
  	</table>
  	</form>
  	 <div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="submit()" style="width: 90px">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton c5"
				iconCls="icon-cancel" onclick="reset()" style="width: 90px">重置</a>
	 </div>
  </body>
</html>
