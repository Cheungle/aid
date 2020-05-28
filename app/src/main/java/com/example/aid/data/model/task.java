package com.example.aid.data.model;

public class task {
    private int Task_ID;
    private String Task_CreatorID_fk;
    private String Task_Content;
    private String Task_Time;
    private int Task_Type;
    private String Task_Place;

    public task(int id, String creator_id_fk, String content, String time, int type, String place){
        this.Task_ID = id;
        this.Task_CreatorID_fk = creator_id_fk;
        this.Task_Content = content;
        this.Task_Time = time;
        this.Task_Type = type;
        this.Task_Place = place;
    }
    public void setTask_ID(int id) { this.Task_ID = id; }
    public void  setTask_CreatorID_fk(String creator_id_fk) { this.Task_CreatorID_fk = creator_id_fk; }
    public void setTask_Content(String content) { this.Task_Content = content; }
    public void setTask_Time(String time) { this.Task_Time = time; }
    public void setTask_Type(int type) { this.Task_Type = type; }
    public void setTask_Place(String place) { this.Task_Place = place; }

    public int getTask_ID() { return this.Task_ID; }
    public String getTask_CreatorID_fk() { return this.Task_CreatorID_fk; }
    public String getTask_Content() { return this.Task_Content; }
    public String getTask_Time() { return this.Task_Time; }
    public int getTask_Type() { return  this.Task_Type; }
    public String getTask_Place() { return this.Task_Place; }
}
