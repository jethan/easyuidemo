package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.demo.dao.DeptStaffDAO;
import com.demo.model.DeptStaffBean;
import com.demo.util.StaffManagementConfig;

/**
 * 统计每个部门的员工数量。
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-27 下午01:56:52
 */
public class DeptStaffServlet extends HttpServlet
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = -680219697005488448L;
	/** The deptStaffDAO. */
	private final DeptStaffDAO _deptStaffDAO = new DeptStaffDAO();

	/**
	 * Constructor of the object.
	 */
	public DeptStaffServlet()
	{
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType(StaffManagementConfig.CONTENTTYPE);
		request.setCharacterEncoding(StaffManagementConfig.CHARACTERENCODING);
		
		List<DeptStaffBean> list = _deptStaffDAO.countStaffsOfDept();
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.println(JSONArray.fromObject(list));
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
