package com.example.aid.data.model;

public class receivedtask {
    private int RVT_ID_fk;
    private String RVT_ManagerID_fk;
    private String RVT_ReceivedTime;
    private String RVT_State;

    public receivedtask(int id_fk, String manager_id_fk, String reveived_time, String state){
        this.RVT_ID_fk = id_fk;
        this.RVT_ManagerID_fk = manager_id_fk;
        this.RVT_ReceivedTime = reveived_time;
        this.RVT_State = state;
    }
    public void setRVT_ID_fk(int id_fk) { this.RVT_ID_fk = id_fk; }
    public void setRVT_ManagerID_fk(String manager_id_fk) { this.RVT_ManagerID_fk = manager_id_fk; }
    public void setRVT_ReceivedTime(String received_time) { this.RVT_ReceivedTime = received_time; }
    public void setRVT_State(String state) { this.RVT_State = state; }

    public int getRVT_ID_fk() { return RVT_ID_fk; }
    public String getRVT_ManagerID_fk() { return RVT_ManagerID_fk; }
    public String getRVT_ReceivedTime() { return RVT_ReceivedTime; }
    public String getRVT_State() { return RVT_State; }
}
