package com.demo.service;

import java.util.List;

import com.demo.dao.ProvinceDAO;
import com.demo.model.Province;

/**
 * {@link Province} service.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:22:31
 */
public class ProvinceManagement
{
	/** The province DAO. */
	private ProvinceDAO _provinceDAO = new ProvinceDAO();
	
	/**
	 * Get the province.
	 * @return the province list.
	 */
	public List<Province> getProvince()
	{
		return _provinceDAO.getProvince();
	}
}
