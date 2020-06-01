package com.example.filereadanwrite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private EditText mDataShow;
    private RadioGroup mRadioGroup;

    private static final String TAG = "sgx111";
    private static final String FILEURL = "/mnt/sdcard/NewTextFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        requestPermission();
    }

    private void initUI(){
        mRadioGroup = findViewById(R.id.rg_radiogroup);
        mDataShow = findViewById(R.id.et_filedata);
        initUIEvent();
    }

    private void initUIEvent(){
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId){
            case R.id.rb_readfile:
                readData();
                break;
            case R.id.rb_writefile:
                writeData();
                break;
            default:
                break;
        }
    }

    private void setEditViewData(String string){
        if(mDataShow != null){
            mDataShow.setText(string);
        }
    }

    private String getEditViewData(){
        String result = "";
        if(mDataShow != null){
            result = mDataShow.getText().toString();
        }
        return result;
    }

    /**
     * 从指定文件读，并且显示到Editview
     */
    private void readData(){
        Log.i(TAG,"readData!!!");
        FileInputStream in = null;
        InputStreamReader inr = null;

        StringBuffer stringBuffer = new StringBuffer();

        File file = new File(FILEURL);
        if (!file.exists()){
            new AlertDialog.Builder(this)
                    .setTitle("warning")
                    .setMessage(FILEURL+"文件不存在，读取失败！")
                    .setPositiveButton("确定",null)
                    .show();
            return;
        }

        try {
            in = new FileInputStream(file);
            inr = new InputStreamReader(in);
            int ch = 0;
            while((ch = inr.read()) != -1){
                stringBuffer.append((char)ch);
            }
            inr.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setEditViewData(stringBuffer.toString());
    }

    /**
     * 把exitview的数据写入到文件
     */
    private void writeData(){
        Log.i(TAG,"writeData!!!");
        File file = new File(FILEURL);
        FileOutputStream out = null;

        if(!file.exists()){
            try {
                Log.i(TAG,"file no exist,create_file!!");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!file.exists()){
            new AlertDialog.Builder(this)
                    .setTitle("warning")
                    .setMessage(FILEURL+"文件不存在，且尝试创建该文件失败！")
                    .setPositiveButton("确定",null)
                    .show();
            return;
        }

        try {
            out = new FileOutputStream(file);
            out.write(getEditViewData().getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED) {//判断是否已经赋予权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限.它在用户选择"不再询问"的情况下返回false

            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }}
    }
}
