package com.example.aid.data.model;

public class identity {
    private String Identity_ID;
    private String Identity_Name;
    public identity(String id,String name){
        this.Identity_ID = id;
        this.Identity_Name = name;
    }
    public void setID(String id){
        this.Identity_ID=id;
    }
    public String getID(){
        return this.Identity_ID;
    }
    public void setName(String name){
        this.Identity_Name = name;
    }
    public String getName(){
        return this.Identity_Name;
    }
}
