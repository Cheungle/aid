package com.example.aid.data.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int version=1;
    private static final String name="aid.db";
    public DataBaseHelper(Context context){
        super(context,name,null,version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE `identity` (" +
                "  `Ididentity_ID` varchar(18) PRIMARY KEY NOT NULL," +
                "  `Identity_Name` varchar(15) NOT NULL" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user` (" +
                "  `User_ID` char(12) PRIMARY KEY UNIQUE NOT NULL," +
                "  `User_Pwd` varchar(15) NOT NULL," +
                "  `User_Sex` tinyint(1) NOT NULL," +
                "  `User_Name` varchar(20) NOT NULL," +
                "  `User_Head` varchar(20) DEFAULT NULL," +
                "  `User_Address` varchar(50) DEFAULT NULL," +
                "  `User_Age` int(11) DEFAULT NULL," +
                "  `User_Identity_fk` varchar(18) DEFAULT NULL," +
                "  `User_RealName_fk` varchar(15) DEFAULT NULL," +
                "  FOREIGN KEY (`User_Identity_fk`) REFERENCES `identity` (`Identity_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`User_RealName_fk`) REFERENCES `identity` (`Identity_Name`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ") ");
        db.execSQL("CREATE TABLE IF NOT EXISTS `manager` (" +
                "  `Manager_ID` char(13) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Manager_Password` varchar(20) NOT NULL" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `data` (" +
                "  `Data_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Data_Place` varchar(10) NOT NULL," +
                "  `Data_Data` int(11) NOT NULL," +
                "  `Data_Time` date NOT NULL," +
                "  `Data_Source` varchar(20) NOT NULL" +
                ") ");
        db.execSQL("CREATE TABLE IF NOT EXISTS `information` (" +
                "  `Info_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Info_Title` varchar(45) UNIQUE NOT NULL," +
                "  `Info_Content` varchar(200) UNIQUE NOT NULL," +
                "  `Info_Time` date NOT NULL," +
                "  `Info_Source` varchar(20) NOT NULL" +
                ") ");
        db.execSQL("CREATE TABLE IF NOT EXISTS `task` (" +
                "  `Task_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Task_CreatorID_fk` char(12) NOT NULL," +
                "  `Task_Content` varchar(100) NOT NULL," +
                "  `Task_Time` date DEFAULT NULL," +
                "  `Task_Type` int(11) NOT NULL," +
                "  `Task_Place` varchar(20) DEFAULT NULL," +
                "  FOREIGN KEY (`Task_CreatorID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `mark` (" +
                "  `Mark_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Mark_TaskID_fk` int(11) NOT NULL," +
                "  `Mark_UserID_fk` char(12) NOT NULL," +
                "  FOREIGN KEY (`Mark_TaskID_fk`) REFERENCES `task` (`Task_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`Mark_UserID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `messagewindow` (" +
                "  `MW_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `MW_UserID1_fk` char(12) NOT NULL," +
                "  `MW_UserID2_fk` char(12) NOT NULL," +
                "  `MW_Time` date NOT NULL," +
                "  `MW_TaskID_fk` int(11) UNIQUE NOT NULL," +
                "  FOREIGN KEY (`MW_TaskID_fk`) REFERENCES `task` (`Task_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`MW_UserID1_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`MW_UserID2_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ") ");
        db.execSQL("CREATE TABLE IF NOT EXISTS `message` (" +
                "  `Message_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Message_UserID_fk` char(12) NOT NULL," +
                "  `Message_Time` date NOT NULL," +
                "  `Message_WindowID_fk` int(11) NOT NULL," +
                "  `Message_State1` int(11) NOT NULL," +
                "  `Message_State2` int(11) NOT NULL," +
                "  `Message_Content` varchar(50) NOT NULL," +
                "  FOREIGN KEY (`Message_UserID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`Message_WindowID_fk`) REFERENCES `messagewindow` (`MW_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reviewedtask` (" +
                "  `RVT_ID_fk` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `RVT_ManagerID_fk` char(13) NOT NULL," +
                "  `RVT_ReviewTime` date NOT NULL," +
                "  `RVT_State` int(11) NOT NULL," +
                "  FOREIGN KEY (`RVT_ID_fk`) REFERENCES `task` (`Task_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`RVT_ManagerID_fk`) REFERENCES `manager` (`Manager_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ") ");
        db.execSQL("CREATE TABLE IF NOT EXISTS `receivedtask` (" +
                "  `RCT_ID_fk` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `RCT_ReceiverID_fk` char(12) NOT NULL," +
                "  `RCT_ReceivedTime` date NOT NULL," +
                "  FOREIGN KEY (`RCT_ReceiverID_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`RCT_ID_fk`) REFERENCES `reviewedtask` (`RVT_ID_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `completedtask` (" +
                "  `CT_ID_fk` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `CT_CompletedTime` date NOT NULL," +
                "  FOREIGN KEY (`CT_ID_fk`) REFERENCES `receivedtask` (`RCT_ID_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ") ");
        db.execSQL("CREATE TABLE IF NOT EXISTS `theme` (" +
                "  `Theme_ID` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `Theme_Content` varchar(100) NOT NULL," +
                "  `Theme_Time` date NOT NULL," +
                "  `Theme_ManagerID_fk` char(13) NOT NULL," +
                "  FOREIGN KEY (`Theme_ManagerID_fk`) REFERENCES `manager` (`Manager_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `comment` (" +
                "  `Comment_ID` int(11) PRIMARY KEY NOT NULL UNIQUE," +
                "  `Comment_Content` varchar(50) NOT NULL," +
                "  `Comment_PublishTime` date NOT NULL," +
                "  `Comment_Source_fk` char(12) NOT NULL," +
                "  `Comment_ThemeID_fk` int(11) NOT NULL," +
                "  `Comment_State` int(11) NOT NULL," +
                "  `Comment_PreCmmtID_fk` int(11) DEFAULT NULL," +
                "  FOREIGN KEY (`Comment_PreCmmtID_fk`) REFERENCES `reviewedcomment` (`RC_ID_fk`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`Comment_Source_fk`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`Comment_ThemeID_fk`) REFERENCES `theme` (`Theme_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reviewedcomment` (" +
                "  `RC_ID_fk` int(11) PRIMARY KEY UNIQUE NOT NULL," +
                "  `RC_ManagerID_fk` char(13) NOT NULL," +
                "  `RC_ReviewTime` date NOT NULL," +
                "  `RC_State` int(11) NOT NULL," +
                "  FOREIGN KEY (`RC_ID_fk`) REFERENCES `comment` (`Comment_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "  FOREIGN KEY (`RC_ManagerID_fk`) REFERENCES `manager` (`Manager_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                ") ");
        db.execSQL("INSERT INTO `identity` VALUES ('52263319990621001X','李四');");
        db.execSQL("INSERT INTO `user` VALUES ('15186861111','123456',1,'LU','image','上海',20,NULL,NULL),('15186862222','123456',0,'Li','image','北京',30,NULL,NULL);");
        db.execSQL("INSERT INTO `manager` VALUES ('15186861111M','123456');");
        db.execSQL("INSERT INTO `theme` VALUES (1,'新冠病毒','2020-05-01','15186861111M'),(2,'今日确诊','2020-05-03','15186861111M');");
        db.execSQL("INSERT INTO `data` VALUES (1,'上海',26,'2020-05-01','国家卫健委');");
        db.execSQL("INSERT INTO `information` VALUES (1,'31省区市新增7例境外输入病例','5月25日0—24时，31个省（自治区、直辖市）和新疆生产建设兵团报告新增确诊病例7例，均为境外输入病例（内蒙古5例，上海1例，福建1例）；无新增死亡病例；无新增疑似病例。','2020-05-26','央视新闻');");
        db.execSQL("INSERT INTO `task` VALUES (1,'15186861111','口罩20个，防护服10个','2020-05-27',1,'上海'),(2,'15186861111','防护服20个','2020-05-01',1,'北京'),(3,'15186862222','防护服20个','2020-04-28',2,'北京'),(4,'15186862222','口罩10个','2020-04-04',2,'北京'),(5,'15186862222','志愿者2位','2020-05-05',1,'北京');");
        db.execSQL("INSERT INTO `reviewedtask` VALUES (1,'15186861111M','2020-05-28',1),(2,'15186861111M','2020-05-01',1),(3,'15186861111M','2020-05-01',1),(4,'15186861111M','2020-05-01',2);");
        db.execSQL("INSERT INTO `receivedtask` VALUES (2,'15186862222','2020-05-20'),(3,'15186861111','2020-05-20');");
        db.execSQL("INSERT INTO `completedtask` VALUES (2,'2020-05-08');");
        db.execSQL("INSERT INTO `messagewindow` VALUES (1,'15186861111','15186862222','2020-05-01',2);");
        db.execSQL("INSERT INTO `comment` VALUES (1,'今天确诊人数下降了','2020-05-03','15186861111',2,1,NULL),(2,'真的！','2020-05-03','15186862222',2,1,1);");
        db.execSQL("INSERT INTO `reviewedcomment` VALUES (1,'15186861111M','2020-05-03',1);");
        db.execSQL("INSERT INTO `message` VALUES (1,'15186861111','2020-05-02',1,1,1,'请问防护服还有吗'),(2,'15186862222','2020-05-02',1,1,1,'您好，还有的');");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
