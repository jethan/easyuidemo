package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Constant;
import com.demo.util.BaseDAO;

/**
 * DAO for {@link Constant}
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-17 下午02:18:18
 */
public class ConstantDAO
{
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	/** The baseDAO. */
	private BaseDAO _bd;
	
	/**
	 * Get the gender.
	 * @return the gender list.
	 */
	public List<Constant> getGender()
	{
		List<Constant> list = new ArrayList<Constant>();
		Constant gender = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql ="select * from CONSTANT where type = '性别'";
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				gender = new Constant();
				gender.setId(_rs.getInt(1));
				gender.setTitle(_rs.getString(2));
				gender.setValue(_rs.getString(3));
				gender.setType(_rs.getString(4));
				gender.setChecked(_rs.getString(5));
				list.add(gender);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
}
