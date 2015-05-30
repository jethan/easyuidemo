package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.demo.dao.DeptStaffGenderDAO;
import com.demo.model.DeptStaffGenderBean;
import com.demo.util.StaffManagementConfig;

/**
 * 统计各部门男女员工所占比例
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-28 上午09:55:21
 */
public class DeptStaffGenderServlet extends HttpServlet
{
	/** The serialVersionUID. */
	private static final long serialVersionUID = -8819635434259808798L;
	/** The deptStaffGenderDAO. */
	private final DeptStaffGenderDAO _deptStaffGenderDAO = new DeptStaffGenderDAO();

	/**
	 * Constructor of the object.
	 */
	public DeptStaffGenderServlet()
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
		
		List<DeptStaffGenderBean> list = _deptStaffGenderDAO.countStaffByGenderOfDept();
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
