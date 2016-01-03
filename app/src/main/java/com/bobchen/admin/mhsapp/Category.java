package com.bobchen.admin.mhsapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 12/31/15.
 */
public class Category implements Parcelable {

    String categoryName, nameNS;
    int rank;

    public Category(String categoryName, String nameNS, int rank){
        this.categoryName = categoryName;
        this.nameNS = nameNS;
        this.rank = rank;
    }

    //Parcelling PArt

    //Makes a Constructor that works when given a Parcel
    public Category(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.categoryName = data[0];
        this.nameNS = data[1];
        this.rank = Integer.parseInt(data[2]);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    //Creates a parcel array with all the data
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.categoryName,
                this.nameNS,
                Integer.toString(this.rank),
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Category createFromParcel(Parcel in){
            return new Category(in);
        }

        public Category[] newArray(int size){
            return new Category[size];
        }
    };

}
