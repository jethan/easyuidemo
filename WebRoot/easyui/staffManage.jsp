<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<head>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div
			data-options="region:'north', title:'查询', border:false, collapsed:false, iconCls:'icon-search'"
			style="height: 70px; padding: 10px;">
			<input type="text" id="s_lName" name="s_lName" placeholder="姓" />
			<input type="text" id="s_fName" name="s_fName" placeholder="名" />
			<a class="easyui-linkbutton easyui-tooltip" title="查询 "
				iconCls="icon-search" plain="true" onclick="doSearch()">查询</a>
			<a class="easyui-linkbutton easyui-tooltip" title="清空"
				data-options="iconCls:'icon-undo'" plain="true"
				onclick="clearSearch()">清空</a>
		</div>
		<div data-options="region:'center', border:false">
			<table id="staffTable"></table>
		</div>
	</div>

	<div id="staffDialog" class="easyui-dialog" buttons="#dlg-buttons"
		style="width: 480px; height: 400px; padding: 10px 20px; overflow: hidden;"
		data-options="modal:true,cache:false,closed:true,resizable: true,minimizable: true,maximizable: true,collapsible: true">
		<div class="ftitle">
			员工信息
		</div>
		<form id="fm" method="post"
			style="width: 400px; height: 350px; padding: 10px 20px; display: block;">
			<table>
				<tr>
					<td>
						<div class="fitem">
							<label>
								姓:
							</label>
							<input name="lName" class="easyui-validatebox"
								data-options="required:true,validType:'name'" />
						</div>
					</td>
					<td>
						<div class="fitem">
							<label>
								 名:
							</label>
							<input id="fName" name="fName" class="easyui-validatebox"
								data-options="required:true,validType:'name'" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>
								电话号码:
							</label>
							<input name="telephone" class="easyui-validatebox"
								data-options="validType:'mobileAndTel'" 
							placeholder="xxx-xxxxxxx" />
						</div>
					</td>
					<td>
						<div class="fitem">
							<label>
								邮箱:
							</label>
							<input name="email" class="easyui-validatebox"
								data-options="validType:'email'" placeholder="xx@xx.com" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>
								出生日期:
							</label>
							<input name="birthday" class="easyui-datebox"
								data-options="validType:'myDate'"></input>
						</div>
					</td>
					<td>
						<div class="fitem">
							<label>
								省:
							</label>
							<input type="text" id="province" name="province"
								style="width: 150px" class="easyui-validatebox">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>
								市:
							</label>
							<input type="text" id="city" name="city" style="width: 150px"
								class="easyui-validatebox">
						</div>
					</td>
					<td>
						<div class="fitem">
							<label>
								县:
							</label>
							<input type="text" id="county" name="county" style="width: 150px"
								class="easyui-validatebox" >
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>
								地址:
							</label>
							<input class="easyui-textbox" id="address" name="address" />
						</div>
					</td>
					<td>
						<div class="fitem">
							<label>
								所属部门:
							</label>
							<input type="text" id="department" name="deptNo"
								style="width: 150px" class="easyui-validatebox" >
						</div>
					</td>
				</tr>
				<tr>
					<td>
					<div id ="gender" class="gender">
                    </div>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveStaff()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton c5"
			iconCls="icon-cancel"
			onclick="javascript:$('#staffDialog').dialog('close')"
			style="width: 90px">取消</a>
	</div>

	<script type="text/javascript" src="easyui/js/staffManage.js">
</script>
	<script type="text/javascript" src="easyui/js/validatebox.js">
</script>
</body>