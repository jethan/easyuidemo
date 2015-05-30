package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Counties of each city of each province in china.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-16 上午09:08:25
 */
@Entity
@Table(name = "area_county")
public class County
{
	/** The id. */
    private String id;
	/** The city id. */
    private String _cityId;
    /** The country name. */
    private String _countyName;

    /**
     * Get the id.
     * @return the id.
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "county_sequence")
    @SequenceGenerator(name = "county_sequence", sequenceName = "county_sequence")
    public String getId()
	{
		return id;
	}
	/**
	 * Set the id.
	 * @param id the id.
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * Get the cityId.
	 * @return the city id.
	 */
	public String getCityId()
	{
		return _cityId;
	}
	/**
	 * Set the city id.
	 * @param cityId the cityId.
	 */
	public void setCityId(String cityId)
	{
		_cityId = cityId;
	}
	/**
	 * Get the countyName.
	 * @return the countyName.
	 */
	public String getCountyName()
	{
		return _countyName;
	}
	/**
	 * Set the contryName.
	 * @param countyName the countyName.
	 */
	public void setCountyName(String countyName)
	{
		_countyName = countyName;
	}
}
