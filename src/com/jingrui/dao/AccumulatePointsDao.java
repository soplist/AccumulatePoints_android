package com.jingrui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jingrui.entity.AccumulatePoint;

public class AccumulatePointsDao {
    public ArrayList<AccumulatePoint> getAccumulatePointsByNameAndMonth(String name,String month){
    	// TODO Auto-generated method stub
    	ArrayList<AccumulatePoint> accumulatePointList = new ArrayList<AccumulatePoint>();
        Connection conn = SQLServerConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from accumulate_points_DB_value where name='"+name+"' and convert(varchar(10),time,20) like '"+month+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	AccumulatePoint ap = new AccumulatePoint();
            	ap.setNo(rs.getInt("no"));
            	ap.setName(rs.getString("name").trim());
            	
            	Timestamp ts1 = rs.getTimestamp("time");
            	Date date1 = new Date(ts1.getTime());
            	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
                String strDate1 = format1.format(date1);
            	ap.setTime(strDate1);
            	
            	ap.setComment(rs.getString("comment").trim());
            	ap.setScore(rs.getInt("score"));
            	
            	Timestamp ts2 = rs.getTimestamp("update_time");
            	Date date2 = new Date(ts2.getTime());
            	DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                String strDate2 = format2.format(date2);
            	ap.setUpdateTime(strDate2);
            	
            	accumulatePointList.add(ap);
            	//ap.setUpdateTime(rs.);
                //System.out.println("no:" + rs.getInt("no") + ",name:"+ rs.getString("real_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    	return accumulatePointList;
    }
    
    public ArrayList<String> getAccumulatePointsMonthsByName(String name){
    	// TODO Auto-generated method stub
    	ArrayList<String> months = new ArrayList<String>();
        Connection conn = SQLServerConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select convert(varchar(7),time,120 ) as months from accumulate_points_DB_value where name='"+name+"' group by convert(varchar(7),time,120 )";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	months.add(rs.getString("months"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    	return months;
    }
    //select convert(varchar(7),time,120 ) from accumulate_points_DB_value where name='ñc∞≤“Â' group by convert(varchar(7),time,120 );
}
