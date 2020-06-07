package com.example.aid.data.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.aid.data.model.information;
public class InfoDAL extends DBHelper{
    private Connection conn;
    private Statement stmt;
    public InfoDAL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public List<information>queryAll() throws SQLException {
        String sql="select * from  information";
        ResultSet rs = stmt.executeQuery(sql);
        List<information> information = new ArrayList<information>();
        while(rs.next()) {
            information m = new information(rs.getInt(1) ,rs.getString(2), rs.getString(3),
                    rs.getString(4) ,rs.getString(5));
            information.add(m);
        }
        return information;
    }



}





