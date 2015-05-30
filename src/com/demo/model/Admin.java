package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 管理员登陆信息.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-20 下午01:04:39
 */
@Entity
@Table(name = "admin")
public class Admin
{
    /** The userNo. */
    private int _userNo;
    /** The user name. */
    private String _userName;
	/** The password. */
    private String _password;
    /** The create time. */
    private String _createTime;
    /** The deleted. */
    private String _deleted;
	
	/**
	 * Get the userNo.
	 * @return the userNo.
	 */
    @Id
    @Column(name = "userNo", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userNo_sequence")
    @SequenceGenerator(name = "userNo_sequence", sequenceName = "userNo_sequence")
	public int getUserNo()
	{
		return _userNo;
	}
	
	/**
	 * Set the userNo.
	 * @param userNo the userNo.
	 */
	public void setUserNo(int userNo)
	{
		_userNo = userNo;
	}
	
	/**
	 * Get the userName.
	 * @return the userName.
	 */
	public String getUserName()
	{
		return _userName;
	}
	
	/**
	 * Set the userName.
	 * @param userName the userName.
	 */
	public void setUserName(String userName)
	{
		_userName = userName;
	}
	
	/**
	 * Get the password.
	 * @return the password.
	 */
	public String getPassword()
	{
		return _password;
	}
	
	/**
	 * Set the password.
	 * @param password the password.
	 */
	public void setPassword(String password)
	{
		_password = password;
	}
	
	/**
	 * Get the createTime.
	 * @return the createTime.
	 */
	public String getCreateTime()
	{
		return _createTime;
	}

	/**
	 * Set the createTime.
	 * @param createTime the createTime.
	 */
	public void setCreateTime(String createTime)
	{
		this._createTime = createTime;
	}
	
	/**
	 * Get the deleted.
	 * @return the deleted.
	 */
	@Column(name = "deleted")
	public String isDeleted()
	{
		return _deleted;
	}

	/**
	 * Set the deleted.
	 * @param deleted the deleted.
	 */
	public void setDeleted(String deleted)
	{
		this._deleted = deleted;
	}
}
