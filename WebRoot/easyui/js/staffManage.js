var operation;
var rowCopy;
$(function() {
	$('#dlg').hide();
	
    var staffTable = $("#staffTable").datagrid({
        url: 'StaffServlet',
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        idField: 'id',
        columns: [[{
            field: 'id',
            checkbox: true
        },
        {
            title: '名字',
            field: 'fName',
            sortable:true
        },
        {
            title: '姓',
            field: 'lName',
        },
        {
            title: '性别',
            field: 'gender',
            sortable:true,
            formatter: function(value, row, index) {
                if (value == 1) {
                    return "男";
                } else {
                    return "女";
                }
            }
        },
        {
            title: '出生日期',
            field: 'birthday'
        },
        {
            title: '联系电话',
            field: 'telephone'
        },
        {
            title: '邮箱',
            field: 'email'
        },
        {
            title: '省',
            field: 'provinceStr'
        },
        {
            title: '市',
            field: 'cityStr'
        },
        {
            title: '县',
            field: 'countyStr'
        },
        {
            title: '地址',
            field: 'address'
        },
        {
            title: '所属部门',
            field: 'departmentStr'
        }]],
        toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						createStaff();
					}
				},{
					id:'btndelete',
					text:'删除',
					iconCls:'icon-cancel',
					handler:function(){
						deleteStaff();
					}
					
				},{
					id:'btnedit',
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						updateStaff();
					}
				}]
			});
    
//to initialize the comboboxes for province/city/county/department.
    $('#province').combobox({
		    valueField:'id',    
		    textField:'provinceName',
		    panelHeight:'auto',
            required:true,
            editable:false,
            onShowPanel : function(){
		    var s=$(this).combobox('getData')
		    if(s.length==0){  
		       $(this).combobox('options').url= "ProvinceServlet";
		       $(this).combobox('reload');
		       }
		    },
		   	onSelect:function(record){
				 var url = 'CityServlet?province='+record.id;
				 $('#city').combobox('clear');
		         $('#city').combobox('reload', url);    
				 
		   	}
		});
		
		$('#city').combobox({
		    valueField:'id',    
		    textField:'cityName',
		    panelHeight:'auto',
            required:true,
            editable:false,
		    value:'--请选择--',
		   	onSelect:function(record){
				 var url = 'CountyServlet?city='+record.id;  
				 $('#county').combobox('clear');
		         $('#county').combobox('reload', url);    
		   	},
		});
		
		$('#county').combobox({
		    valueField:'id',    
		    textField:'countyName',
		    panelHeight:'auto',
            required:true,
            editable:false,
			});
		
		$('#department').combobox({
		    valueField:'deptNo',    
		    textField:'dName',
		    panelHeight:'auto',
            required:true,
            editable:false,
            onShowPanel : function(){
		    var s=$(this).combobox('getData')
		    if(s.length==0){  
		       $(this).combobox('options').url= "DepartmentServlet?operation=getDepartments";
		       $(this).combobox('reload');
		       }
		    }
		});
		
//radio button for gender.
		var resJsonObj
		$.getJSON('ConstantServlet', function(json) {
		resJsonObj=json.gender;
		var tagStr = "";
		for(var i=0;i<resJsonObj.length;i++){
			var t = resJsonObj[i];
			tagStr+='<input type="radio" name="gender" value="'+t.value+'"/>'+t.title;
		}
		$("#gender").html('<label>'+'性别:'+'</label>'+tagStr);
		 });
		
    });

//add staff.
function createStaff() {
	operation = "add";
	$('#staffDialog').dialog('open').dialog('setTitle', '添加');

	$('#fm').form('clear');
	$("input[name='gender'][value='1']").prop("checked","checked"); 
}

//delete the selected row, one or more.
function deleteStaff(){
	var rows = $('#staffTable').datagrid('getSelections');
	
	if (rows.length > 0){

		$.messager.confirm('确认',

		'确定删除?', function(r) {

			if (r) {
				    var ids = [];
                    for (var i = 0; i <  rows.length; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
				$.post('StaffServlet?operation=delete&id=', {id:ids}, function(data) {
					if (data.success) {
						
						$.messager.show( { 

									title : '成功',

									msg : data.msg

								});

						$('#staffTable').datagrid('reload'); 

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
function updateStaff(){
	operation = "modify";
	rowCopy = $('#staffTable').datagrid('getSelected');

	if (rowCopy)
	{

		$('#staffDialog').dialog('open').dialog('setTitle', '修改');

		$('#fm').form('load', rowCopy);
		
		//初始化下拉列表值
		$("#province").combobox('setText',rowCopy.provinceStr);
		$("#city").combobox('setText',rowCopy.cityStr);
		$("#county").combobox('setText',rowCopy.countyStr);
		$("#department").combobox('setText',rowCopy.departmentStr);
	}
	else
	{
		jQuery.messager.alert('警告', '请先选择一条记录!');
		 
		return false;
	}

}

//search by name.
function doSearch(){
	$('#staffTable').datagrid('load',
	{
		"operation" : "search",
		"lName" : $("#s_lName").val(),
		"fName" : $("#s_fName").val(),
	});
}

//clear the search input and reload all the data.
function clearSearch() {
    $("input[name='s_fName']").val('');
    $("input[name='s_lName']").val('');
    doSearch(); 
}

//save staff when add or modify.
function saveStaff()
{
	var fName = $("input[name='fName']").val();
	var lName = $("input[name='lName']").val();
	var gender = $("input[name='gender']").val();
    var birthday = $("input[name='birthday']").val();
    var email = $("input[name='email']").val();
    var telephone = $("input[name='telephone']").val();
    var province = $("input[name='province']").val();
    var city = $("input[name='city']").val();
    var county = $("input[name='county']").val();
	var address = $("input[name='address']").val();
	var department = $("input[name='deptNo']").val();
	
	var data =
	{
		"fName" : fName,
		"lName" : lName,
		"gender" : gender,
		"birthday" : birthday,
		"telephone" : telephone,
		"email" : email,
		"province" : province,
		"city" : city,
		"county" : county,
		"address" : address,
		"department" : department
	};
	var url = "";
    if (operation == "add") {
        url += 'StaffServlet?operation=add';
    } else {
        url += 'StaffServlet?operation=modify&id=' + rowCopy.id;
    }
//     $.ajax(
//	  {
//		url: url,
//		type : 'POST',
//		data : data,
//		dataType : "json",
//        contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
//		success : function(data)
//		{
//			if (data.result)
//			{
//				 $.messager.alert('提示信息', data.msg, 'info');
//                 $('#staffDialog').dialog('close'); 
//		         $('#staffTable').datagrid('reload');
//			}
//			else
//			{
////				$.messager.alert('提示信息', data.msg, 'error'); //后台验证错误消息
//			}
//		},
//	});
    $('#fm').form('submit', {
	url: url,
	onSubmit: function(){
		var isValid = $("#fm").form('validate');
		if (!isValid){
			$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		}
		return isValid;	// 返回false终止表单提交
	},
	success: function(data){
		$.messager.progress('close');	// 如果提交成功则隐藏进度条
		$('#staffDialog').dialog('close'); 
		$('#staffTable').datagrid('reload');
	}
});
}