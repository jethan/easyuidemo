<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<head>
</head>
<body>
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north', title:'查询', border:false, collapsed:false, iconCls:'icon-search'" 
			style="height:70px;padding:10px;">
				<input type="text" id="userName" name="userName" placeholder="名"/>
				<a class="easyui-linkbutton easyui-tooltip" title="查询 " iconCls="icon-search" plain="true" onclick="doSearch()">查询</a>
			    <a class="easyui-linkbutton easyui-tooltip" title="清空" data-options="iconCls:'icon-undo'" plain="true" onclick="clearSearch()">清空</a>
			</div>
			<div data-options="region:'center', border:false" >
				<table id="UserTable"></table>
			</div>
		 </div>
    
		<div id="userDialog" class="easyui-dialog" buttons="#dlg-buttons1"
			style="width: 395px; height: 395px; padding: 10px 20px; overflow:hidden;"
			data-options="modal:true,cache:false,closed:true,resizable: true,minimizable: true,maximizable: true,collapsible: true">
			<div class="ftitle">
				用户信息
			</div>
			<form id="userForm" method="post" style="width: 400px; height: 360px; padding: 10px 20px;display: block;">
				<div class="fitem">
					<label>
						用户名:
					</label>
					<input name="username" class="easyui-validatebox" required="true"/>
				</div>
				<div class="fitem">
					<label>
						密码:
					</label>
					<input name="password" class="easyui-validatebox" required="true" type="password" />
				</div>
		</form>
	    </div>

		<div id="dlg-buttons1">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton c5"
				iconCls="icon-cancel" onclick="javascript:$('#userDialog').dialog('close')"
				style="width: 90px">取消</a>
		</div>
		
  <div id="editUserDialog" class="easyui-dialog" buttons="#dlg-buttons2"
	style="width: 395px; height: 395px; padding: 10px 20px; overflow:hidden;"
	data-options="modal:true,cache:false,closed:true,resizable: true,minimizable: true,maximizable: true,collapsible: true">
  <form id="editUserInfoForm" method="post">
    <input type="hidden" name="dataObject.id" />
    <input type="hidden" id="userOldName" name="oldName" />
  	<table class="common-table">
  	  <tr>
  	    <th>用户名：</th>
  	    <td>
  	      <input type="text" class="easyui-validatebox txt" delay="300" required="true" name="userName"/>
  	    </td>
  	  </tr>
  	</table>
  </form>
  </div>
  
  <div id="dlg-buttons2">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton c5"
				iconCls="icon-cancel" onclick="javascript:$('#editUserDialog').dialog('close')"
				style="width: 90px">取消</a>
		</div>
		
		<script type="text/javascript" src="easyui/js/userManage.js">
</script>
</body>