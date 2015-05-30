package com.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.Admin;
import com.demo.util.BaseDAO;


/**
 * DAO for {@link Admin}.
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-20 上午10:34:24
 */
public class AdminDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(AdminDAO.class);
	/** The baseDAO. */
	private BaseDAO _bd;
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	/** The date format. */
	private final SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
	private final int count = -1;
	// 声明时间变量
	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
			.getInstance().getTime());
	private List list;

	/**
	 * Validate if the user legal or not.
	 * @param userName the userName.
	 * @param password password
	 * @return
	 */
	public boolean validateLogin(String userName, String password)
	{
		boolean flag = false;
		String sql = "select password from ADMIN where userName = ?";
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		try
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setString(1, userName);
			_rs = _ps.executeQuery();
			if(_rs.next())
			{
				String str = _rs.getString("password");
				if(str.trim().equals(password))
				{
				 flag = true;
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return flag;
	}
	
	/**
	 * To get the password of the specified admin user.
	 * @param userNo the userNo.
	 * @return the password.
	 */
	public String getPassword(int userNo)
	{
		String sql = "select password from ADMIN where userNo = ?";
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String password = "";
		try
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setInt(1, userNo);
			_rs = _ps.executeQuery();
			if(_rs.next())
			{
				password = _rs.getString("password");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return password;
	}
	
	/**
	 * Get the admin information according to the specified userName.
	 * @param userName the userName.
	 * @return the admin.
	 */
	public Admin getAdminInfo(String userName)
	{
		_logger.info("按用户名查找用户...");
		String sql = "select * from ADMIN where userName= ?";
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		Admin admin = null;
		try
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setString(1, userName);
			_rs = _ps.executeQuery();
			if (_rs.next())
			{
				admin = new Admin();
				admin.setUserNo(_rs.getInt(1));
				admin.setUserName(_rs.getString(2));
				admin.setPassword(_rs.getString(3));
				admin.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(_rs.getDate(4)));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return admin;
	}
	
	/**
	 * To get the admin user according to the specified userNo.
	 * @param userNo the userNo.
	 * @return the admin.
	 */
	public Admin getAdmin(int userNo)
	{
		_logger.info("按用户名查找用户...");
		String sql = "select * from ADMIN where userNo= ?";
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		Admin admin = null;
		try
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setInt(1, userNo);
			_rs = _ps.executeQuery();
			if (_rs.next())
			{
				admin = new Admin();
				admin.setUserNo(_rs.getInt(1));
				admin.setUserName(_rs.getString(2));
				admin.setPassword(_rs.getString(3));
				admin.setCreateTime(_format.format(_rs.getDate(4)));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return admin;
	}
	
	/**
	 * To update the specified admin.
	 * @param admin the admin.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean updateAdmin(Admin admin)
	{
		_logger.info("修改管理员密码...");
		return _getRows("update admin set password=? where userNo = ?",admin,true);
	}
	
	/**
	 * To add or update the admin user.
	 * @param sql the sql.
	 * @param admin the admin.
	 * @param update the update.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	private boolean _getRows(String sql,Admin user,boolean update)
	{
		BaseDAO bd = new BaseDAO();
		boolean success = false;
		_conn = bd.getConnnection();
		try
		{
			if (update)
			{
				_ps = _conn.prepareStatement(sql);
				_ps.setString(1, user.getPassword());
				_ps.setInt(2, user.getUserNo());
			} else
			{
				String sqlId = "select ADMIN_SEQUENCES.nextval from admin";
				_ps = _conn.prepareStatement(sqlId);
				ResultSet rs = _ps.executeQuery();
				rs.next();
				int userNo = rs.getInt(1);
				rs.close();
				_ps.close();
				_ps = _conn.prepareStatement(sql);
				_ps.setInt(1, userNo);
				_ps.setString(2, user.getUserName());
				_ps.setString(3, user.getPassword());
				_ps.setDate(4, new Date(_format.parse(user.getCreateTime()).getTime()));
				_ps.setString(5, user.isDeleted());
			}
			
			if(_ps.executeUpdate()!=0);
			{
				success = true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ParseException e)
		{
			e.printStackTrace();
		} finally{
			bd.CloseAll(_conn, _ps, null);
		}
		return success;
	}
	
	/**
	 * Get the number of the user that is not deleted.
	 * @return the number.
	 */
	public int countAll()
	{	
		_logger.info("查询所有未删除用户总数...");
		String sql ="select count(userNo) from admin where deleted = '0'";
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
	 * Get all the users that not deleted in the database.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the user list.
	 */
	public List<Admin> getAllUsers(int startIndex ,int endIndex)
	{
		_logger.info("查询所有未删除用户信息...");
		return _getUsersList("select * from (select U.*,ROWNUM RN from admin U where deleted = 0 and ROWNUM <="+endIndex+" ) WHERE RN >= "+startIndex);	
	}
	
	/**
	 * Get the users list according to the specified sql.
	 * @param sql the sql.
	 * @return the sql.
	 */
	private List<Admin> _getUsersList(String sql)
	{
		List<Admin> list = new ArrayList<Admin>();
		Admin user = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				user = new Admin();
				user.setUserNo(_rs.getInt(1));
				user.setUserName(_rs.getString(2));
				user.setPassword(_rs.getString(3));
				user.setCreateTime(_format.format(_rs.getDate(4)));
				user.setDeleted(_rs.getString(5));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
	
	/**
	 * To add user.
	 * @param user the user.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean addUser(Admin user)
	{
		_logger.info("添加用户...");
		return _getRows("insert into ADMIN (userNo,userName,password,createTime,deleted) values(?,?,?,?,?)",user,false);
	}
	
	/**
	 * Count the specified records according to the userName.
	 * @param userName the userName.
	 * @return the number.
	 */
	public int count(String userName)
	{	
		_logger.info("按用户名称查找用户总数...");
		String sql ="select count(userNo) from admin where userName like '%"+userName+"%'";
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
	 * Get the users like the specified userName.
	 * @param userName the userName.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the user list.
	 */
	public List<Admin> findAdminByUserName(String userName,int startIndex ,int endIndex)
	{
		_logger.info("按部门名称查找部门信息...");
		String sql ="select * from (select U.*,ROWNUM RN from Admin U where userName like '%"+userName+"%' and ROWNUM <="+endIndex+" ) WHERE RN >= "+startIndex;
		return _getUsersList(sql);	
	}
}
