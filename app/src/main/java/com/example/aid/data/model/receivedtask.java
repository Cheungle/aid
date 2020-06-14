package com.example.aid.data.model;

public class receivedtask {
    private int RCT_ID_fk;
    private String RCT_ReceiverID_fk;
    private String RCT_ReceivedTime;

    public receivedtask(int id_fk, String manager_id_fk, String reveived_time){
        this.RCT_ID_fk = id_fk;
        this.RCT_ReceiverID_fk = manager_id_fk;
        this.RCT_ReceivedTime = reveived_time;
    }
    public void setRCT_ID_fk(int id_fk) { this.RCT_ID_fk = id_fk; }
    public void setRCT_ReceiverID_fk(String manager_id_fk) { this.RCT_ReceiverID_fk = manager_id_fk; }
    public void setRCT_ReceivedTime(String received_time) { this.RCT_ReceivedTime = received_time; }
    public int getRCT_ID_fk() { return RCT_ID_fk; }
    public String getRCT_ReceiverID_fk() { return RCT_ReceiverID_fk; }
    public String getRCT_ReceivedTime() { return RCT_ReceivedTime; }
}
