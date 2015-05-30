package com.demo.model;

/**
 * 统计各部门员工人数bean。
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-27 下午02:24:38
 */
public class DeptStaffBean
{
	/** The department name. */
	private String _departmentName;
	/** The staff number of each department. */
	private int _num;

	/**
	 * Constructor.
	 */
	public DeptStaffBean()
	{
		super();
	}

	/**
	 * Get the departmentName.
	 * @return the departmentName.
	 */
	public String getDepartmentName()
	{
		return _departmentName;
	}

	/**
	 * Set the departmentName.
	 * @param departmentName the departmentName.
	 */
	public void setDepartmentName(String departmentName)
	{
		this._departmentName = departmentName;
	}

	/**
	 * Get the num.
	 * @return the num.
	 */
	public int getNum()
	{
		return _num;
	}

	/**
	 * Set the num.
	 * @param num the num.
	 */
	public void setNum(int num)
	{
		this._num = num;
	}
}
