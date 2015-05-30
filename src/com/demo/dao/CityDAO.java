package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.City;
import com.demo.util.BaseDAO;

/**
 * DAO for {@link City}.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:21:49
 */
public class CityDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(CityDAO.class);
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	/** The baseDAO. */
	private BaseDAO _bd;
	
	/**
	 * Get the city of the specified province.
	 * @param province the province.
	 * @return the city list.
	 */
	public List<City> getCity(String province)
	{
		_logger.info("----下拉框---查询市---");
		
		List<City> list = new ArrayList<City>();
		City city = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql ="select * from AREA_CITY where provinceId = "+province;
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				city = new City();
				city.setId(_rs.getString(1));
				city.setProvinceId(_rs.getString(2));
				city.setCityName(_rs.getString(3));
				list.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
	
	/**
	 * Get all the cities.
	 * @return the city list.
	 */
	public List<City> getCity()
	{
		_logger.info("----加载市列表---");
		
		List<City> list = new ArrayList<City>();
		City city = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql ="select * from AREA_CITY";
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				city = new City();
				city.setId(_rs.getString(1));
				city.setProvinceId(_rs.getString(2));
				city.setCityName(_rs.getString(3));
				list.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
}
