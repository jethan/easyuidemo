package com.demo.util;

import java.sql.*;


import org.apache.log4j.Logger;

import com.demo.dao.StaffDAO;

/**
 * BaseDAO for {@link StaffDAO}.
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-1 下午11:14:20
 */
public class BaseDAO
{
	/** The logger. */
	private static Logger _logger = Logger.getLogger(BaseDAO.class);
	
	/**
	 * Get the connection to the specified database.
	 * @return
	 */
	public Connection getConnnection()
	{
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			_logger.error("驱动装载失败！");
			e.printStackTrace();
		}
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		} catch (SQLException e) {
			_logger.error("驱动加载或数据库连接失败！");
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Close the connection to the database.
	 * @param connection the connection.
	 * @param preparedStatement the preparedStatement.
	 * @param resultSet the resultSet.
	 */
	public void CloseAll(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet)
	{
		if(connection!=null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				_logger.error("数据库连接关闭失败！");
				e.printStackTrace();
			}
		}
		
		if(preparedStatement!=null)
		{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				_logger.error("关闭PreparedStatement对象失败！");
				e.printStackTrace();
			}
		}
		
		if(resultSet!=null)
		{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				_logger.error("结果集关闭失败！");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BaseDAO db = new BaseDAO();
		Connection con = db.getConnnection();
		if(con!=null){
			_logger.info("数据库连接成功！");
		}
	}
}
