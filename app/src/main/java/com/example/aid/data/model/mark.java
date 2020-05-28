package com.example.aid.data.model;

public class mark {
    private int Mark_ID;
    private String Mark_UserID_fk;
    private int Mark_TaskID_fk;
    public mark(int id,String userid,int taskid){
        this.Mark_ID = id;
        this.Mark_TaskID_fk =taskid;
        this.Mark_UserID_fk = userid;
    }
    public void setID(int id){
        this.Mark_ID = id;
    }
    public void setUserID(String id){
        this.Mark_UserID_fk = id;
    }
    public void setTaskID(int id){
        this.Mark_TaskID_fk = id;
    }
    public int getID(){
        return this.Mark_ID;
    }
    public String getUserID(){
        return this.Mark_UserID_fk;
    }
    public int getTaskID(){
        return this.Mark_TaskID_fk;
    }
}
