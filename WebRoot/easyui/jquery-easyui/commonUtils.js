///*
// * 自定义全局变量
// */
//var $_var_buttons;
//
///*
// * 自定义的validatebox验证规则
// */
//$.extend($.fn.validatebox.defaults.rules, {
//	number : {
//		validator : function(value, param) {
//			if (value != null) {
//				return $.isNumeric(value);
//			} else {
//				return true;
//			}
//		},
//		message : '此处应输入数字'
//	},
//	idCard : {
//		validator : function(value, param) {
//			if (value != null) {
//				var result = (/^\d{17}([0-9]|X)$/).test(value);
//				return result;
//			} else {
//				return true;
//			}
//		},
//		message : '身份证号格式错误'
//	},
//	checkRePwd : {
//		validator : function(value, param) {
//			var firstPwd = $(param[0]).val();
//			return value == firstPwd;
//		},
//		message : '两次密码输入不一致'
//	},
//	checkStaffAccountExists : {
//		validator : function(value, param){
//			if ($.trim(value) != ""){
//				var result;
//				$.ajax({
//					async : false,
//					url : 'userAjaxAction!queryUserExistsByStaffId.do',
//					data : 'id=' + $(param[1]).combobox('getValue'),
//					success : function(r){
//						r = $.parseJSON(r);
//						if (r && r.exists == false){
//							result = true;
//						} else {
//							result = false;
//						}						
//					}
//				});
//				return result;
//			} else {
//				return true;
//			}
//		},
//		message : '该员工已拥有登录账户'
//	},
//	checkUserNameExists : {
//		validator : function(value, param){
//			if (param.length == 3 && value == $.trim($(param[2]).val())){
//				return true;
//			}
//			var result;
//			$.ajax({
//				async : false,
//				url : param[0] + 'userAjaxAction!queryUserNameExists.do',
//				data : param[1] + "=" + value,
//				success : function(r){
//					if (r.exists){
//						result = false;
//					} else {
//						result = true;
//					}
//				}
//			});
//			return result;
//		},
//		message : '该用户名已存在'
//	}
//});
//
///*
// * 覆盖了panel的onBeforeDestroy方法。
// * destroy一个panel时，若存在iframe元素，则删除这些iframe
// */
//$.fn.panel.defaults = $.extend( {}, $.fn.panel.defaults, {
//	onBeforeDestroy : function() {
//		var frame = $('iframe', this);
//		if (frame.length > 0) {
//			frame[0].contentWindow.document.write('');
//			frame[0].contentWindow.close();
//			frame.remove();
//			if ($.browser.msie) {
//				CollectGarbage();
//			}
//		}
//	}
//});
//
////对Date的扩展，将 Date 转化为指定格式的String   
////月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
////年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
////例子：   
////(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
////(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
////author: meizz   
//Date.prototype.Format = function(fmt) {
//	var o = {
//	 "M+" : this.getMonth()+1,                 //月份   
//	 "d+" : this.getDate(),                    //日   
//	 "h+" : this.getHours(),                   //小时   
//	 "m+" : this.getMinutes(),                 //分   
//	 "s+" : this.getSeconds(),                 //秒   
//	 "q+" : Math.floor((this.getMonth()+3)/3), //季度   
//	 "S"  : this.getMilliseconds()             //毫秒   
//	};
//	if(/(y+)/.test(fmt))
//	 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
//	for(var k in o)
//	 if(new RegExp("("+ k +")").test(fmt))
//	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
//	return fmt;
//}
//
///*
// * 修改系统样式方法
// */
//function changeStyle(newStyleName){
//	if (newStyleName == null || newStyleName == ""){
//		var styleName = $.cookies.get("styleName");
//		if (styleName == null || styleName == ""){
//			newStyleName = "default";
//		} else {
//			newStyleName = styleName;
//		}
//	}
//	var styleHref = $("#themeStyle").attr("href");
//	var styleHrefFront = styleHref.substring(0, styleHref.indexOf("themes/") + 7);
//	var styleHrefBehind = "/easyui.css";
//	var styleHref = styleHrefFront + newStyleName + styleHrefBehind;
//	$("#themeStyle").attr("href", styleHref);
//	var $iframe = $('iframe');
//	if ($iframe.length > 0) {
//		for ( var i = 0; i < $iframe.length; i++) {
//			var ifr = $iframe[i];
//			$(ifr).contents().find('#themeStyle').attr('href', styleHref);
//		}
//	}
//	$.cookies.set("styleName", newStyleName);
//}

function loadingMask(){
	var element = "<div id='loginMaskDialog' closable='false' style='width:220px; height: 70px; text-align:center; " +
			"padding:10px;'><div style='float:left;'><img src='easyui/images/loading.gif'/></div><div " +
			"style='padding-top:10px;'>数据传输中，请稍候...</div></div>";
	$("body").append(element);
	$("#loginMaskDialog").append("");
	$("#loginMaskDialog").dialog({
		modal : true,
		title : '',
		onClose : function(){
			$(this).dialog('destroy');
		}
	});
}

//function getPermission(state, permission){
//	//定义临时变量
//	var tmp = 1;
//	//位移运算
//	tmp = tmp << permission;
//	//临时变量与ACL状态值做与操作
//	tmp &= state;
//	//如果操作结束后的结果不为0,代表拥有该权限
//	if (tmp != 0) {
//		return true;
//	}
//	return false;
//}