package com.demo.service;

import java.util.List;

import com.demo.dao.ConstantDAO;
import com.demo.model.Constant;


/**
 * Service refers to the corresponding {@link ConstantDAO} operations.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-17 下午02:24:09
 */
public class ConstantManagement
{
	/** The constant DAO. */
	private ConstantDAO _constantDAO = new ConstantDAO();
	
	/**
	 * Get the gender.
	 * @return the gender list.
	 */
	public List<Constant> getGender()
	{
		return _constantDAO.getGender();
	}
}
