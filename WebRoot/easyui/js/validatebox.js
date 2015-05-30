//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules,{
					//验证汉字和字母
					name :
					{
						validator : function(value)
						{
							return /^(([A-Za-z]|[·]|[\u4E00-\u9FA5]){1,10})+$/
									.test(value);
						},
						message : '非法字符.'
					},

					CHS :
					{
						validator : function(value, param)
						{
							return /^[\u0391-\uFFE5]+$/.test(value);
						},
						message : '请输入汉字'
					},
					english :
					{// 验证英语
						validator : function(value)
						{
							return /^[A-Za-z]+$/i.test(value);
						},
						message : '请输入英文'
					},
					ip :
					{// 验证IP地址
						validator : function(value)
						{
							return /\d+\.\d+\.\d+\.\d+/.test(value);
						},
						message : 'IP地址格式不正确'
					},
					ZIP :
					{
						validator : function(value, param)
						{
							return /^[0-9]\d{5}$/.test(value);
						},
						message : '邮政编码不存在'
					},
					QQ :
					{
						validator : function(value, param)
						{
							return /^[1-9]\d{4,10}$/.test(value);
						},
						message : 'QQ号码不正确'
					},
					mobile :
					{
						validator : function(value, param)
						{
							return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/
									.test(value);
						},
						message : '手机号码不正确'
					},
					tel :
					{
						validator : function(value, param)
						{
							return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/
									.test(value);
						},
						message : '电话号码不正确'
					},
					mobileAndTel :
					{
						validator : function(value, param)
						{
							return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/
									.test(value);
						},
						message : '请正确输入电话号码'
					},
					number :
					{
						validator : function(value, param)
						{
							return /^[0-9]+.?[0-9]*$/.test(value);
						},
						message : '请输入数字'
					},
					money :
					{
						validator : function(value, param)
						{
							return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)
									.test(value);
						},
						message : '请输入正确的金额'

					},
					mone :
					{
						validator : function(value, param)
						{
							return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)
									.test(value);
						},
						message : '请输入整数或小数'

					},
					integer :
					{
						validator : function(value, param)
						{
							return /^[+]?[1-9]\d*$/.test(value);
						},
						message : '请输入最小为1的整数'
					},
					integ :
					{
						validator : function(value, param)
						{
							return /^[+]?[0-9]\d*$/.test(value);
						},
						message : '请输入整数'
					},
					range :
					{
						validator : function(value, param)
						{
							if (/^[1-9]\d*$/.test(value))
							{
								return value >= param[0] && value <= param[1]
							} else
							{
								return false;
							}
						},
						message : '输入的数字在{0}到{1}之间'
					},
					minLength :
					{
						validator : function(value, param)
						{
							return value.length >= param[0]
						},
						message : '至少输入{0}个字'
					},
					maxLength :
					{
						validator : function(value, param)
						{
							return value.length <= param[0]
						},
						message : '最多{0}个字'
					},
					//select即选择框的验证
					selectValid :
					{
						validator : function(value, param)
						{
							if (value == param[0])
							{
								return false;
							} else
							{
								return true;
							}
						},
						message : '请选择'
					},
					idCode :
					{
						validator : function(value, param)
						{
							return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
									.test(value);
						},
						message : '请输入正确的身份证号'
					},
					loginName :
					{
						validator : function(value, param)
						{
							return /^[\u0391-\uFFE5\w]+$/.test(value);
						},
						message : '登录名称只允许汉字、英文字母、数字及下划线。'
					},
					checkRePwd :
					{
						validator : function(value, param)
						{
							var firstPwd = $(param[0]).val();
							return value == firstPwd;
						},
						message : '两次密码输入不一致'
					},
					checkStaffAccountExists :
					{
						validator : function(value, param)
						{
							if ($.trim(value) != "")
							{
								var result;
								$.ajax(
								{
									async : false,
									url : '',
									data : 'id=' + $(param[1]).combobox(
											'getValue'),
									success : function(r)
									{
										r = $.parseJSON(r);
										if (r && r.exists == false)
										{
											result = true;
										} else
										{
											result = false;
										}
									}
								});
								return result;
							} else
							{
								return true;
							}
						},
						message : '该员工已拥有登录账户'
					},
					checkUserNameExists :
					{
						validator : function(value, param)
						{
							if (param.length == 3
									&& value == $.trim($(param[2]).val()))
							{
								return true;
							}
							var result;
							$.ajax(
							{
								async : false,
								url : param[0] + '',
								data : param[1] + "=" + value,
								success : function(r)
								{
									if (r.exists)
									{
										result = false;
									} else
									{
										result = true;
									}
								}
							});
							return result;
						},
						message : '该用户名已存在'
					},
					equalTo :
					{
						validator : function(value, param)
						{
							return value == $(param[0]).val();
						},
						message : '两次输入的字符不一至'
					},
					englishOrNum :
					{// 只能输入英文和数字
						validator : function(value)
						{
							return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
						},
						message : '请输入英文、数字、下划线或者空格'
					},
					decimal :
					{
						validator : function(value)
						{
							return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/
									.test(value);
						},
						message : '最多保留两位小数！'
					},
					ddPrice :
					{
						validator : function(value, param)
						{
							if (/^[1-9]\d*$/.test(value))
							{
								return value >= param[0] && value <= param[1];
							} else
							{
								return false;
							}
						},
						message : '请输入1到100之间正整数'
					},
					jretailUpperLimit :
					{
						validator : function(value, param)
						{
							if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value))
							{
								return parseFloat(value) > parseFloat(param[0])
										&& parseFloat(value) <= parseFloat(param[1]);
							} else
							{
								return false;
							}
						},
						message : '请输入0到100之间的最多俩位小数的数字'
					},
					rateCheck :
					{
						validator : function(value, param)
						{
							if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value))
							{
								return parseFloat(value) > parseFloat(param[0])
										&& parseFloat(value) <= parseFloat(param[1]);
							} else
							{
								return false;
							}
						},
						message : '请输入0到1000之间的最多俩位小数的数字'
					},

					myDate :
					{
						validator : function(mytime, param)
						{
							//标准时间格式  
						var regStandard = /^\1|2\d{3}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}$/; //匹配标准日期格式  2014-1-20 12:10:00  
						//日期快速输入格式    
						var regA = /^\1|2\d{3}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}$/; //匹配日期 和 时\分\秒  2014-1-20-12-10-00 意思是2014-1-20 12:10:00  
						var regB = /^\1|2\d{3}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}$/; //匹配日期 和 时\分  2014-1-20-12-10-00 意思是2014-1-20 12:10  
						var regC = /^(19\d{2}|2\d{3})-\d{1,2}-\d{1,2}$/; //匹配日期  2014-1-20   
						var x = "";

						if (!regStandard.test(mytime))
						{
							if (regA.test(mytime))
							{
								var tempArr = mytime.split('-');
								x = tempArr[0] + "-" + tempArr[1] + "-"
										+ tempArr[2] + " " + tempArr[3] + ":"
										+ tempArr[4] + ":" + tempArr[5];
								if (!(checkDateTime("date", x) && checkDateTime(
										"time", x)))
								{
									$.fn.validatebox.defaults.rules.myDate.message = "日期格式错误！";
									return false;
								}
							} else if (regB.test(mytime))
							{
								var tempArr = mytime.split('-');
								x = tempArr[0] + "-" + tempArr[1] + "-"
										+ tempArr[2] + " " + tempArr[3] + ":"
										+ tempArr[4] + ":00";
								if (!(checkDateTime("date", x) && checkDateTime(
										"time", x)))
								{
									$.fn.validatebox.defaults.rules.myDate.message = "日期格式错误";
									return false;
								}
							} else if (regC.test(mytime))
							{
								x = mytime + " 00:00:00";
								if (!checkDateTime("date", x))
								{
									$.fn.validatebox.defaults.rules.myDate.message = "日期格式错误";
									return false;
								}
							} else
							{
								$.fn.validatebox.defaults.rules.myDate.message = "日期格式错误";
								return false;
							}
						} else
						{
							if (!(checkDateTime("date", mytime) && checkDateTime(
									"time", mytime)))
							{
								$.fn.validatebox.defaults.rules.myDate.message = "日期格式错误";
								return false;
							}
						}
						return true;
					},
					message : ''
					}
				});

function checkDateTime(type, datetime, split)
{
	var date = datetime.split(" ")[0];
	var time = datetime.split(" ")[1];
	//alert(date + '\n' + time)  
	switch (type)
	{
	case "time"://检查时分秒的有效性  
		var tempArr = time.split(":");
		if (parseInt(tempArr[0]) > 23)
		{
			return false;
		}
		if (parseInt(tempArr[1]) > 60 || parseInt(tempArr[2]) > 60)
		{
			return false;
		}
		break;
	case "date"://检查日期的有效性  
		var tempArr = date.split("-");
		if (parseInt(tempArr[1]) == 0 || parseInt(tempArr[1]) > 12)
		{//月份  
			return false;
		}
		var lastday = new Date(parseInt(tempArr[0]), parseInt(tempArr[1]), 0);//获取当月的最后一天日期           
		if (parseInt(tempArr[2]) == 0
				|| parseInt(tempArr[2]) > lastday.getDate())
		{//当月的日  
			return false;
		}
		var myDate = new Date(parseInt(tempArr[0]), parseInt(tempArr[1]) - 1,
				parseInt(tempArr[2]));
		if (myDate == "Invalid Date")
		{
			return false;
		}
		break;
	}

	return true;
}