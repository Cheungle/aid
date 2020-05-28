package com.example.aid.data.model;

public class completedtask {
    private int CT_ID_fk;
    private String CT_CompletedTime;

    public completedtask(int id_fk, String completed_time){
        this.CT_ID_fk = id_fk;
        this.CT_CompletedTime = completed_time;
    }
    public void setCT_ID_fk(int id_fk) { this.CT_ID_fk = id_fk; }
    public void setCT_CompletedTime(String completed_time) { this.CT_CompletedTime = completed_time; }

    public int getCT_ID_fk() { return CT_ID_fk; }
    public String getCT_CompletedTime() { return CT_CompletedTime; }
}
