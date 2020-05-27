package com.example.aid.data.model;

public class manager {
    private String Manager_ID;
    private String Manager_Password;
    public manager(String id,String password){
         this.Manager_ID = id;
         this.Manager_Password = password;
    }
    public void setID(String id){
        this.Manager_ID = id;
    }
    public void setPassword(String password){
        this.Manager_Password = password;
    }
    public String getID(){
        return this.Manager_ID;
    }
    public String getIDPassword(){
        return this.Manager_Password;
    }
}
