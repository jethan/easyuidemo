package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.demo.dao.AdminDAO;
import com.demo.model.Admin;
import com.demo.util.StaffManagementConfig;
import com.demo.util.ValidatorUtil;


/**
 * Action for {@link Admin}.
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-20 上午10:21:07
 */
public class AdminServlet extends HttpServlet
{
	/** The serialVersionUID. */
	private static final long serialVersionUID = 4016245189492700517L;
	/** The adminDAO. */
	private final AdminDAO _adminDAO = new AdminDAO();

	/**
	 * Constructor.
	 */
	public AdminServlet()
	{
		// nothing.
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

		String operation = request.getParameter("operation");
		if (operation==null)
		{
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			int resultMaxCount = _adminDAO.countAll();
			int startIndex = (page - 1) * rows + 1;
			int endIndex = page * rows < resultMaxCount ? page * rows: resultMaxCount;
			List<Admin> adminList = _adminDAO.getAllUsers(startIndex, endIndex);
			_List2JSON(response, request, adminList, resultMaxCount);
		}else if("toLogin".equals(operation))
		{
			// 获取session对象
			HttpSession session = request.getSession();
			// //获取用户输入的验证码
			// String randCodeInput = request.getParameter("randCode");
			// //真实验证码
			// String randCode = (String) session.getAttribute("randCode");
			String userName = request.getParameter("userName").trim();
			String password = request.getParameter("password").trim();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			AdminDAO _adminDAO = new AdminDAO();
			/*
			 * //如果用户输入的验证码为空 if (randCodeInput == null ||
			 * randCodeInput.equals("")){ dataMap.put("result", false);
			 * dataMap.put("msg", "请输入验证码！"); //如果用户输入的验证码与真实验证码不相同 } else if
			 * (!randCodeInput.equals(randCode)){ dataMap.put("result", false);
			 * dataMap.put("msg", "验证码输入错误，请重新输入！"); //验证码正确 } else
			 */if ("".equals(userName))
			{
				dataMap.put("result", false);
				dataMap.put("msg", "用户名不能为空，请重新输入！");
			} else if (_adminDAO.validateLogin(userName, password))
			{
				dataMap.put("result", true);
				dataMap.put("msg", "登陆成功");
				// 验证成功的话,把用户名放到session中
				session.setAttribute("loginUser", _adminDAO
						.getAdminInfo(userName));

				// 然后转向成功页面
				// request.getRequestDispatcher("easyui/default.jsp").forward(request,
				// response);
				/*
				 * MD5 md5 = new MD5(); Map<String, String> args = new
				 * HashMap<String, String>(); args.put("userName", userName);
				 * args.put("password", md5.getMD5ofStr(password));
				 */
			} else
			{
				dataMap.put("result", false);
				dataMap.put("msg", "登录失败，用户名或密码错误！");
			}

			PrintWriter out;
			try
			{
				out = response.getWriter();
				out.println(JSONObject.fromObject(dataMap));
				out.flush();
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}else if("toChangePassword".equals(operation))
		{
			int userNo = Integer.parseInt(request.getParameter("id"));
			String inputOldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			String con_password = request.getParameter("con_password");
			String oldPassword = _adminDAO.getPassword(userNo);
			if(_isLegal(response,inputOldPassword,oldPassword,password,con_password,false))
			{
				Admin admin = _adminDAO.getAdmin(userNo);
				admin.setPassword(con_password);
				_adminDAO.updateAdmin(admin);
			}
		}else if ("add".equals(operation))
		{
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			if(_isLegalAdd(response,userName,password))
			{
				String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
						.getInstance().getTime());
				Admin user = new Admin();
				user.setUserName(userName);
				user.setPassword(password);
				user.setCreateTime(date);
				user.setDeleted("0");
				_adminDAO.addUser(user);
			}
		} else if ("delete".equals(operation))
		{
		} else if ("modify".equals(operation))
		{
		} else if ("search".equals(operation))
		{
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String userName = request.getParameter("userName");
			int resultMaxCount = _adminDAO.count(userName);
			int startIndex = (page - 1) * rows + 1;
			int endIndex = page * rows < resultMaxCount ? page * rows: resultMaxCount;
			List<Admin> adminList = _adminDAO.findAdminByUserName(userName, startIndex, endIndex);
			_List2JSON(response, request, adminList, resultMaxCount);
		}
	}

	/**
	 * To validate the field legal or not.
	 * @param response the response.
	 * @param inputOldPassword the inputOldPassword.
	 * @param oldPassword the oldPassword.
	 * @param password the password.
	 * @param con_password con_password.
	 * @param isAdd the isAdd.
	 * @return <code>true</code> if parameter is not null ,else <code>false</code>.
	 */
	private boolean _isLegal(HttpServletResponse response, String inputOldPassword, String oldPassword, String password, String con_password, boolean isAdd)
	{
		boolean flag = true;
		StringBuilder msg = new StringBuilder();

		if (ValidatorUtil.getInstance().requiredValidator(inputOldPassword))
		{
			msg.append("请输入原密码...<br/>");
			flag = false;
		}else if(!inputOldPassword.equals(oldPassword))
		{
			msg.append("原密码输入错误...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(password))
		{
			msg.append("请输入新密码...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(con_password))
		{
			msg.append("请确认新密码...<br/>");
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
	 * @param adminList the adminList.
	 * @param resultMaxCount the resultMaxCount.
	 */ 
	private void _List2JSON(HttpServletResponse response,HttpServletRequest request,List<Admin> adminList,int resultMaxCount)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        jsonMap.put("total", resultMaxCount);
        jsonMap.put("rows", adminList);
			
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
	
	/**
	 * Validate the field legal or not.
	 * @param response the response.
	 * @param userName the userName.
	 * @param password the password.
	 * @return <code>true</code> if parameter is not null ,else <code>false</code>.
	 */
	private boolean _isLegalAdd(HttpServletResponse response, String userName,String password)
	{
		boolean flag = true;
		StringBuilder msg = new StringBuilder();

		if (ValidatorUtil.getInstance().requiredValidator(userName))
		{
			msg.append("请用户名...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(password))
		{
			msg.append("请输入密码...<br/>");
			flag = false;
		}
		if (flag)
		{
			msg.append("添加成功...<br/>");
		}
		else
		{
			msg.append("添加失败...<br/>");
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
}
