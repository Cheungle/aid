package com.example.aid.data.BLL;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;


import com.bumptech.glide.load.HttpException;
import com.example.aid.data.DAL.InfoDAL;
import com.example.aid.data.model.information;

import org.json.JSONArray;

import static java.lang.System.out;


public class InfoBLL {

    private InfoDAL dal = new InfoDAL();
    List information = dal.queryAll();

    public InfoBLL() throws SQLException {

    }
}
