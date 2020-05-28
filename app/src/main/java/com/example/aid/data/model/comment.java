package com.example.aid.data.model;

public class comment {
    private int Comment_ID;
    private String Comment_Content;
    private String Comment_PublishTime;
    private String Comment_Source_fk;
    private int Comment_ThemeID_fk;
    private int Comment_State;
    private int Comment_PreCmmtID_fk;

    public comment(int id, String content, String time, String source,
                   int theme_id, int state, int precmmt_id ){
        this.Comment_ID = id;
        this.Comment_Content = content;
        this.Comment_PublishTime = time;
        this.Comment_Source_fk = source;
        this.Comment_ThemeID_fk = theme_id;
        this.Comment_State = state;
        this.Comment_PreCmmtID_fk = precmmt_id;
    }

    public void setID(int id){
        this.Comment_ID = id;
    }
    public void setContent(String content){
        this.Comment_Content = content;
    }
    public void setPublishTime(String time){
        this.Comment_PublishTime = time;
    }
    public void setSource(String source){
        this.Comment_Source_fk = source;
    }
    public void setThemeID(int theme_id){ this.Comment_ThemeID_fk = theme_id; }
    public void setState(int state){
        this.Comment_State = state;
    }
    public void setPreCmmtID(int precmmt_id){
        this.Comment_PreCmmtID_fk = precmmt_id;
    }

    public int getID(){
        return this.Comment_ID ;
    }
    public String getContent(){
        return  this.Comment_Content;
    }
    public String getPublishTime(){
        return this.Comment_PublishTime;
    }
    public String getSource(){
        return this.Comment_Source_fk ;
    }
    public int getThemeID() { return this.Comment_ThemeID_fk ; }
    public int getState(){
        return this.Comment_State ;
    }
    public int getPreCmmtID() { return this.Comment_PreCmmtID_fk; }
}
