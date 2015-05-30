package com.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Validate the input legal or not.
 * 
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-10 下午02:07:00
 */
public class ValidatorUtil
{
    /** The ValidatorUtils instance. */
    static ValidatorUtil _validatorUtils = new ValidatorUtil();

    /**
     * Get the ValidatorUtil instance.
     * @return  the validatorUtils.
     */
    public static ValidatorUtil getInstance()
    {
        return _validatorUtils;
    }

    /**
     * Validate the field null or not.
     * @param parameter the parameter.
     * @return <code>true</code> if parameter is not null ,else <code>false</code>.
     */
    public boolean requiredValidator(String parameter)
    {
		return parameter.length()==0;
    }
    
	/**
	 * Validate the dateTime legal or not.
	 * @param birthday the birthday.
	 * @return <code>true</code> if birthday is legal ,else <code>false</code>.
	 */
	public boolean dateTimeValidator(String birthday)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		@SuppressWarnings("unused")
		Date d = null;
		if (birthday != null && !birthday.equals(""))
		{
			if (birthday.split("-").length > 1)
			{
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try
				{
					d = dateFormat.parse(birthday);
				} catch (ParseException e)
				{
					e.printStackTrace();
				}
			}
		} 
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]" +
				"?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])" +
				"|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])" +
				"|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])" +
				"|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])" +
				"|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))" +
				"|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\" +
				":([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(birthday);
		
		return birthday.equals("") || m.matches();
	}
	
	/**
	 * Validate the phone legal or not.
	 * @param phone the phone.
	 * @return <code>true</code> if phone is legal ,else <code>false</code>.
	 */
	public boolean phoneValidator(String phone)
	{
		Pattern phoneValidate = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}$");
		
		return phone.equals("") || phoneValidate.matcher(phone).matches();
	}
	
	/**
	 * Validate the email legal or not.
	 * @param email the email.
	 * @return <code>true</code> if phone is legal ,else <code>false</code>.
	 */
	public boolean emailValidator(String email)
	{
		Pattern emailValidate = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");

		return email.equals("") || emailValidate.matcher(email).matches();
	}
}
