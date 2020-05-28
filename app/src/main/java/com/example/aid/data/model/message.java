package com.example.aid.data.model;

public class message {
    private int Message_ID;
    private String Message_UserID_fk;
    private String Message_Time;
    private int Message_WindowID_fk;
    private int Message_State1;
    private int Message_State2;
    private String Message_Content;

    public message(int id, String user_id, String time, int window_id,
                   int state1, int state2, String content){
        this.Message_ID = id;
        this.Message_UserID_fk = user_id;
        this.Message_Time = time;
        this.Message_WindowID_fk = window_id;
        this.Message_State1 = state1;
        this.Message_State2 = state2;
        this.Message_Content = content;
    }

    public void setID(int id){
        this.Message_ID = id;
    }
    public void setUserID(String user_id){
        this.Message_UserID_fk = user_id;
    }
    public void setTime(String time){
        this.Message_Time = time;
    }
    public void setWindowID(int window_id){
        this.Message_WindowID_fk = window_id;
    }
    public void setState1(int state1){
        this.Message_State1 = state1;
    }
    public void setState2(int state2){
        this.Message_State2 = state2;
    }
    public void setContent(String content){
        this.Message_Content = content;
    }

    public int getID(){
        return this.Message_ID;
    }
    public String getUserID(){
        return  this.Message_UserID_fk;
    }
    public String getTime() { return  this.Message_Time;}
    public int getWindowID(){
        return  this.Message_WindowID_fk;
    }
    public int getState1(){
        return  this.Message_State1;
    }
    public int getState2(){
        return  this.Message_State2;
    }
    public String getContent(){
        return  this.Message_Content;
    }
}
