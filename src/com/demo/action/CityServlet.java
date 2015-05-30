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

import com.demo.model.City;
import com.demo.service.CityManagement;

/**
 * {@link City} action.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:20:49
 */
public class CityServlet extends HttpServlet
{

	/* The serialVersionUID. */
	private static final long serialVersionUID = -3831433960916749756L;

	/**
	 * Constructor.
	 */
	public CityServlet()
	{
		//nothing.
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
        response.setCharacterEncoding("UTF-8");
		
        String province = request.getParameter("province");
        
		CityManagement cityManagement = new CityManagement();
		List<City> city = cityManagement.getCity(province);
			
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.println(JSONArray.fromObject(city));
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
