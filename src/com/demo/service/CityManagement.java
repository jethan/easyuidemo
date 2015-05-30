package com.demo.service;

import java.util.List;

import com.demo.dao.CityDAO;
import com.demo.model.City;

/**
 * {@link City} service.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:22:47
 */
public class CityManagement
{
	/** The cityDAO. */
	private CityDAO _cityDAO = new CityDAO();
	
	/**
	 * Get the city of the specified province.
	 * @param province the province.
	 * @return the city list.
	 */
	public List<City> getCity(String province)
	{
		return _cityDAO.getCity(province);
	}
}
