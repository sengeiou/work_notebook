package com.example.my_application_test;


import android.util.Log;



public class Remote {
    private final String TAG = "Remote";

    public Remote() {
    }

    public void executeMessage(String msg, CallBack callback){
        //模拟耗时操作
        for(int i = 0;i < 999999; i++){
        }
        Log.i(TAG,"Task finished!!!");
        callback.execute("I'am calling you~~~----Remote");
    }
}
