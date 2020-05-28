package com.example.aid.data.model;

public class data {

        private int Data_ID;
        private String Data_Place;
        private String Data_Time;
        private String Data_Source;
        private int Data_Data1;
        private int Data_Data2;
        private int Data_Data3;
        private int Data_Data4;
        public data(int id,String place,String time,String source,int data1,int data2,int data3,int data4){
            this.Data_ID =id;
            this.Data_Place =place;
            this.Data_Time = time;
            this.Data_Source = source;
            this.Data_Data1 = data1;
            this.Data_Data2 = data2;
            this.Data_Data3 = data3;
            this.Data_Data4 = data4;
        }
        public void setID(int id){
            this.Data_ID = id;
        }
        public void setPlace(String place){
            this.Data_Place = place;
        }
        public void setTime(String time){
        this.Data_Time = time;
    }
        public void setSource(String source){
        this.Data_Source = source;
    }
        public void setData1(int data1){
        this.Data_Data1 = data1;
    }
        public void setData2(int data2){
        this.Data_Data2 = data2;
    }
        public void setData3(int data3){
        this.Data_Data3 = data3;
    }
        public void setData4(int data4){
        this.Data_Data4 = data4;
    }

        public int getID(){
            return this.Data_ID;
        }
        public String getPlace(){
            return this.Data_Place;
        }
        public String getTime(){
            return this.Data_Time;
        }
        public String getSource(){
            return this.Data_Source;
        }
        public int getData1(){
            return this.Data_Data1;
        }
        public int getData2(){
            return this.Data_Data2;
        }
        public int getData3(){
            return this.Data_Data3;
        }
        public int getData4(){
            return this.Data_Data4;
        }

}




