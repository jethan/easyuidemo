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

import com.demo.dao.CityDAO;
import com.demo.dao.CountyDAO;
import com.demo.dao.DepartmentDAO;
import com.demo.dao.ProvinceDAO;
import com.demo.model.City;
import com.demo.model.County;
import com.demo.model.Department;
import com.demo.model.Province;
import com.demo.model.Staff;
import com.demo.service.StaffManagement;
import com.demo.util.CacheMap;
import com.demo.util.StaffManagementConfig;
import com.demo.util.ValidatorUtil;


/**
 * Action for {@link Staff}.
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-2 上午10:28:57
 */
public class StaffServlet extends HttpServlet
{
	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The StaffManagement. */
	private final StaffManagement _staffManagement = new StaffManagement();

	/**
	 * Constructor.
	 */
	public StaffServlet()
	{
		// nothing.
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
		String operation = request.getParameter("operation");
		response.setContentType(StaffManagementConfig.CONTENTTYPE);
		request.setCharacterEncoding(StaffManagementConfig.CHARACTERENCODING);

		if (operation == null)
		{
			//to get all the province, city, county and department, set to the specified cacheMap which used to display in the dataGrid,
			//when set the value to the provinceStr, cityStr, countyStr and departmentStr in the data model.
			List<Province> lprovince = new ProvinceDAO().getProvince();
			Map<String, String> provinceMap = new HashMap<String, String>();
			for (Province p : lprovince)
			{
				provinceMap.put(p.getId(), p.getProvinceName());
			}
			
			List<City> lcity = new CityDAO().getCity();
			Map<String, String> cityMap = new HashMap<String, String>();
			for (City c : lcity)
			{
				cityMap.put(c.getId(), c.getCityName());

			}
			
			List<County> lcounty = new CountyDAO().getCounty();
			Map<String, String> countyMap = new HashMap<String, String>();
			for (County c : lcounty)
			{
				countyMap.put(c.getId(), c.getCountyName());
			}
			
			List<Department> ldepartment = new DepartmentDAO().getDepartments();
			Map<String, String> departmentMap = new HashMap<String, String>();
			for(Department d : ldepartment)
			{
				departmentMap.put(String.valueOf(d.getDeptNo()), d.getdName());
			}

			new CacheMap(provinceMap, cityMap, countyMap, departmentMap);
			//------------------------------------------------------------
			
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			int resultMaxCount = _staffManagement.countAll();
			int startIndex = (page - 1) * rows + 1;
			int endIndex = page * rows < resultMaxCount ? page * rows: resultMaxCount;
			List<Staff> staffList = _staffManagement.getAllStaffs(startIndex, endIndex);
			_List2JSON(response, request, staffList, resultMaxCount);
		}else if ("add".equals(operation))
		{
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String gender = request.getParameter("gender");
			String date = request.getParameter("birthday");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String county = request.getParameter("county");
			String address = request.getParameter("address");
			int deptNo = Integer.parseInt(request.getParameter("deptNo"));

			if (_isLegal(response, fName, lName, date, telephone, email, province, city, county, true))
			{
				try
				{
					Staff staff = new Staff();
					staff.setfName(fName);
					staff.setlName(lName);
					staff.setGender(gender);
					staff.setBirthday(date);
					staff.setTelephone(telephone);
					staff.setEmail(email);
					staff.setProvince(province);
					staff.setCity(city);
					staff.setCounty(county);
					staff.setAddress(address);
					staff.setDeleted("0");
					staff.setDeptNo(deptNo);
					_staffManagement.addStaff(staff);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		} else if ("delete".equals(operation))
		{
			String[] arrayId = request.getParameterValues("id[]");

			PrintWriter out = response.getWriter();
			if (_staffManagement.deleteStaff(arrayId))
			{
				out.println("{\"success\":\"true\",\"msg\":\"删除成功...\"}");
			}
			else
			{
				out.println("{\"success\":\"false\",\"msg\":\"删除失败...\"}");
			}
			out.flush();
			out.close();
		} else if ("modify".equals(operation))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String gender = request.getParameter("gender");
			String date = request.getParameter("birthday");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String county = request.getParameter("county");
			String address = request.getParameter("address");
			int deptNo = Integer.parseInt(request.getParameter("deptNo"));

			if (_isLegal(response, fName, lName, date, telephone, email, province, city, county, false))
			{
				try
				{
					Staff staff = _staffManagement.getStaffById(id);
					staff.setfName(fName);
					staff.setlName(lName);
					staff.setGender(gender);
					staff.setBirthday(date);
					staff.setTelephone(telephone);
					staff.setEmail(email);
					staff.setProvince(province);
					staff.setCity(city);
					staff.setCounty(county);
					staff.setAddress(address);
					staff.setDeptNo(deptNo);
					_staffManagement.updateStaff(staff);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		} else if ("search".equals(operation))
		{
			String lName = request.getParameter("lName");
			String fName = request.getParameter("fName");
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			int resultMaxCount = _staffManagement.count(fName, lName);
			int startIndex = (page - 1) * rows + 1;
			int endIndex = page * rows < resultMaxCount ? page * rows : resultMaxCount;
			List<Staff> staffList = _staffManagement.findStaffByName(fName, lName, startIndex, endIndex);
			if("".equals(lName) && "".equals(fName))
			{
				resultMaxCount = staffList.size();
			}
			_List2JSON(response, request, staffList, resultMaxCount);
		}
	}

	/**
	 * Change the list to JSON format.
	 * 
	 * @param response the response.
	 * @param request the request.
	 * @param staffList the staffList.
	 */
	private void _List2JSON(HttpServletResponse response,HttpServletRequest request,List<Staff> staffList,int resultMaxCount)
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        jsonMap.put("total", resultMaxCount);
        jsonMap.put("rows", staffList);
			
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
	 * Validate the input legal or not.
	 * 
	 * @param response the response.
	 * @param fName the fName.
	 * @param lName the lName.
	 * @param birthday the birthday.
	 * @param phone the phone.
	 * @param email the email.
	 * @param isAdd the isAdd.
	 * @return <code>true</code> if parameter is not null ,else <code>false</code>.
	 */
	private boolean _isLegal(HttpServletResponse response, String fName, String lName, String birthday,
			String phone, String email, String province, String city, String county, boolean isAdd)
	{
		boolean flag = true;
		StringBuilder msg = new StringBuilder();

		if (ValidatorUtil.getInstance().requiredValidator(fName))
		{
			msg.append("请输入名字...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(lName))
		{
			msg.append("请输入姓...<br/>");
			flag = false;
		}
		if (!ValidatorUtil.getInstance().dateTimeValidator(birthday))
		{
			msg.append("日期格式错误...<br/>");
			flag = false;

		}
		if (!ValidatorUtil.getInstance().phoneValidator(phone))
		{
			msg.append("您输入的电话有误，请重新输入...<br/>");
			flag = false;

		}
		if (!ValidatorUtil.getInstance().emailValidator(email))
		{
			msg.append("您输入的邮件有误，请重新输入...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(province))
		{
			msg.append("请选择省...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(city))
		{
			msg.append("请选择市...<br/>");
			flag = false;
		}
		if (ValidatorUtil.getInstance().requiredValidator(county))
		{
			msg.append("请选择县...<br/>");
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
}
