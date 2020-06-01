package com.example.my_application_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDB {
    private static final String TAG = "SQLiteDB";
    //数据库名称
    public static final String DBName = "SQLite_Test";
    //数据库版本
    public static final int DBVersion = 1;

    //数据库
    private static SQLiteDB sqliteDB;

    private SQLiteDatabase db;


    public SQLiteDB(Context context) {
        //初始化数据库，待接口完善
        OpenHelper openHelper = new OpenHelper(context,DBName,null,DBVersion);

        //获取db
        db = openHelper.getWritableDatabase();
    }

    //获取SQLiteDB单例对象
    public synchronized static SQLiteDB getInstance(Context context){
        if (sqliteDB == null){
            sqliteDB = new SQLiteDB(context);
        }
        return sqliteDB;
    }

    public void addItem(SongTestObject songTestObject){
        if (null != songTestObject && null != db){
            Log.i(TAG,"inserting!!!");
            ContentValues cv = new ContentValues();
            cv.put("username",songTestObject.getNumber());
            cv.put("password",songTestObject.getSongInformation());
            db.insert("user",null,cv);
        }else{
            Log.i(TAG,"save Item failed , value is null!");
        }
    }

    public List<SongTestObject> queryAllItemData(){
        List<SongTestObject> data = new ArrayList<SongTestObject>();
        if (null != db){
            Cursor cursor = db.query("user",null,null,null,null,null,null);
            if (cursor.moveToFirst())
            {
                do {
                    SongTestObject songTestObject = new SongTestObject(cursor.getInt(cursor.getColumnIndex("userid")),cursor.getString(cursor.getColumnIndex("username")),cursor.getString(cursor.getColumnIndex("password")));
                    data.add(songTestObject);
                }while (cursor.moveToNext());
            }
        }
        return data;
    }

    public boolean deleteData(int userId){
        if (null != db){
            String[] arg = new String[]{String.valueOf(userId)};
            return (db.delete("user","userid=?",arg) == 0 ? false : true);
        }
        return false;
    }

    public boolean updateData(int userId,String password){
        if (null != db){
            ContentValues cv=new ContentValues();
            cv.put("password",password);
            String[] arg = new String[]{String.valueOf(userId)};
            return (db.update("user",cv,"userid = ?",arg) == 0?false :true );
        }
        return false;
    }
}
