package com.demo.service;

import java.util.List;

import com.demo.dao.StaffDAO;
import com.demo.model.Staff;

/**
 * Service refers to the corresponding {@link StaffDAO} operations.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-2 上午10:35:19
 */
public class StaffManagement 
{
	/** The staffDAO. */
	private StaffDAO _staffDAO = new StaffDAO();

	/**
	 * To add staff.
	 * @param staff the staff.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean addStaff(Staff staff)
	{
		return _staffDAO.addStaff(staff);
	}
	
	/**
	 * To delete staff.
	 * @param arrayId the arrayId.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean deleteStaff(String[] arrayId)
	{
		return _staffDAO.deleteStaff(arrayId);
	}
	
	/**
	 * To edit the specified staff.
	 * @param staff the staff.
	 * @return <code>true</code> if success ,else <code>false</code>.
	 */
	public boolean updateStaff(Staff staff)
	{
		return _staffDAO.updateStaff(staff);
	}
	
	/**
	 * Get all the staffs as list.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the staff list.
	 */
	public List<Staff> getAllStaffs(int startIndex ,int endIndex)
	{
		return _staffDAO.getAllStaffs(startIndex, endIndex);
	}
	
	/**
	 * Get the staff by lName.
	 * @param fName the fName.
	 * @param lName the lName.
	 * @param startIndex the startIndex.
	 * @param endIndex the endIndex.
	 * @return the staff list.
	 */
	public List<Staff> findStaffByName(String fName, String lName, int startIndex, int endIndex)
	{
		return _staffDAO.findStaffByName(fName, lName, startIndex, endIndex);
	}
	
	/**
	 * Get the staff by id.
	 * @param id the id.
	 * @return the staff.
	 */
	public Staff getStaffById(int id)
	{
		return _staffDAO.getStaffById(id);
	}
	
	/**
	 * Get the count of the specified records.
	 * @return the count.
	 */
	public int countAll()
	{
		return _staffDAO.countAll();
	}
	
	/**
	 * Get the count of the specified records according to the name.
	 * @param fName the fName.
	 * @param lName the lName.
	 * @return the count.
	 */
	public int count(String fName, String lName){
		return _staffDAO.count(fName, lName); 
	}
}
