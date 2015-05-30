package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.DeptStaffBean;
import com.demo.util.BaseDAO;

/**
 * 数据访问对象统计部门员工数量。
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-27 下午02:14:30
 */
public class DeptStaffDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(DeptStaffDAO.class);
	/** The baseDAO. */
	private BaseDAO _bd;
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	
	/**
	 * 按部门统计员工数量，返回一个DeptStaffBean list。
	 * @return the deptStaffBean list.
	 */
	public List<DeptStaffBean> countStaffsOfDept()
	{
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		List<DeptStaffBean> list = new ArrayList<DeptStaffBean>();
		DeptStaffBean deptStaffBean = null;
		_logger.info("按部门统计员工数量...");
		String sql = "select r.dName,count(r.deptNo) as num from" +
				"(select t.deptNo,d.dName from STAFFINFO t, department d " +
				"where t.deptno=d.deptno and t.deleted=0 ) r group by r.deptNo,r.dname " +
				"order by r.dname,r.deptNo";
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				deptStaffBean = new DeptStaffBean();
				deptStaffBean.setDepartmentName(_rs.getString(1));
				deptStaffBean.setNum(_rs.getInt(2));
				list.add(deptStaffBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			_bd.CloseAll(_conn, _ps, _rs);
//		}
		return list;
	}
}
