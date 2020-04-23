package com.acamara.blowit;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    private String uid;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password; // We might remove this for sec reasons

    public User(){

    }
    public User(String uid, String username, String first_name, String last_name, String email, String password){

        this.email=email;
        this.username=username;
        this.first_name=first_name;
        this.last_name=last_name;
        this.uid=uid;
        this.password = password;
    }

    protected User(Parcel in) {
        uid = in.readString();
        username = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        email = in.readString();
        password = in.readString();
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

    //setters
    public void setUID(String uid){
        this.uid=uid;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setFirst_name(String first_name){
        this.first_name=first_name;
    }
    public void setLast_name(String last_name){
        this.last_name=last_name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    //getters
    public String getUID(){return uid;}
    public String getUsername() {return username;}
    public String getFirst_name(){return first_name;}
    public String getLast_name(){return last_name;}
    public String getEmail(){return email;}

    public String toString(){
        return "Name: " + this.first_name + " " + this.last_name + "/n" +
                "Email: " + this.email + "\n" +
                "Username: " + this.username + "\n" +
                "Password: " + this.password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(username);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(email);
        dest.writeString(password);
    }
}
