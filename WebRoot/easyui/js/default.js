
//tab for displaying the staff list.
function addTab(title, url){ 
     if ($('#tt').tabs('exists', title)){    
        $('#tt').tabs('select', title);    
    } else {     
        $('#tt').tabs('add',{    
            title:title,    
            href:url,
            closable:true
        });    
     }    
}
//打开新窗口跳转到按部门统计员工数量页面。
function countDeptStaff(){
	window.open('echarts/echartsForDeptStaff.jsp','','height:auto,width:auto,scrollbars=yes,status=yes');//打开新窗口跳转
//	window.location.href="easyui/echartsForDeptStaff.jsp"; //地址跳转
}

//打开新窗口跳转到男女员工所占比例统计页面。
function countStaffGender(){
	window.open('echarts/echartsForStaffGender.jsp','','height:auto,width:auto,scrollbars=yes,status=yes');//打开新窗口跳转
}


//logout
function logout() {
    $.messager.confirm('确认', '您确定要退出本系统吗？',
    function(r) {
        if (r) {
            $.post('LoginServlet?operation=toLogout',
            function(data) { 
                if (data[0].success) {
                    window.location.href = 'LoginServlet?operation=toLogin';
                }
            }, 'json');
        }
    });
}

//change password
function changePassword() {
    $("body").append("<div id='changePasswordDialog' style='padding:10px;'></div>");
    $("#changePasswordDialog").dialog({
        modal: true,
        href: 'easyui/changePassword.jsp',
        title: '修改密码',
        width: 300,
        height: 200,
        onClose: function() {
            $("this").dialog('destroy');
        }
    });
}

//change password
function submit() {
	var id = $("input[name='id']").val();
	var userName = $("input[name='userName']").val();
	var oldPassword = $("input[name='oldPassword']").val();
	var password = $("input[name='password']").val();
	var con_password = $("input[name='con_password']").val();
	var data =
	{
		"id" : id,
		"userName" : userName,
		"oldPassword" : oldPassword,
		"password" : password,
		"con_password" : con_password,
	};
    $.ajax(
	  {
		url: "AdminServlet?operation=toChangePassword",
		type : 'POST',
		data : data,
		dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
		success : function(data)
		{
			if (data && data.result) {
                $.messager.alert('提示信息', data.msg, 'info');
                $('#changePasswordDialog').dialog('close'); 
            } else {
                $.messager.alert('提示信息', data.msg, 'error');
            }
		},
	});
}

//change password
function reset() {
	$("input[name='oldPassword']").val('');
    $("input[name='password']").val('');
    $("input[name='con_password']").val('');
}