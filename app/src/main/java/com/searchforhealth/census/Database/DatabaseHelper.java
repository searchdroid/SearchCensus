package com.searchforhealth.census.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.searchforhealth.census.Model.Model_UserNames;
import com.searchforhealth.census.Preference.ConstantDatabase;

public class DatabaseHelper {

    private SQLiteDatabase db;
    SQLiteHelper helper;
    Context context;
    String name;

    public DatabaseHelper(Context context){

        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long insertUserNamesData(Model_UserNames data){

        ContentValues cv = new ContentValues();

        cv.put(ConstantDatabase.COLUMN_FIRSTNAME, data.getFirstName());
        cv.put(ConstantDatabase.COLUMN_LASTNAME, data.getLastname());

        long  rec = db.insert(ConstantDatabase.TABLE_USERNAMES, null, cv);
        return rec;
    }

    public Cursor selectUsersDataByFirstName(String fName){
        String[] selectionarg = {fName};

        Cursor cursor = db.query(ConstantDatabase.TABLE_USERNAMES, null,
                ConstantDatabase.COLUMN_FIRSTNAME + "=? " , selectionarg, null,
                null, null);
        return cursor;
    }

    public Cursor selectAllUsersDataData(){

        Cursor cursor = db.query(ConstantDatabase.TABLE_USERNAMES, null,
                null , null, null,
                null, null);
        return cursor;
    }

//
//    //For Community
//
//    public long insertCommunityChatData(Model_chat data){
//
//        ContentValues cv = new ContentValues();
//
//        cv.put(ConstantDatabase.COMMUNITY_ID, data.getAppointmentId());
//        cv.put(ConstantDatabase.SENDER_TYPE, data.getSender_type());
//        cv.put(ConstantDatabase.SENDER_ID, data.getSenderId());
//        cv.put(ConstantDatabase.SENDER_NAME, data.getSender_name());
//        cv.put(ConstantDatabase.MSG, data.getMessage());
//        cv.put(ConstantDatabase.CHAT_DATE, data.getChat_time());
//        // cv.put(ConstantDatabase.CHAT_CREATED_DATE, data.getChat_time());
//        cv.put(ConstantDatabase.MSG_CHAT_COUNTER, data.getMsg_Counter());
//
//        long rec = db.insert(ConstantDatabase.CHAT_COMMUNITY_TABLE, null, cv);
//        return rec;
//    }
//
//    public Cursor selectCommunityChatData(String community_id){
//        String[] selectionarg = {community_id};
//
//
//        Cursor cursor = db.query(ConstantDatabase.CHAT_COMMUNITY_TABLE, null,
//                ConstantDatabase.COMMUNITY_ID + "=? ", selectionarg, null,
//                null, null);
//        return cursor;
//    }
//
//    public Cursor selectCommunityChatDataCounter(String community_id){
//        String[] selectionarg = {community_id};
//        String[] selectColoumn = {ConstantDatabase.MSG_CHAT_COUNTER};
//
//
//        Cursor cursor = db.query(ConstantDatabase.CHAT_COMMUNITY_TABLE, selectColoumn,
//                ConstantDatabase.COMMUNITY_ID + "=? ", selectionarg, null,
//                null, null);
//        return cursor;
//    }
//
//    public void updateMsgCounter(String community_id){
//        String[] whereArg = {community_id};
//
//        ContentValues cv = new ContentValues();
//        cv.put(ConstantDatabase.MSG_CHAT_COUNTER, ""+0);
//
//        db.update(ConstantDatabase.CHAT_COMMUNITY_TABLE, cv, ConstantDatabase.COMMUNITY_ID + "=?", whereArg);
////        return id;
//    }

}
