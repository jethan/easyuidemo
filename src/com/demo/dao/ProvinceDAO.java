package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.Province;
import com.demo.util.BaseDAO;

/**
 * DAO for {@link Province}.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-18 下午08:21:36
 */
public class ProvinceDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(ProvinceDAO.class);
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	/** The baseDAO. */
	private BaseDAO _bd;
	
	/**
	 * Get the province.
	 * @return the province list.
	 */
	public List<Province> getProvince()
	{
		_logger.info("----加载省列表---");
		
		List<Province> list = new ArrayList<Province>();
		Province province = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql ="select * from AREA_PROVINCE";
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				province = new Province();
				province.setId(_rs.getString(1));
				province.setProvinceName(_rs.getString(2));
				list.add(province);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
}
