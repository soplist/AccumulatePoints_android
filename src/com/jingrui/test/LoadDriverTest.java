package com.jingrui.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.util.Log;

public class LoadDriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driverName = "net.sourceforge.jtds.jdbc.Driver";
		String dbURL = "jdbc:jtds:sqlserver://61.150.109.162:53433/accumulate_points_DB;charset=utf8";  
		
	    String userName = "sa";
	    String userPwd = "akjr3838968";
	 
	    Connection dbConn = null;  
	    try {  
	        Class.forName(driverName);  
	        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);  
	        System.out.println("Connection Successful!");
	    } catch (ClassNotFoundException e) {  
	    	//Log.d("SQLServerConnector", "ClassNotFoundException");
	    	//Log.d("MainActivity",e.printStackTrace());  
	    	e.printStackTrace();
	    }  catch (SQLException e) {  
	    	//Log.d("SQLServerConnector", "SQLException");
	    	//Log.d("MainActivity",e.printStackTrace());  
	    	e.printStackTrace();
	    }  
	}

}
