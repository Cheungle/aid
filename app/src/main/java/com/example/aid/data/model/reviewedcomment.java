package com.example.aid.data.model;

public class reviewedcomment {
    private String RC_ID_fk;
    private String RC_ManagerID_fk;
    private String RC_ReviewTime;
    private int RC_State;

    public reviewedcomment(String id,String password,String time,int state){
        this.RC_ID_fk = id;
        this.RC_ManagerID_fk = password;
        this.RC_ReviewTime = time;
        this.RC_State = state;
    }
    public void setID(String id){
        this.RC_ID_fk = id;
    }
    public void setManagerID(String id){
        this.RC_ManagerID_fk = id;
    }
    public void setTime(String time){
        this.RC_ReviewTime = time;
    }
    public void setState(int state){
        this.RC_State = state;
    }
    public String getID(){
        return this.RC_ID_fk;
    }
    public String getManagerID(){
        return this.RC_ManagerID_fk;
    }
    public String getTime(){
        return this.RC_ReviewTime;
    }
    public int getState(){
        return this.RC_State;
    }
}
