package com.jingrui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

public class SQLServerConnector {
	public static Connection getConnection() {
		// TODO Auto-generated method stub
	    //String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
		//String dbURL = "jdbc:sqlserver://61.150.109.162:53433;DatabaseName=accumulate_points_DB";  
		String driverName = "net.sourceforge.jtds.jdbc.Driver";
		//String dbURL = "jdbc:jtds:sqlserver://61.150.109.162:53433/accumulate_points_DB;charset=utf-8"; 
		String dbURL = "jdbc:jtds:sqlserver://61.150.109.162:53433/accumulate_points_DB";
		
	    String userName = "sa";
	    String userPwd = "akjr3838968";
	 
	    Connection dbConn = null;  
	    try {  
	        Class.forName(driverName);  
	        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);  
	        System.out.println("Connection Successful!");
	    } catch (ClassNotFoundException e) {  
	    	Log.d("SQLServerConnector", "ClassNotFoundException:"+e);
	    	//Log.d("MainActivity",e.printStackTrace());  
	    }  catch (SQLException e) {  
	    	Log.d("SQLServerConnector", "SQLException");
	    	//Log.d("MainActivity",e.printStackTrace());  
	    }  catch (NetworkOnMainThreadException e) {  
	    	Log.d("SQLServerConnector", "NetworkOnMainThreadException");
	    	//Log.d("MainActivity",e.printStackTrace());  
	    }  
	    return dbConn;
	}
}
