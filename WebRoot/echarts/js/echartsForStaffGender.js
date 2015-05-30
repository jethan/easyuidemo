// 基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('line'));
var departmentArr = [];
var maleNumArr = [];
var femaleNumArr = [];
	$.ajax(
	{
		type : "post",
		async : false, //同步执行
		url : "DeptStaffGenderServlet",
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
					maleNumArr.push(result[i].male);
					femaleNumArr.push(result[i].female);
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
		text : '部门男女员工统计',
		subtext : '折线图/柱状图示例'
	},
	tooltip :
	{
		trigger : 'axis'
	},
	legend :
	{
		data : [ '男','女' ]
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
				type : [ 'line', 'bar', 'stack', 'tiled']
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
		boundaryGap : false,
		data : departmentArr,
	} ],
	yAxis : [
	{
		type : 'value'
	} ],
	series : [
	{
		"name" : "男",
		"type" : "line",
		smooth:true,
        itemStyle: {normal: {areaStyle: {type: 'default'}}},
		"data" : maleNumArr,
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
	},
	{
		"name" : "女",
		"type" : "line",
		smooth:true,
        itemStyle: {normal: {areaStyle: {type: 'default'}}},
		"data" : femaleNumArr,
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
	}]
};

// 为echarts对象加载数据 
myChart.setOption(option);