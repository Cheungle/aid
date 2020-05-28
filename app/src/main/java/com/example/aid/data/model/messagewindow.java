package com.example.aid.data.model;

public class messagewindow {
    private int MW_ID;
    private int MW_TaskID_fk;
    private String MW_Time;
    private String MW_UserID1_fk;
    private String MW_UserID2_fk;


    public messagewindow(int id, int task_id, String time, String user_id1, String user_id2){
        this.MW_ID = id;
        this.MW_TaskID_fk = task_id;
        this.MW_Time = time;
        this.MW_UserID1_fk = user_id1;
        this.MW_UserID2_fk = user_id2;
    }

    public void setID(int id){
        this.MW_ID = id;
    }
    public void setTaskID(int task_id){
        this.MW_TaskID_fk = task_id;
    }
    public void setTime(String time){
        this.MW_Time = time;
    }
    public void setUserID1(String user_id1){
        this.MW_UserID1_fk = user_id1;
    }
    public void setUserID2(String user_id2){
        this.MW_UserID2_fk = user_id2;
    }

    public int getID(){ return this.MW_ID; }
    public int getTaskID(){ return this.MW_TaskID_fk; }
    public String getTime(){
        return this.MW_Time;
    }
    public String getUserID1(){
        return this.MW_UserID1_fk;
    }
    public String getUserID2(){
        return this.MW_UserID2_fk;
    }

}
