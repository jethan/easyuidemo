package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.demo.dao.DepartmentDAO;
import com.demo.model.Department;
import com.demo.util.StaffManagementConfig;
import com.demo.util.ValidatorUtil;

/**
 * Action for {@link Department}.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-21 下午11:21:19
 */
public class DepartmentServlet extends HttpServlet
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 7299299620910125170L;
	/** The department DAO. */
	private final DepartmentDAO _departmentDAO= new DepartmentDAO();

	/**
	 * Constructor.
	 */
	public DepartmentServlet()
	{
		//nothing.
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String operation = request.getParameter("operation");
		response.setContentType(StaffManagementConfig.CONTENTTYPE);
		request.setCharacterEncoding(StaffManagementConfig.CHARACTERENCODING);

		if (operation == null)
		{
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			int resultMaxCount = _departmentDAO.countAll();
			int startIndex = (page - 1) * rows + 1;
			int endIndex = page * rows < resultMaxCount ? page * rows: resultMaxCount;
			List<Department> departmentList = _departmentDAO.getAllDepartments(startIndex, endIndex);
			_List2JSON(response, request, departmentList, resultMaxCount);
		}else if ("add".equals(operation))
		{
			String dName = request.getParameter("dName");
			String description = request.getParameter("description");
			if(_isLegal(response,dName,true))
			{
				Department department = new Department();
				department.setdName(dName);
				department.setDescription(description);
				department.setDeleted("0");
				_departmentDAO.addDepartment(department);
			}
		} else if ("delete".equals(operation))
		{
		} else if ("modify".equals(operation))
		{
			int deptNo = Integer.parseInt(request.getParameter("id"));
			String dName = request.getParameter("dName");
			String description = request.getParameter("description");
			if(_isLegal(response,dName,false))
			{
				Department department = _departmentDAO.getDepartmentById(deptNo);
				department.setdName(dName);
				department.setDescription(description);
				department.setDeleted("0");
				_departmentDAO.updateDepartment(department);
			}
			
		} else if ("search".equals(operation))
		{
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String dName = request.getParameter("department");
			int resultMaxCount = _departmentDAO.count(dName);
			int startIndex = (page - 1) * rows + 1;
			int endIndex = page * rows < resultMaxCount ? page * rows: resultMaxCount;
			List<Department> departmentList = _departmentDAO.findDepartmentBydName(dName, startIndex, endIndex);
			_List2JSON(response, request, departmentList, resultMaxCount);
		}else if("getDepartments".equals(operation))
		{
			PrintWriter out;
			try
			{
				out = response.getWriter();
				out.println(JSONArray.fromObject(_departmentDAO.getDepartments()));
				out.flush();
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Validate the departmentName is null or not.
	 * @param response the response.
	 * @param departmentName the departmentName.
	 * @param isAdd the isAdd.
	 * @return <code>true</code> if parameter is not null ,else <code>false</code>.
	 */
	private boolean _isLegal(HttpServletResponse response, String departmentName, boolean isAdd)
	{
		boolean flag = true;
		StringBuilder msg = new StringBuilder();

		if (ValidatorUtil.getInstance().requiredValidator(departmentName))
		{
			msg.append("请输入部门名称...<br/>");
			flag = false;
		}
		if (flag)
		{
			if (isAdd)
			{
				msg.append("添加成功...<br/>");
			} else
			{
				msg.append("修改成功...<br/>");
			}
		}
		try
		{
			PrintWriter out = response.getWriter();
			out.println("{\"result\":" + flag + ",\"msg\":\"" + msg + "\"}");
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * Change the list to JSON format.
	 * @param response the response.
	 * @param request the request.
	 * @param departmentList the departmentList.
	 * @param resultMaxCount the resultMaxCount.
	 */
	private void _List2JSON(HttpServletResponse response,HttpServletRequest request,List<Department> departmentList,int resultMaxCount)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        jsonMap.put("total", resultMaxCount);
        jsonMap.put("rows", departmentList);
			
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.println(JSONObject.fromObject(jsonMap));
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
