package com.bobchen.admin.mhsapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 12/31/15.
 */
public class MHSGroup implements Parcelable {

    String groupName;
    int teams;

    public MHSGroup(String groupName, int teams){
        this.groupName = groupName;
        this.teams = teams;
    }

    //Parcelling Part

    //Makes a Constructor that works when given a Parcel
    public MHSGroup(Parcel in){
        String[] data = new String[2];

        in.readStringArray(data);
        this.groupName = data[0];
        this.teams = Integer.parseInt(data[1]);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    //Creates a parcel array with all the data
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.groupName,
                Integer.toString(this.teams),
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public MHSGroup createFromParcel(Parcel in){
            return new MHSGroup(in);
        }

        public MHSGroup[] newArray(int size){
            return new MHSGroup[size];
        }
    };
}
