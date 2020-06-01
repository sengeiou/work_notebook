package com.example.my_application_test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter001 extends BaseAdapter implements View.OnClickListener {
    private final String TAG = "TestAdapter001";
    private LayoutInflater mInflater;
    private MainActivity mainActivity;
    private List<SongTestObject> mData = new ArrayList<SongTestObject>();

    public TestAdapter001(MainActivity mContext) {
        mInflater = LayoutInflater.from(mContext);
        this.mainActivity = mContext;
    }

    public void setmData(List <SongTestObject> mData){
        this.mData = mData;
        notifyDataSetChanged();
        Log.i(TAG,"setData");
    }

    @Override
    public int getCount() {
        if (null != mData){
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (null != mData){
            return mData.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        if (null != mData){
            return i;
        }
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.i(TAG,"getView");
        ViewHolder mViewHolder = null;

        //通过内部类和对应的item的子布局文件，根据data数据和position值去绘制item view。
        if (null == view){
//            view = mInflater.inflate(R.layout.fragment_adapter001,viewGroup,false);
            view = mInflater.inflate(R.layout.fragment_adapter001,viewGroup,false);
            mViewHolder = new ViewHolder();
            mViewHolder.mid = (TextView) view.findViewById(R.id.id_text);
            mViewHolder.mInformation = (TextView) view.findViewById(R.id.information_text);
            mViewHolder.mNumber = (TextView) view.findViewById(R.id.number_text);
            mViewHolder.mDelete = (Button) view.findViewById(R.id.deleteButton);
            mViewHolder.mEdit = (Button) view.findViewById(R.id.editButton);
            mViewHolder.mDelete.setOnClickListener(this);
            mViewHolder.mEdit.setOnClickListener(this);
            view.setTag(mViewHolder);
        }else{
            mViewHolder =(ViewHolder)view.getTag();
        }

        try{
            SongTestObject item = mData.get(i);
            if(null != item){
                mViewHolder.mid.setText(item.getId()+"");
                mViewHolder.mNumber.setText(item.getNumber());
                mViewHolder.mInformation.setText(item.getSongInformation());
                mViewHolder.mDelete.setTag(i);
                mViewHolder.mEdit.setTag(i);
                Log.i(TAG,"getview number is :"+item.getNumber());
            }else{
                Log.i(TAG,"this position item is null. position is:"+i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        int index = (Integer) view.getTag();
        Log.i(TAG,"onclick Button get Tag is :"+index);
        SongTestObject songTestObject =(SongTestObject) this.getItem(index);
        if (null != songTestObject){
            switch (view.getId()){
                case R.id.deleteButton:
                    mainActivity.deleteData(songTestObject.getId());
                    break;
                case R.id.editButton:
                    showEditView(songTestObject.getId());
                    break;
                default:
                    break;
            }
        }else{
            Log.i(TAG,"songTestObject is null!");
        }
    }

    private void showEditView(final int userId){
        LayoutInflater factory = LayoutInflater.from(mainActivity);
        final EditText edt01 = new EditText(mainActivity);
        final EditText edt02 = new EditText(mainActivity);
        edt01.setMaxLines(1);
        new AlertDialog.Builder(mainActivity)
                .setTitle("请输入新密码：")
                .setView(edt01)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainActivity.updateData(userId,edt01.getText().toString());
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private final class ViewHolder
    {
        TextView mid;
        TextView mNumber;
        TextView mInformation;
        Button mDelete;
        Button mEdit;
    }
}


