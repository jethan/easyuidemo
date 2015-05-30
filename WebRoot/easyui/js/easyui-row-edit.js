var editIndex =
{};
function endEditing(datagridId, indexKey)
{
	console.log(editIndex);
	if (editIndex[indexKey] == undefined)
	{
		return true;
	} // 当前没有编辑的行直接返回true
	if ($(datagridId).datagrid('validateRow', editIndex[indexKey]))
	{ //校验通过
		$(datagridId).datagrid('endEdit', editIndex[indexKey]); // 完成编辑
		editIndex[indexKey] = undefined;
		return true;
	} else
	{
		return false; //未通过校验
	}
}
function onClickRow(datagridId, index, indexKey)
{
	if (editIndex[indexKey] != index)
	{ // 点击的行和当前编辑的行不一样时
		if (endEditing(datagridId, indexKey))
		{ // 当前编辑的行可结束编辑时
			$(datagridId).datagrid('selectRow', index) // 选中当前点击行
					.datagrid('beginEdit', index); // 并开始编辑
			editIndex[indexKey] = index; // 当前编辑行变成点击的行
		} else
		{ // 当前编辑行不可结束
			$(datagridId).datagrid('selectRow', editIndex[indexKey]); // 继续留在当前编辑的行 
		}
	}
}
function append(datagridId, indexKey)
{
	if (endEditing(datagridId, indexKey))
	{ // 首先还是要判断是否能结束当前编辑行
		$(datagridId).datagrid('appendRow',
		{ /*status:'P'*/}); // 新增一行再最后
		editIndex[indexKey] = $(datagridId).datagrid('getRows').length - 1;
		$(datagridId).datagrid('selectRow', editIndex[indexKey]) // 并选中新增的行
				.datagrid('beginEdit', editIndex[indexKey]); // 并开始编辑
	}
}
function removeit(datagridId, indexKey)
{
	if (editIndex[indexKey] == undefined)
	{
		return;
	} // 当前没有选中行，什么也不做，直接返回
	$(datagridId).datagrid('cancelEdit', editIndex[indexKey]) // 取消当前编辑行的编辑
			.datagrid('deleteRow', editIndex[indexKey]); // 并删除当前行
	editIndex[indexKey] = undefined;
}
function accept(datagridId, indexKey, url, ids)
{
	// 还是先判断是否可结束编辑才能继续保存
	if (!endEditing(datagridId, indexKey))
		return;
	// 判断一下，没有更改，则不用保存
	if ($(datagridId).datagrid('getChanges').length === 0)
		return;

	// 分别获得新增行、删除行、更改行的数据    
	var inserted = $(datagridId).datagrid('getChanges', 'inserted');
	var deleted = $(datagridId).datagrid('getChanges', 'deleted');
	var updated = $(datagridId).datagrid('getChanges', 'updated');
	var deletedId = []; // 
	if (ids)
	{ // 设置了主键字段，才重新组装删除行的数据
		if ($.isArray(ids))
		{ // 组合主键的，只返回包含组合主键和值的对象
			deletedId = $.map(deleted, function(row)
			{
				var newRow =
				{};
				$.each(ids, function(idx, id)
				{
					newRow[id] = row[id];
				});
				return newRow;
			});
		} else
		{ // 只有一个主键字段的，只返回主键的值，连主键都不用返回
			deletedId = $.map(deleted, function(row)
			{
				return row[ids];
			});
		}
	} else
	{
		deletedId = deleted;
	}

	$('#w').window('open');

	$.ajax(
	{
		url : url,
		dataType : 'json',
		method : 'POST',
		data :
		{
			inserted : $.toJSON(inserted),
			updated : $.toJSON(updated),
			deleted : $.toJSON(deletedId)
		}
	}).done(function(data)
	{
		if (data.status === 'OK')
		{ // 提交成功
				$(datagridId).datagrid('acceptChanges');
				$(datagridId).datagrid('reload'); // 刷新datagrid
			}

		}).fail(function(resp)
	{
		$.messager.alert('出错了', resp.responseText);
	}).always(function()
	{
		$('#w').window('close');
	});
}
function reject(datagridId, indexKey)
{
	$(datagridId).datagrid('rejectChanges');
	editIndex[indexKey] = undefined;
}
function getChanges()
{
	var rows = $('#dg').datagrid('getChanges');
	alert(rows.length + ' rows are changed!');
}