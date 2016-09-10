package com.jingrui.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Date date = new Date();
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        DateFormat format = new SimpleDateFormat("yyyy-MM-");
        String str = format.format(date); //2013-01-14  
        System.out.println(str);
	}

}
