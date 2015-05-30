package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.demo.util.StaffManagementConfig;

/**
 * Action for login.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-21 下午12:34:13
 */
public class LoginServlet extends HttpServlet
{
	/** The serialVersionUID. */
	private static final long serialVersionUID = -1813996920199158805L;
	/** Json数据映射集. */
	private Map<String, Object> _dataMap;
	
	/**
	 * Constructor.
	 */
	public LoginServlet()
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
		
		if("toLogin".equals(operation))
		{
			request.getRequestDispatcher("easyui/login.jsp").forward(request, response);
		}else if("toLogout".equals(operation))
		{
			//获取session对象
			HttpSession session = request.getSession();
			//使session失效
			session.invalidate();
			_dataMap = new HashMap<String, Object>();
			_dataMap.put("success", true);
			_dataMap.put("msg", "成功!");
			PrintWriter out;
			try
			{
				out = response.getWriter();
				out.println(JSONArray.fromObject(_dataMap));
				out.flush();
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
