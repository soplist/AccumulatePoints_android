package com.jingrui.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class AccumulatePoint implements Parcelable {
    private int no;
    private String name;
    private String time;
    private String comment;
    private int score;
    private String updateTime;
    
    public AccumulatePoint(){
    	
    }
    public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(no);
		dest.writeString(name);
		dest.writeString(time);
		dest.writeString(comment);
		dest.writeInt(score);
		dest.writeString(updateTime);
	}
	
	public static final Parcelable.Creator<AccumulatePoint> CREATOR = new Creator<AccumulatePoint>()
    {
        @Override
        public AccumulatePoint[] newArray(int size)
        {
            return new AccumulatePoint[size];
        }
        
        @Override
        public AccumulatePoint createFromParcel(Parcel in)
        {
            return new AccumulatePoint(in);
        }
    };
    
    public AccumulatePoint(Parcel in)
    {
    	no = in.readInt();
		name = in.readString();
		time = in.readString();
		comment = in.readString();
		score = in.readInt();
		updateTime = in.readString();
    }
}
