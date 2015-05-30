package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.demo.model.Province;
import com.demo.service.ProvinceManagement;
import com.demo.util.StaffManagementConfig;

/**
 * {@link Province} action.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:20:24
 */
public class ProvinceServlet extends HttpServlet
{
	/* The serialVersionUID. */
	private static final long serialVersionUID = -4968736076824777792L;
	
	/**
	 * Constructor.
	 */
	public ProvinceServlet()
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
		response.setContentType(StaffManagementConfig.CONTENTTYPE);
		
		ProvinceManagement provinceManagement = new ProvinceManagement();
		List<Province> province = provinceManagement.getProvince();
			
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.println(JSONArray.fromObject(province));
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
