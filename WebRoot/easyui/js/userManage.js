var UserTable;
var operation;
var rowCopy;
$(function() {
    UserTable = $("#UserTable").datagrid({
        url: 'AdminServlet',
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        idField: 'userNo',
        columns: [[{
            field: 'userNo',
            checkbox: true
        },
        {
            title: '用户名',
            field: 'userName',
            sortable:true
        },
        {
            title: '创建时间',
            field: 'createTime',
        }]],
        toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
        	            $('#btnsave').linkbutton('enable');
						createUser();
					}
				},{
					id:'btndelete',
					text:'删除',
					iconCls:'icon-cancel',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						deleteUser();
					}
					
				},{
					id:'btnedit',
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						updateUser();
					}
				}]
			});
    });

//add user.
function createUser() {
	operation = "add";
	$('#userDialog').dialog('open').dialog('setTitle', '添加');

	$('#userForm').form('clear');
}

//delete the selected row, one or more.
function deleteUser(){
	var rows = $('#UserTable').datagrid('getSelections');
	
	if (rows.length > 0){

		$.messager.confirm('确认',

		'确定删除?', function(r) {

			if (r) {
				    var ids = [];
                    for (var i = 0; i <  rows.length; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
				$.post('AdminServlet?operation=delete&id=', {id:ids}, function(data) {
					if (data.success) {
						
						$.messager.show( { 

									title : '成功',

									msg : data.msg

								});

						$('#UserTable').datagrid('reload'); 

					} else {

						$.messager.show( { 

									title : '失败',

									msg : data.msg

								});
					}

				}, 'json');

			}

		});

	}
	else
	{
		jQuery.messager.alert('警告', '请先选择一条记录!');
		
	    return false;  
	}

}

//modify the selected staff.
function updateUser(){
	operation = "modify";
	rowCopy = $('#UserTable').datagrid('getSelected');

	if (rowCopy)
	{

		$('#editUserDialog').dialog('open').dialog('setTitle', '修改');

		$('#editUserInfoForm').form('load', rowCopy);
		
		url = 'AdminServlet?operation=modify&id=' + rowCopy.id;
	}
	else
	{
		jQuery.messager.alert('警告', '请先选择一条记录!');
		 
		return false;
	}

}

//search by name.
function doSearch(){
	$('#UserTable').datagrid('load',
	{
		"operation" : "search",
		"userName" : $("#userName").val(),
	});
}

//clear the search input and reload all the data.
function clearSearch() {
    $("input[name='userName']").val('');
    doSearch(); 
}

//save user when add or modify.
function saveUser()
{
	var username = $("input[name='username']").val();
	var password = $("input[name='password']").val();
	
	var data =
	{
		"username" : username,
		"password" : password,
	};
	var url = "";
    if (operation == "add") {
        url += 'AdminServlet?operation=add';
    } else {
        url += 'AdminServlet?operation=modify&id=' + rowCopy.id;
    }
     $.ajax(
	  {
		url: url,
		type : 'POST',
		data : data,
		dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
		success : function(data)
		{
			if (data.result)
			{
				 $.messager.alert('提示信息', data.msg, 'info');
                 $('#userDialog').dialog('close'); 
		         $('#UserTable').datagrid('reload');
			}
			else
			{
				$.messager.alert('提示信息', data.msg, 'error');
			}
		},
	});
}
