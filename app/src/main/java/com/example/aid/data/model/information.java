package com.example.aid.data.model;

public class information {
    private int Info_ID;
    private String Info_Title;
    private String Info_Time;
    private String Info_Content;
    private String Info_Source;
    private String Info_Image1;
    private String Info_Image2;

    public information(int id,String title,String time,String source,String content,String image1,String image2){
        this.Info_ID =id;
        this.Info_Title =title;
        this.Info_Time = time;
        this.Info_Source = source;
        this.Info_Content = content;
        this.Info_Image1 = image1;
        this.Info_Image2 = image2;
    }
    public void setID(int id){
        this.Info_ID = id;
    }
    public void setTitle(String title){
        this.Info_Title = title;
    }
    public void setTime(String time){
        this.Info_Time = time;
    }
    public void setSource(String source){
        this.Info_Source = source;
    }
    public void setContent(String content){
        this.Info_Content = content;
    }
    public void setImage1(String image1){
        this.Info_Image1 = image1;
    }
    public void setImage2(String image2){
        this.Info_Image2 = image2;
    }

    public int getID(){
        return Info_ID;
    }
    public String getTitle(){
        return Info_Title;
    }
    public String getTime(){
        return Info_Time;
    }
    public String getSource(){
        return Info_Source;
    }
    public String getContent(){
        return Info_Content;
    }
    public String getImage1(){
        return Info_Image1;
    }
    public String getImage2(){
        return Info_Image2;
    }
}
