package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.DeptStaffGenderBean;
import com.demo.util.BaseDAO;

/**
 * 数据访问对象统计各部门男女员工所占比例。
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-28 上午09:46:34
 */
public class DeptStaffGenderDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(DeptStaffGenderDAO.class);
	/** The baseDAO. */
	private BaseDAO _bd;
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	
	/**
	 * 按部门统计男女员工比列。
	 * @return the deptStaffGenderBean list.
	 */
	public List<DeptStaffGenderBean> countStaffByGenderOfDept()
	{
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		List<DeptStaffGenderBean> list = new ArrayList<DeptStaffGenderBean>();
		DeptStaffGenderBean deptStaffGenderBean = null;
		_logger.info("按部门统计男女员工数量...");
		String sql = "select d.dName,sum(decode(t.gender,'1',1,0)) male," +
				"sum(decode(t.gender,'0',1,0)) female from STAFFINFO t, " +
				"department d where t.deptno=d.deptno and t.deleted=0 group by d.dName";
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				deptStaffGenderBean = new DeptStaffGenderBean();
				deptStaffGenderBean.setDepartmentName(_rs.getString(1));
				deptStaffGenderBean.setMale(_rs.getInt(2));
				deptStaffGenderBean.setFemale(_rs.getInt(3));
				list.add(deptStaffGenderBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//			finally {
//			_bd.CloseAll(_conn, _ps, _rs);
//		}
		return list;
	}
}
