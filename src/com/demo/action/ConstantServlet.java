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

import net.sf.json.JSONObject;

import com.demo.model.Constant;
import com.demo.service.ConstantManagement;

/**
 * Constant action.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 上午01:00:00
 */
public class ConstantServlet extends HttpServlet
{

	/* The serialVersionUID. */
	private static final long serialVersionUID = 8403405182183054385L;

	/**
	 * Constructor.
	 */
	public ConstantServlet()
	{
		//nothing.
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
			response.setCharacterEncoding("UTF-8");
			
			ConstantManagement constantManagement = new ConstantManagement();
			List<Constant> gender = constantManagement.getGender();
			Map<String, Object> jsonMap = new HashMap<String, Object>(); 
	        jsonMap.put("gender", gender);
				
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
