package com.demo.model;
/**
 * 统计各部门男女员工所占比例bean.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-28 上午09:37:39
 */
public class DeptStaffGenderBean
{
	/** The department name. */
	private String _departmentName;
	/** The male staff number of each department. */
	private int _male;
	/** The female staff number of each department. */
	private int _female;
	
	/**
	 * Constructor.
	 */
	public DeptStaffGenderBean()
	{
		//nothing.
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
	 * Get the male.
	 * @return the male.
	 */
	public int getMale()
	{
		return _male;
	}

	/**
	 * Set the male.
	 * @param male the male.
	 */
	public void setMale(int male)
	{
		this._male = male;
	}

	/**
	 * Get the female.
	 * @return the female.
	 */
	public int getFemale()
	{
		return _female;
	}

	/**
	 * Set the female.
	 * @param female the female.
	 */
	public void setFemale(int female)
	{
		this._female = female;
	}
}
