package com.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.model.Staff;
import com.demo.util.BaseDAO;

/**
 * DAO for {@link Staff}
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-2 上午10:32:20
 */
public class StaffDAO {
	/** The logger. */
	private static Logger _logger = Logger.getLogger(StaffDAO.class);
	/** The connection. */
	private Connection _conn;
	/** The statement. */
	private PreparedStatement _ps;
	/** The resultSet. */
	private ResultSet _rs;
	/** The baseDAO. */
	private BaseDAO _bd;
	/** The date format. */
	private final SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Add new staffs.
	 * @param staff the staff.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean addStaff(Staff staff)
	{
		_logger.info("添加员工信息...");
		return _getRows("insert into staffinfo (id,fName,lName,gender,birthday,address,telephone,email,deleted,province,city,county,deptNo) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",staff,false);
	}

	/**
	 * Update the specified staff.
	 * @param staff the suer.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean updateStaff(Staff staff)
	{
		_logger.info("修改员工信息...");
		//to_date(?,'yyyy-MM-dd')
		return _getRows("update staffinfo set fName=?,lName=?,gender=?,birthday=?,address=?,telephone=?,email=?,province=?,city=?,county=?,deptNo=? where id = ?",staff,true);
	}
	
	/**
	 * Set the staff deleted status to true according to the specified id.
	 * @param arrayId the id.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean deleteStaff(String[] arrayId)
	{
		_logger.info("删除员工信息...");
		BaseDAO bd = new BaseDAO();
		boolean success = false;
		_conn = bd.getConnnection();
		for(int i=0;i<arrayId.length;i++)
		{
			int id = Integer.parseInt(arrayId[i]);
		
			String sql = "update staffinfo set deleted=? where id = ?";		
			
				try
				{
					_ps = _conn.prepareStatement(sql);
					_ps.setBoolean(1, true);	
					_ps.setInt(2, id);			
					_ps.executeUpdate();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				success = true;
			
		}
		bd.CloseAll(_conn, _ps, null);
		
		return success;		
	}
	
	/**
	 * To add or update a staff.
	 * @param sql the sql.
	 * @param staff the staff.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	private boolean _getRows(String sql,Staff staff,boolean update)
	{
		BaseDAO bd = new BaseDAO();
		boolean success = false;
		_conn = bd.getConnnection();
		try
		{
			if (update)
			{
				_ps = _conn.prepareStatement(sql);
				_ps.setString(1, staff.getfName());
				_ps.setString(2, staff.getlName());
				_ps.setString(3, staff.getGender());
				if(!"".equals(staff.getBirthday()))
				{
					_ps.setDate(4, new Date(_format.parse(staff.getBirthday()).getTime()));
				}
				else
				{
					_ps.setDate(4,null);
				}
				_ps.setString(5, staff.getAddress());
				_ps.setString(6, staff.getTelephone());
				_ps.setString(7, staff.getEmail());
				_ps.setString(8, staff.getProvince());
				_ps.setString(9, staff.getCity());
				_ps.setString(10, staff.getCounty());
				_ps.setInt(11, staff.getDeptNo());
				_ps.setInt(12, staff.getId());
			} else
			{
				String sqlId = "select staff_sequences.nextval from staffinfo";
				_ps = _conn.prepareStatement(sqlId);
				ResultSet rs = _ps.executeQuery();
				rs.next();
				int id = rs.getInt(1);
				rs.close();
				_ps.close();
				_ps = _conn.prepareStatement(sql);
				_ps.setInt(1, id);
				_ps.setString(2, staff.getfName());
				_ps.setString(3, staff.getlName());
				_ps.setString(4, staff.getGender());
				if(!"".equals(staff.getBirthday()))
				{
					_ps.setDate(5, new Date(_format.parse(staff.getBirthday()).getTime()));
				}
				else
				{
					_ps.setDate(5,null);
				}
				_ps.setString(6, staff.getAddress());
				_ps.setString(7, staff.getTelephone());
				_ps.setString(8, staff.getEmail());
				_ps.setString(9, staff.isDeleted());
				_ps.setString(10, staff.getProvince());
				_ps.setString(11, staff.getCity());
				_ps.setString(12, staff.getCounty());
				_ps.setInt(13, staff.getDeptNo());
			}
			
			_ps.executeUpdate();
			success = true;
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ParseException e)
		{
			e.printStackTrace();
		}finally{
			bd.CloseAll(_conn, _ps, null);
		}
		return success;
	}
	
	/**
	 * Get the Staff according to the specified id.
	 * 
	 * @param id the id.
	 * @return the staffInfo.
	 */
	public Staff getStaffById(int id) 
	{
		_logger.info("按id查找员工信息...");
		Staff staff = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		String sql = "select * from staffinfo where id = ?";
		try 
		{
			_ps = _conn.prepareStatement(sql);
			_ps.setInt(1, id);
			_rs = _ps.executeQuery();
			if (_rs.next())
			{
				staff = new Staff();
				staff.setId(_rs.getInt(1));
				staff.setfName(_rs.getString(2));
				staff.setlName(_rs.getString(3));
				staff.setGender(_rs.getString(4));
				if(_rs.getDate(5)!=null)
				{
					staff.setBirthday(_format.format(_rs.getDate(5)));
				}
				staff.setAddress(_rs.getString(6));
				staff.setTelephone(_rs.getString(7));
				staff.setEmail(_rs.getString(8));
				staff.setProvince(_rs.getString(10));
				staff.setCity(_rs.getString(11));
				staff.setCounty(_rs.getString(12));
				staff.setDeptNo(_rs.getInt(13));
				
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return staff;
	}
	
	/**
	 * Get all the staffs that not deleted in the database.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the staffInfo list;
	 */
	public List<Staff> getAllStaffs(int startIndex ,int endIndex)
	{
		_logger.info("查询所有未删除员工信息...");
		return _getStaffsList("select * from (select U.*,ROWNUM RN from staffinfo U where deleted = 0 and ROWNUM <="+endIndex+" ) WHERE RN >= "+startIndex);	
	}
	
	/**
	 * Count the specified records.
	 * @return the count.
	 */
	public int countAll()
	{	
		_logger.info("查询所有未删除员工总数...");
		String sql ="select count(id) from staffinfo where deleted = '0'";
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
	 * Get the staffs like the specified lName.
	 * @param fName the fName.
	 * @param lName the lName.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the list.
	 */
	public List<Staff> findStaffByName(String fName, String lName,int startIndex ,int endIndex)
	{
		_logger.info("按姓名查找员工信息...");
		String sql = "";
		if(!"".equals(fName) || !"".equals(lName))
		{
			sql ="select * from (select U.*,ROWNUM RN from staffinfo U where lName like '%"+lName+"%' and fName like '%"+fName+"%' and deleted=0 and ROWNUM <="+endIndex+" ) WHERE RN >= "+startIndex;
		}
		else
		{
			sql ="select * from (select U.*,ROWNUM RN from staffinfo U where ROWNUM <="+endIndex+" and deleted=0) WHERE RN >= "+startIndex;
		}
		return _getStaffsList(sql);	
	}
	
	/**
	 * Count the specified records according to the lName.
	 * @param fName the fName.
	 * @param lName the lName.
	 * @return the count.
	 */
	public int count(String fName, String lName)
	{	
		_logger.info("按姓名查找员工总数...");
		String sql ="select count(id) from staffinfo where lName like '%"+lName+"%' and fName like '%"+fName+"%' and deleted=0";
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
	 * Get the staffs list according to the specified sql.
	 * @param sql the sql.
	 * @return the staffs list.
	 */
	private List<Staff> _getStaffsList(String sql)
	{
		List<Staff> list = new ArrayList<Staff>();
		Staff staff = null;
		_bd = new BaseDAO();
		_conn = _bd.getConnnection();
		try {
			_ps = _conn.prepareStatement(sql);
			_rs = _ps.executeQuery();
			while (_rs.next()) {
				staff = new Staff();
				staff.setId(_rs.getInt(1));
				staff.setfName(_rs.getString(2));
				staff.setlName(_rs.getString(3));
				staff.setGender(_rs.getString(4));
				if(_rs.getDate(5)!=null)
				{
					staff.setBirthday(_format.format(_rs.getDate(5)));
				}
				else
				{
					staff.setBirthday("");
				}
				staff.setAddress(_rs.getString(6));
				staff.setTelephone(_rs.getString(7));
				staff.setEmail(_rs.getString(8));
				staff.setDeleted(_rs.getString(9));
				staff.setProvince(_rs.getString(10));
				staff.setCity(_rs.getString(11));
				staff.setCounty(_rs.getString(12));
				staff.setDeptNo(_rs.getInt(13));
				list.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_bd.CloseAll(_conn, _ps, _rs);
		}
		return list;
	}
}

