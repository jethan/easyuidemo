package com.demo.service;

import java.util.List;

import com.demo.dao.CountyDAO;
import com.demo.model.County;

/**
 * {@link County} service.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:23:04
 */
public class CountyManagement
{
    /** The countyDAO. */
	private CountyDAO _countyDAO = new CountyDAO();
	
	/**
	 * Get the county of the specified city.
	 * @param city the city.
	 * @return the county list.
	 */
	public List<County> getCounty(String city)
	{
		return _countyDAO.getCounty(city);
	}
}
