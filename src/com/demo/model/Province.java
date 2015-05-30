package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Provinces of china.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-16 上午09:07:58
 */
@Entity
@Table(name = "area_province")
public class Province
{
	/** The id. */
    private String id;
	/** The province name. */
    private String _provinceName;

    /**
     * Get the id.
     * @return the id.
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "province_sequence")
    @SequenceGenerator(name = "province_sequence", sequenceName = "province_sequence")
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
	 * Get the provinceName.
	 * @return the provinceName.
	 */
	public String getProvinceName()
	{
		return _provinceName;
	}
	
	/**
	 * Set the provinceName.
	 * @param provinceName the provinceName.
	 */
	public void setProvinceName(String provinceName)
	{
		_provinceName = provinceName;
	}
}
