var DepartmentTable;
var operation;
var rowCopy;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function() {
    DepartmentTable = $("#DepartmentTable").datagrid({
        url: 'DepartmentServlet',
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        idField: 'deptNo',
        columns: [[{
            field: 'deptNo',
            checkbox: true
        },
        {
            title: '部门名称',
            field: 'dName',
            editor: { type: 'text', options: { required: true } },
            sortable:true
        },
        {
            title: '部门简介',
            field: 'description',
            editor:'text',
        }]],
        toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						createDepartment();
					}
				},{
					id:'btndelete',
					text:'删除',
					iconCls:'icon-cancel',
					handler:function(){
						deleteDepartment();
					}
					
				},{
					id:'btnedit',
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						updateDepartment();
					}
				},{
					id:'btnsave',
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
					DepartmentTable.datagrid("endEdit", editRow);
					}
				},{
					id:'btnundo',
					text:'撤销',
					iconCls:'icon-undo',
					handler:function(){
					clearForm();
					}
				}],
				onAfterEdit: function (rowIndex, rowData, changes) {
	              editRow = undefined;
	              var rows = DepartmentTable.datagrid('getChanges');
	              var data =
					  {
						"dName" : rows[0].dName,
						"description" : rows[0].description,
					  };
				  $.ajax(
					  {
						url: 'DepartmentServlet?operation=modify&id=' + rows[0].deptNo,
						type : 'POST',
						data : data,
						dataType : "json",
				        contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
						success : function(data)
						{
							if (data.result)
							{
								 $.messager.alert('提示信息', data.msg, 'info');
				                 $('#departmentDialog').dialog('close'); 
						         $('#DepartmentTable').datagrid('reload');
							}
							else
							{
								$.messager.alert('提示信息', data.msg, 'error');
							}
						},
					});
		        },
		        onDblClickRow:function (rowIndex, rowData) {
		            if (editRow != undefined) {
		                DepartmentTable.datagrid('endEdit', editRow);
		            }
		 
		            if (editRow == undefined) {
		                DepartmentTable.datagrid('beginEdit', rowIndex);
		                editRow = rowIndex;
		            }
		        }
			});
    });

//add department.
function createDepartment() {
	operation = "add";
	$('#departmentDialog').dialog('open').dialog('setTitle', '添加');

	$('#departmentForm').form('clear');
}

//delete the selected row, one or more.
function deleteDepartment(){
	var rows = $('#DepartmentTable').datagrid('getSelections');
	
	if (rows.length > 0){

		$.messager.confirm('确认',

		'确定删除?', function(r) {

			if (r) {
				    var ids = [];
                    for (var i = 0; i <  rows.length; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
				$.post('DepartmentServlet?operation=delete&id=', {id:ids}, function(data) {
					if (data.success) {
						
						$.messager.show( { 

									title : '成功',

									msg : data.msg

								});

						$('#DepartmentTable').datagrid('reload'); 

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
function updateDepartment(){
	operation = "modify";
	rowCopy = $('#DepartmentTable').datagrid('getSelected');
	if (rowCopy)
	{

		$('#departmentDialog').dialog('open').dialog('setTitle', '修改');

		$('#departmentForm').form('load', rowCopy);
	}
	else
	{
		jQuery.messager.alert('警告', '请先选择一条记录!');
		 
		return false;
	}
	$("#department").combobox('setText',rowCopy.dName);
	
//行编辑
     var rows = DepartmentTable.datagrid("getSelections");
     //如果只选择了一行则可以进行修改，否则不操作
     if (rowCopy.length == 1) {
         //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
         if (editRow != undefined) {
             DepartmentTable.datagrid("endEdit", editRow);
         }
         //当无编辑行时
         if (editRow == undefined) {
             //获取到当前选择行的下标
             var index = DepartmentTable.datagrid("getRowIndex", rows[0]);
             //开启编辑
             DepartmentTable.datagrid("beginEdit", index);
             //把当前开启编辑的行赋值给全局变量editRow
             editRow = index;
             //当开启了当前选择行的编辑状态之后，
             //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
             DepartmentTable.datagrid("unselectAll");
         }
     }
}

//search by name.
function doSearch(){
	$('#DepartmentTable').datagrid('load',
	{
		"operation" : "search",
		"dName" : $("#department").val(),
	});
}

//clear the search input and reload all the data.
function clearSearch() {
    $("input[name='dName']").val('');
    doSearch(); 
}

//save department when add or modify.
function saveDepartment()
{
	var dName = $("input[name='department']").val();
	var description = $("textarea[name='description']").val();
	var data =
	{
		"dName" : dName,
		"description" : description,
	};
	var url = "";
    if (operation == "add") {
        url += 'DepartmentServlet?operation=add';
    } else {
        url += 'DepartmentServlet?operation=modify&id=' + rowCopy.deptNo;
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
                 $('#departmentDialog').dialog('close'); 
		         $('#DepartmentTable').datagrid('reload');
			}
			else
			{
				$.messager.alert('提示信息', data.msg, 'error');
			}
		},
	});
}

function clearForm() {
    editRow = undefined;
    DepartmentTable.datagrid("rejectChanges");
    DepartmentTable.datagrid("unselectAll");
}