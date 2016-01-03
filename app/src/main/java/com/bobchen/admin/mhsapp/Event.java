package com.bobchen.admin.mhsapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 12/30/15.
 */
public class Event implements Parcelable{

    String MHSTeam, teamRank, eventName, eventDate, eventTime, eventLocation, eventAdmission;


    public Event(String MHSTeam, String teamRank, String eventName, String eventDate, String eventTime, String location, String admission){
        this.MHSTeam = MHSTeam;
        this.teamRank = teamRank;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = location;
        this.eventAdmission = admission;
    }

    //Parcelling Part


    //Makes a Constructor that works when given a Parcel
    public Event(Parcel in){
        String[] data = new String[7];

        in.readStringArray(data);
        this.MHSTeam = data[0];
        this.teamRank = data[1];
        this.eventName = data[2];
        this.eventDate = data[3];
        this.eventTime = data[4];
        this.eventLocation = data[5];
        this.eventAdmission = data[6];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    //Creates a parcel array with all the data
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeStringArray(new String[]{
                this.MHSTeam,
                this.teamRank,
                this.eventName,
                this.eventDate,
                this.eventTime,
                this.eventLocation,
                this.eventAdmission
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Event createFromParcel(Parcel in){
            return new Event(in);
        }

        public Event[] newArray(int size){
            return new Event[size];
        }
    };

}
