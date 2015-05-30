package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 部门信息。
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-16 下午11:55:16
 */
@Entity
@Table(name = "department")
public class Department
{
	/** The department no. */
    private int _deptNo;
    /** The department name. */
    private String _dName;
    /** The department description. */
    private String _description;
    /** The deleted. */
    private String _deleted;
    
    /**
     * Constructor.
     */
	public Department()
	{
		//nothing.
	}
	
	/**
	 * Get the deptNo.
	 * @return the deptNo.
	 */
	@Id
    @Column(name = "userNo", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "deptNo_sequence")
    @SequenceGenerator(name = "deptNo_sequence", sequenceName = "deptNo_sequence")
	public int getDeptNo()
	{
		return _deptNo;
	}
	
	/**
	 * Set the deptNo.
	 * @param deptNo the deptNo.
	 */
	public void setDeptNo(int deptNo)
	{
		_deptNo = deptNo;
	}
	
	/**
	 * Get the dName.
	 * @return the _dName.
	 */
	public String getdName()
	{
		return _dName;
	}
	
	/**
	 * Set the dName.
	 * @param dName the dName.
	 */
	public void setdName(String dName)
	{
		_dName = dName;
	}

	/**
	 * Get the description.
	 * @return the description.
	 */
	public String getDescription()
	{
		return _description;
	}

	/**
	 * Set the description.
	 * @param description the description.
	 */
	public void setDescription(String description)
	{
		this._description = description;
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
