
var loginDialog;
var loginForm;
$(function() {
    loginForm = $("#loginForm").form({});
    loginDialog = $("#loginDialog").dialog({
        modal: true,
        closed: false,
        collapsible: true,
        maximizable: true,
        resizable: true,
        closable: false,
        title: '管理员登录',
        width: 300,
        height: 180,
    });
});

function cancel() {
    $("input[name='userName']").val('');
    $("input[name='password']").val('');
}

function login() {
    var userName = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
	var data =
	{
		"userName" : userName,
		"password" : password,
	};
    $.ajax(
	  {
		url: "AdminServlet?operation=toLogin",
		type : 'POST',
		data : data,
		dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
		success : function(data)
		{
			if (data && data.result) {
                window.top.location.href = 'easyui/default.jsp';
            } else {
                $.messager.alert('提示信息', data.msg, 'error');
            }
		},
	});
}