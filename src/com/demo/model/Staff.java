package com.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.demo.util.CacheMap;
import com.sun.istack.internal.NotNull;

/**
 * Model for {@link Staff}.
 *
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 15-3-31 下午11:23
 */
@Entity
@Table(name = "staff")
public class Staff implements Serializable 
{
	/* The serialVersionUID. */
	private static final long serialVersionUID = 8429327937885987376L;
	
	/** The id. */
    private int id;
    /** The first name. */
    private String _fName;
    /** The last name. */
    private String _lName;
    /** The gender. */
    private String _gender;
    /** The birthday. */
    private String _birthday;
    /** The province. */
    private String _province;
    /** The city. */
    private String _city;
    /** The county. */
    private String _county;
    /** The address. */
    private String _address;
    /** The telephone. */
    private String _telephone;
    /** The email. */
    private String _email;
    /** The deleted. */
    private String _deleted;
    /** The deptNo. */
    private int _deptNo;
    /** The provinceStr used to show the province name in the dataGrid. */
    private String _provinceStr;
    /** The cityStr used to show the city name in the dataGrid. */
    private String _cityStr ;
    /** The countyStr used to show the county name in the dataGrid. */
    private String _countyStr ;
    /** The departmentStr used to show the department name in the dataGrid. */
    private String _departmentStr ;

	/**
     * Constructor.
     */
    public Staff() {
	}

    /**
     * Get the id.
     * @return the id.
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "staff_sequence")
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence")
    public int getId()
    {
        return id;
    }

    /**
     * Set the id.
     * @param id the id.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get the fName.
     * @return the fName.
     */
    @Column(name = "fName")
    @NotNull
    public String getfName()
    {
        return _fName;
    }

    /**
     * Set the fName.
     * @param fName the fName.
     */
    public void setfName(String fName)
    {
        _fName = fName;
    }

    /**
     * Get the lName.
     * @return the lName.
     */
    @Column(name = "lName")
    @NotNull
    public String getlName()
    {
        return _lName;
    }

    /**
     * Set the lName.
     * @param lName the lName.
     */
    public void setlName(String lName)
    {
        _lName = lName;
    }

    /**
     * Get the gender.
     * @return the gender.
     */
    @Column(name = "gender")
    public String getGender()
    {
        return _gender;
    }

    /**
     * Set the gender.
     * @param gender the gender.
     */
    public void setGender(String gender)
    {
    	_gender = gender;
    }

    /**
     * Get the birthday.
     * @return the birthday.
     */
    @Column(name = "birthday", length = 19)
    @NotNull
    public String getBirthday()
    {
        return _birthday;
    }

    /**
     * Set the birthday.
     * @param birthday the birthday.
     */
    public void setBirthday(String birthday)
    {
        _birthday = birthday;
    }

    /**
	 * Get the province.
	 * @return the province.
	 */
	@Column(name = "province")
	@NotNull
	public String getProvince()
	{
		
		return _province;
	}

	/**
	 * Set the province.
	 * @param province the province.
	 */
	public void setProvince(String province)
	{
		this._provinceStr=CacheMap._provinceMap.get(province);
		_province = province;
	}

	/**
	 * Get the city.
	 * @return the city.
	 */
	@Column(name = "city")
	@NotNull
	public String getCity()
	{
		return _city;
	}

	/**
	 * Set the city.
	 * @param city the city.
	 */
	public void setCity(String city)
	{
		this._cityStr =CacheMap._cityMap.get(city);
		_city = city;
	}

	/**
	 * Get the county.
	 * @return the county.
	 */
	@Column(name = "county")
	@NotNull
	public String getCounty()
	{
		return _county;
	}

	/**
	 * Set the county.
	 * @param county the county.
	 */
	public void setCounty(String county)
	{
		this._countyStr = CacheMap._countyMap.get(county);
		_county = county;
	}

	/**
     * Get the address.
     * @return the address.
     */
    @Column(name = "address")
    public String getAddress()
    {
        return _address;
    }

    /**
     * Set the address.
     * @param address the address.
     */
    public void setAddress(String address)
    {
        _address = address;
    }

    /**
     * Get the telephone.
     * @return the telephone.
     */
    @Column(name = "telephone")
    public String getTelephone()
    {
        return _telephone;
    }

    /**
     * Set the telephone.
     * @param telephone
     */
    public void setTelephone(String telephone)
    {
        _telephone = telephone;
    }

    /**
     * Get the email.
     * @return the email.
     */
    @Column(name = "email")
    public String getEmail()
    {
        return _email;
    }

    /**
     * set the email.
     * @param email the email.
     */
    public void setEmail(String email)
    {
        _email = email;
    }
    
	/**
	 * Is deleted or nor.
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
		_deleted = deleted;
	}
	
	/**
	 * Get the deptNo.
	 * @return the deptNo.
	 */
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
		this._departmentStr = CacheMap._departmentMap.get(String.valueOf(deptNo));
		this._deptNo = deptNo;
	}
	
	/**
	 * Get the provinceStr.
	 * @return the provinceStr.
	 */
	public String getProvinceStr()
	{
		return _provinceStr;
	}

	/**
	 * Set the provinceStr.
	 * @param provinceStr the provinceStr.
	 */
	public void setProvinceStr(String provinceStr)
	{
		this._provinceStr = provinceStr;
	}

	/**
	 * Get the cityStr.
	 * @return the cityStr.
	 */
	public String getCityStr()
	{
		return _cityStr;
	}

	/**
	 * Set the cityStr.
	 * @param cityStr the cityStr.
	 */
	public void setCityStr(String cityStr)
	{
		this._cityStr = cityStr;
	}

	/**
	 * Get the countyStr.
	 * @return the countyStr.
	 */
	public String getCountyStr()
	{
		return _countyStr;
	}

	/**
	 * Set the countyStr.
	 * @param countyStr the countyStr.
	 */
	public void setCountyStr(String countyStr)
	{
		this._countyStr = countyStr;
	}
	
	/**
	 * Get the departmentStr.
	 * @return the departmentStr.
	 */
	public String getDepartmentStr()
	{
		return _departmentStr;
	}

	/**
	 * Set the departmentStr.
	 * @param departmentStr the departmentStr.
	 */
	public void setDepartmentStr(String departmentStr)
	{
		_departmentStr = departmentStr;
	}
}
