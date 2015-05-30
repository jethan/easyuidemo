package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Cities of each province in china.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-16 上午09:08:14
 */
@Entity
@Table(name = "area_city")
public class City
{
	/** The id. */
    private String id;
    /** The province id. */
    private String _provinceId;
    /** The city name. */
    private String _cityName;

    /**
     * Get the id.
     * @return the id.
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city_sequence")
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence")
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
	 * Get the provinceId.
	 * @return the provinceId.
	 */
	public String getProvinceId()
	{
		return _provinceId;
	}
	/**
	 * Set the provinceId.
	 * @param provinceId the provinceId.
	 */
	public void setProvinceId(String provinceId)
	{
		_provinceId = provinceId;
	}
	/**
	 * Get the cityName.
	 * @return the cityName.
	 */
	public String getCityName()
	{
		return _cityName;
	}
	/**
	 * Set the cityName.
	 * @param cityName the cityName.
	 */
	public void setCityName(String cityName)
	{
		_cityName = cityName;
	}
}
