package com.demo.junit;

import org.junit.Test;

import com.demo.dao.ConstantDAO;
import com.demo.dao.StaffDAO;
import com.demo.model.Constant;
import com.demo.model.Staff;

/**
 * FIXME : Document this class.
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-6 下午02:50:09
 */
public class TestFunction
{
	@Test
	public void testadd()
	{
//		StaffDAO dao = new StaffDAO();
//		Staff user = new Staff();
//		user.setlName("12121");
//		user.setEmail("1234");
//		dao.addUser(user);
		
		
		ConstantDAO constantDAO = new ConstantDAO();
		constantDAO.getGender();
		System.out.print(constantDAO.getGender().get(1).getClass());
	}
}
