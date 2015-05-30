// 基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('bar'));
var departmentArr = [];
var numArr = [];
	$.ajax(
	{
		type : "post",
		async : false, //同步执行
		url : "DeptStaffServlet",
		data :
		{},
		dataType : "json", //返回数据形式为json
		success : function(result)
		{
			if (result)
			{
				for ( var i = 0; i < result.length; i++)
				{
					departmentArr.push(result[i].departmentName);
					numArr.push(result[i].num);
				}
			}
		},
		error : function(errorMsg)
		{
			alert("不好意思，图表请求数据失败啦!");
			myChart.hideLoading();
		}
	})

option =
{
	title :
	{
		text : '部门员工统计',
		subtext : '柱状图/折线图示例'
	},
	tooltip :
	{
		trigger : 'axis'
	},
	legend :
	{
		data : [ '员工人数' ]
	},
	toolbox :
	{
		show : true,
		feature :
		{
			mark :
			{
				show : true
			},
			dataView :
			{
				show : true,
				readOnly : false
			},
			magicType :
			{
				show : true,
				type : [ 'line', 'bar']
			},
			restore :
			{
				show : true
			},
			saveAsImage :
			{
				show : true
			}
		}
	},
	calculable : true,
	xAxis : [
	{
		type : 'category',
		data : departmentArr,
	} ],
	yAxis : [
	{
		type : 'value'
	} ],
	series : [
	{
		"name" : "员工人数",
		"type" : "bar",
		"data" : numArr,
		markPoint :
		{
			data : [
			{
				type : 'max',
				name : '最大值'
			},
			{
				type : 'min',
				name : '最小值'
			} ]
		},
		markLine :
		{
			data : [
			{
				type : 'average',
				name : '平均值'
			} ]
		}
	} ]
};

// 为echarts对象加载数据 
myChart.setOption(option);