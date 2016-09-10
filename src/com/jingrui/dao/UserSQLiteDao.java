package com.jingrui.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserSQLiteDao {
	private SQLiteDatabase db;
    public UserSQLiteDao(SQLiteDatabase db){
    	this.db = db;
    }
    public String getUserName(){
    	String name = "";
    	Cursor cursor = db.query("User", null, null, null, null, null, null);
    	if(cursor.moveToFirst()){
    		do {
				name = cursor.getString(cursor.getColumnIndex("name"));
			} while (cursor.moveToNext());
    	}
    	return name;
    }
    public void insertUserName(String name){
    	ContentValues values = new ContentValues();
    	values.put("name", name);
    	db.insert("User", null, values);
    }
    public void updateUserName(String name){
    	ContentValues values = new ContentValues();
    	values.put("name", name);
    	db.update("User", values, null, null);
    }
}
