package com.demo.util;

import java.util.Map;

/**
 * Map to store the province, city, county. 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-19 下午09:51:16
 */
public class CacheMap
{
	/** The provinceMap. */
	public static Map<String,String> _provinceMap;
	/** The cityMap. */
	public static Map<String,String> _cityMap;
	/** The countyMap. */
	public static Map<String,String> _countyMap;
	/** The departmentMap. */
	public static Map<String, String> _departmentMap;
	
	/**
	 * Constructor.
	 * @param provinceMap the provinceMap.
	 * @param cityMap the cityMap.
	 * @param countyMap the countyMap.
	 * @param departmentMap the departmentMap.
	 */
	public CacheMap(final Map<String,String> provinceMap,final Map<String,String> cityMap,final Map<String,String> countyMap,final Map<String, String> departmentMap)
	{
		CacheMap._provinceMap = provinceMap;
		CacheMap._cityMap = cityMap;
		CacheMap._countyMap = countyMap;
		CacheMap._departmentMap = departmentMap;
	}
}
