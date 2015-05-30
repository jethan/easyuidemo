package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.Department;
import com.demo.util.BaseDAO;

/**
 * 部门数据接入对象
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-21 下午11:21:33
 */
public class DepartmentDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(DepartmentDAO.class);
	/** The baseDAO. */
	private BaseDAO _bd;
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	
	/**
	 * To add the department.
	 * @param department the department.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean addDepartment(Department department)
	{
		_logger.info("添加部门...");
		return _getRows("insert into DEPARTMENT (deptNo,dName,description,deleted) values(?,?,?,?)",department,false);
	}
	
	/**
	 * To add or update the department.
	 * @param sql the sql.
	 * @param department the department.
	 * @param update the update.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	private boolean _getRows(String sql,Department department,boolean update)
	{
		BaseDAO bd = new BaseDAO();
		boolean success = false;
		_conn = bd.getConnnection();
		try
		{
			if (update)
			{
				_ps = _conn.prepareStatement(sql);
				_ps.setString(1, department.getdName());
				_ps.setString(2, department.getDescription());
				_ps.setInt(3, department.getDeptNo());
			} else
			{
				String sqlId = "select DEPARTMENT_SEQUENCES.nextval from department";
				_ps = _conn.prepareStatement(sqlId);
				ResultSet rs = _ps.executeQuery();
				rs.next();
				int deptNo = rs.getInt(1);
				rs.close();
				_ps.close();
				_ps = _conn.prepareStatement(sql);
				_ps.setInt(1, deptNo);
				_ps.setString(2, department.getdName());
				_ps.setString(3, department.getDescription());
				_ps.setString(4, department.isDeleted());
			}
			
			_ps.executeUpdate();
			success = true;
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			bd.CloseAll(_conn, _ps, null);
		}
		return success;
	}
	
	/**
	 * Get the number of the department that is not deleted.
	 * @return the number.
	 */
	public int countAll()
	{	
		_logger.info("查询所有未删除部门总数...");
		String sql ="select count(deptNo) from department where deleted = '0'";
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		int count =0;
		try
		{
			_ps=_conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			_rs.next();
			count = _rs.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		
		return count;	
	}
	
	/**
	 * Get all the departments that not deleted in the database.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the department list.
	 */
	public List<Department> getAllDepartments(int startIndex ,int endIndex)
	{
		_logger.info("分页查询所有未删除员工信息...");
		return _getDepartmentsList("select * from (select U.*,ROWNUM RN from department U where deleted = 0 and ROWNUM <="+endIndex+" ) WHERE RN >= "+startIndex);	
	}
	
	/**
	 * Get all the departments that not deleted.
	 * @return the departments.
	 */
	public List<Department> getDepartments()
	{
		_logger.info("查询所有未删除员工信息...");
		return _getDepartmentsList("select * from department where deleted = 0 ");	
	}
	
	/**
	 * Get the departments list according to the specified sql.
	 * @param sql the sql.
	 * @return the department list.
	 */
	private List<Department> _getDepartmentsList(String sql)
	{
		List<Department> list = new ArrayList<Department>();
		Department department = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				department = new Department();
				department.setDeptNo(_rs.getInt(1));
				department.setdName(_rs.getString(2));
				department.setDescription(_rs.getString(3));
				department.setDeleted(_rs.getString(4));
				list.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
	
	/**
	 * Count the specified records according to the dName.
	 * @param dName the dName.
	 * @return the number.
	 */
	public int count(String dName)
	{	
		_logger.info("按部门名称查找部门总数...");
		String sql ="select count(deptNo) from department where dName like '%"+dName+"%'";
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		int count =0;
		try
		{
			_ps=_conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			_rs.next();
			count = _rs.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		
		return count;	
	}
	
	/**
	 * Get the departments like the specified dName.
	 * @param dName the dName.
	 * @param startIndex the startIndex.
	 * @param endIndex the startIndex.
	 * @return the department list.
	 */
	public List<Department> findDepartmentBydName(String dName,int startIndex ,int endIndex)
	{
		_logger.info("按部门名称查找部门信息...");
		String sql ="select * from (select U.*,ROWNUM RN from department U where dName like '%"+dName+"%' and ROWNUM <="+endIndex+" ) WHERE RN >= "+startIndex;
		return _getDepartmentsList(sql);	
	}
	
	/**
	 * Get the department according to the specified id.
	 * @param id the id.
	 * @return the department.
	 */
	public Department getDepartmentById(int id) 
	{
		_logger.info("按id查找部门信息...");
		Department department = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql = "select * from department where deptNo = ?";
		try 
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setInt(1, id);
			_rs = _ps.executeQuery();
			if (_rs.next())
			{
				department = new Department();
				department.setDeptNo(_rs.getInt(1));
				department.setdName(_rs.getString(2));
				department.setDescription(_rs.getString(3));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return department;
	}
	
	/**
	 * Get the department according to the specified name.
	 * @param dName the dName.
	 * @return the department.
	 */
	public Department getDepartmentBydName(String dName) 
	{
		_logger.info("按部门名查找部门信息...");
		Department department = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql = "select * from department where dName = ?";
		try 
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setString(1, dName);
			_rs = _ps.executeQuery();
			if (_rs.next())
			{
				department = new Department();
				department.setDeptNo(_rs.getInt(1));
				department.setdName(_rs.getString(2));
				department.setDescription(_rs.getString(3));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return department;
	}
	
	/**
	 * To update the department.
	 * @param department the department.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean updateDepartment(Department department)
	{
		_logger.info("修改部门信息...");
		return _getRows("update department set dName=?,description=? where deptNo = ?",department,true);
	}
}
