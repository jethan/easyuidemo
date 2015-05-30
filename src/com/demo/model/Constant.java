package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 常量数据字典。
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-16 下午11:55:45
 */
@Entity
@Table(name = "constant")
public class Constant
{
	/** The id. */
    private int id;
	/** The title. */
    private String _title;
    /** The value. */
    private String _value;
    /** The checked. */
    private String _checked;
	/** The type. */
    private String _type;
    
    /**
     * Constructor.
     */
    public Constant()
	{
		//nothing.
	}
    
	/**
	 * Get the id.
	 * @return the id.
	 */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "constant_sequence")
    @SequenceGenerator(name = "constant_sequence", sequenceName = "constant_sequence")
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
	 * Get the title.
	 * @return the title.
	 */
	public String getTitle()
	{
		return _title;
	}
	
	/**
	 * Set the title.
	 * @param title the title.
	 */
	public void setTitle(String title)
	{
		_title = title;
	}
	
	/**
	 * Get the value.
	 * @return the _value.
	 */
	public String getValue()
	{
		return _value;
	}
	
	/**
	 * Set the value.
	 * @param value the value.
	 */
	public void setValue(String value)
	{
		_value = value;
	}
	
	/**
	 * Get the checked.
	 * @return the checked.
	 */
	public String getChecked()
	{
		return _checked;
	}

	/**
	 * Set the checked.
	 * @param checked the checked.
	 */
	public void setChecked(String checked)
	{
		_checked = checked;
	}
	
	/**
	 * Get the type.
	 * @return the type.
	 */
	public String getType()
	{
		return _type;
	}
	
	/**
	 * Set the type.
	 * @param type the type.
	 */
	public void setType(String type)
	{
		_type = type;
	}
}
