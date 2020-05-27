package com.example.aid.data.model;

public class reviewedtask {
    private String RVT_ID_fk;
    private String RVT_ManagerID_fk;
    private String RVT_ReviewTime;
    private int RVT_State;
    public reviewedtask(String id ,String Mid,String time,int state){
        this.RVT_ID_fk=id;
        this.RVT_ManagerID_fk=Mid;
        this.RVT_ReviewTime=time;
        this.RVT_State=state;
    }
    public void setID(String id){
        this.RVT_ID_fk = id;
    }
    public void setManagerID(String id){
        this.RVT_ManagerID_fk = id;
    }
    public void setTime(String time){
        this.RVT_ReviewTime = time;
    }
    public void setState(int state){
        this.RVT_State = state;
    }
    public String getID(){
        return this.RVT_ID_fk;
    }
    public String getManagerID(){
        return this.RVT_ManagerID_fk;
    }
    public int getState(){
        return this.RVT_State;
    }
    public String getTime(){
        return this.RVT_ReviewTime;
    }
}
