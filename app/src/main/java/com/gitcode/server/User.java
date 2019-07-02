package com.gitcode.server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MuQuan on 2019/7/1 0001.
 * Features：
 */
public class User implements Parcelable {

    private String username;

    private String address;

    public User() {
    }



    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    User(Parcel in) {
       readFromParcel(in);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(address);
    }


    @Override
    public String toString() {
        return username+":"+address;
    }

    public void readFromParcel(Parcel in){
        username=in.readString();
        address=in.readString();
    }
}
