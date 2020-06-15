package com.example.aid.data.model;

import java.sql.Blob;

public class user {
    private String User_ID;
    private String User_Pwd;
    private int User_Sex;
    private String User_Name;
    private byte[] User_Head;
    private String User_Address;
    private int User_Age;
    private String User_Identity;
    private String User_RealName;
    public user(String id,String pwd,int sex,String name,byte[] head,String address,int age,String iden,String rname){
        this.User_Address = address;
        this.User_Age = age;
        this.User_Head =head;
        this.User_ID = id;
        this.User_Name = name;
        this.User_Pwd = pwd;
        this.User_Sex = sex;
        this.User_Identity = iden;
        this.User_RealName = rname;
    }
    public user(String id,int sex,String name,byte[] head,int age){
        this.User_Age = age;
        this.User_Head =head;
        this.User_ID = id;
        this.User_Name = name;
        this.User_Sex = sex;
    }
    public user(String id,String name,byte[] head){
        this.User_Head =head;
        this.User_ID = id;
        this.User_Name = name;
    }
    public void setID(String id){
        this.User_ID = id;
    }
    public void setPassword(String pwd){
        this.User_Pwd = pwd;
    }
    public void setSex(int sex){
        this.User_Sex = sex;
    }
    public void setName(String name){
        this.User_Name = name;
    }
    public void setHead(byte[] head){
        this.User_Head = head;
    }
    public void setAddress(String address){
        this.User_Address = address;
    }
    public void setAge(int age){
        this.User_Age = age;
    }
    public void setIdentity(String Identity){
        this.User_Identity = Identity;
    }
    public void setRealName(String name){
        this.User_RealName = name;
    }
    public String getID(){
        return this.User_ID;
    }
    public String getPassword(){
        return this.User_Pwd;
    }
    public int getSex(){
        return this.User_Sex;
    }
    public String getName(){
        return this.User_Name;
    }
    public byte[] getHead(){
        return this.User_Head;
    }
    public String getAddress(){
        return this.User_Address;
    }
    public int getAge(){
        return this.User_Age;
    }
    public String getIdentity(){
        return this.User_Identity;
    }
    public String getRealName(){
        return  this.User_RealName;
    }
}
