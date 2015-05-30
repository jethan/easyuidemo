<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<head>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div
			data-options="region:'north', title:'查询', border:false, collapsed:false, iconCls:'icon-search'"
			style="height: 70px; padding: 10px;">
			<input type="text" id="dName" name="dName" placeholder="部门名称" />
			<a class="easyui-linkbutton easyui-tooltip" title="查询 "
				iconCls="icon-search" plain="true" onclick="doSearch()">查询</a>
			<a class="easyui-linkbutton easyui-tooltip" title="清空"
				data-options="iconCls:'icon-undo'" plain="true"
				onclick="clearSearch()">清空</a>
		</div>
		<div data-options="region:'center', border:false">
			<table id="DepartmentTable"></table>
		</div>
	</div>

	<div id="departmentDialog" class="easyui-dialog" buttons="#dlg-buttons"
		style="width: 395px; height: 395px; padding: 10px 20px; overflow: hidden;"
		data-options="modal:true,cache:false,closed:true,resizable: true,minimizable: true,maximizable: true,collapsible: true">
		<div class="ftitle">
			部门信息
		</div>
		<form id="departmentForm" method="post"
			style="width: 400px; height: 360px; padding: 10px 20px; display: block;">
			<div class="fitem">
				<label>
					部门名称:
				</label>
				<input id="department" name="department" class="easyui-validatebox" required="true" />
			</div>
			<div class="fitem">
				<label>
					部门简介:
				</label>
				<textarea name="description" id="description" cols="30" rows="4" /> 
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveDepartment()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton c5"
			iconCls="icon-cancel"
			onclick="javascript:$('#departmentDialog').dialog('close')"
			style="width: 90px">取消</a>
	</div>
	<script type="text/javascript" src="easyui/js/departmentManage.js">
</script>
	<script type="text/javascript" src="easyui/js/validatebox.js">
</script>
</body>