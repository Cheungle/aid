package com.example.aid.data.model;

public class theme {
    private int Theme_ID;
    private String Theme_Content;
    private String Theme_Title;
    private String Theme_Time;
    private String Theme_ManagerID_fk;

    public theme(int id, String title, String content, String time, String manager_id){
        this.Theme_ID = id;
        this.Theme_Title = title;
        this.Theme_Content = content;
        this.Theme_Time = time;
        this.Theme_ManagerID_fk = manager_id;
    }

    public void setID(int id){
        this.Theme_ID = id;
    }
    public void setContentTitle(String title){
        this.Theme_Title = title;
    }
    public void setContent(String content){
        this.Theme_Content = content;
    }
    public void setTime(String time){
        this.Theme_Time = time;
    }
    public void setManagerID(String manager_id){
        this.Theme_ManagerID_fk = manager_id;
    }

    public int getID(){ return this.Theme_ID ; }
    public String getTitle(){
        return this.Theme_Title;
    }
    public String getContent(){
        return this.Theme_Content;
    }
    public String getTime(){ return this.Theme_Time; }
    public String getManagerID(){
        return this.Theme_ManagerID_fk;
    }
}
