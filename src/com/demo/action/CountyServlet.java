package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.demo.model.County;
import com.demo.service.CountyManagement;

/**
 * {@link County} action.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:21:09
 */
public class CountyServlet extends HttpServlet
{

	/* The serialVersionUID. */
	private static final long serialVersionUID = -4136084427980467443L;

	/**
	 * Constructor.
	 */
	public CountyServlet()
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
			
	        String city = request.getParameter("city");
	        
			CountyManagement countyManagement = new CountyManagement();
			List<County> county = countyManagement.getCounty(city);
				
			PrintWriter out;
			try
			{
				out = response.getWriter();
				out.println(JSONArray.fromObject(county));
				out.flush();
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	}
}
