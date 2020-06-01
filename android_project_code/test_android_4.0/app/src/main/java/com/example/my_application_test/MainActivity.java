package com.example.my_application_test;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;


public class MainActivity extends Activity implements CallBack,Runnable {
    private static final String TAG = "MainActivity";
    private Fragment mCurActiveFm = null;
    private Fragment001 mfragment1;
    private Fragment002 mfragment2;
    private SQLiteDB mSQLiteDB;
    private Remote remote;

    private static int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SQLiteDB单例对象获取
        mSQLiteDB = SQLiteDB.getInstance(this);
        initUi();
        showFragment002();
        remote = new Remote();
        this.sendMessage();
        Log.i(TAG,"oncreate");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ImageView animationImg1 = (ImageView) findViewById(R.id.test_animation);
//        animationImg1.setBackgroundResource(R.drawable.animation_test001);
//        AnimationDrawable animationDrawable = (AnimationDrawable)animationImg1.getBackground();
//        animationDrawable.start();
//        getDensity();
    }

    public void getDensity(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.e(TAG," Density is  "+displayMetrics.density+
                "densityDpi is  "+displayMetrics.densityDpi +"height:"+displayMetrics.heightPixels
                        +"width:  "+displayMetrics.widthPixels );

    }


    //数据库写入数据
    public void saveItem(SongTestObject songTestObject){
        mSQLiteDB.addItem(songTestObject);
    }

    //数据库查询数据
    public List<SongTestObject> queryAllItemData(){
        return mSQLiteDB.queryAllItemData();
    }

    public void deleteData(int userId){
        boolean result = mSQLiteDB.deleteData(userId);
        if (result){
            mfragment1.updataUi();
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("删除成功！！")
                    .setPositiveButton("确定",null)
                    .show();
        } else
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("删除失败！！")
                    .setPositiveButton("确定",null)
                    .show();
        }

    public void updateData(int userId,String password){
        boolean result = mSQLiteDB.updateData(userId,password);
        if (result){
            mfragment1.updataUi();
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("修改成功！！")
                    .setPositiveButton("确定",null)
                    .show();
        } else
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("修改失败！！")
                    .setPositiveButton("确定",null)
                    .show();
    }


    private void initUi(){
        mfragment1 = new Fragment001();
        mfragment2 = new Fragment002();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"ondestroy");
    }

    private void LoadFragment(android.app.Fragment fm) {
        if (fm != null && fm != mCurActiveFm) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout_content, fm);
            mCurActiveFm = fm;
            ft.commitAllowingStateLoss();
        }
    }

    public void showFragment001(){
        LoadFragment(mfragment1);
    }

    public void showFragment002(){
        LoadFragment(mfragment2);
    }

    @Override
    public void execute(String string) {
        /**打印返回的消息**/
        Log.i(TAG,string);
        /**打印发送消息的线程名称**/
        Log.i(TAG,Thread.currentThread().getName());
        /**中断发送消息的线程**/
        Thread.interrupted();
    }

    @Override
    public void run() {
        Log.i(TAG,"this is Run()!");
        Log.i(TAG,"i'am call you~~~~----mainactivity");
        remote.executeMessage("",this);
    }

    /**
     * 发送消息
     */
    public void sendMessage()
    {
        /**当前线程的名称**/
        Log.i(TAG,"send Massage!");
        Log.i(TAG,Thread.currentThread().getName());

        /**创建一个新的线程发送消息**/
        Thread thread = new Thread(this);
        thread.start();
        /**当前线程继续执行**/
        Log.i(TAG,"Message has been sent by Local~!");
    }
}
