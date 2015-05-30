package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.County;
import com.demo.util.BaseDAO;

/**
 * DAO for {@link County}.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:22:01
 */
public class CountyDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(CountyDAO.class);
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	/** The baseDAO. */
	private BaseDAO _bd;
	
	/**
	 * Get the county of the specified city.
	 * @param city the city.
	 * @return the county list.
	 */
	public List<County> getCounty(String city)
	{
		_logger.info("----下拉框---查询县/区---");
		
		List<County> list = new ArrayList<County>();
		County county = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql ="select * from AREA_COUNTY where cityId = "+city;
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				county = new County();
				county.setId(_rs.getString(1));
				county.setCityId(_rs.getString(2));
				county.setCountyName(_rs.getString(3));
				list.add(county);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
	
	/**
	 * Get all the counties.
	 * @return the county list.
	 */
	public List<County> getCounty()
	{
		_logger.info("----加载县/区列表---");
		
		List<County> list = new ArrayList<County>();
		County county = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql ="select * from AREA_COUNTY";
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				county = new County();
				county.setId(_rs.getString(1));
				county.setCityId(_rs.getString(2));
				county.setCountyName(_rs.getString(3));
				list.add(county);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
}
