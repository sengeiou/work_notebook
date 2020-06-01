package com.example.my_application_test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter002 extends BaseAdapter {
    private final String TAG = "TestAdapter002";
    private LayoutInflater mInflater;
    private Context mContext;
    private List<SongTestObject> mData = new ArrayList<SongTestObject>();

    public TestAdapter002(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
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
            mViewHolder.mInformation = (TextView) view.findViewById(R.id.information_text);
            mViewHolder.mNumber = (TextView) view.findViewById(R.id.number_text);
            view.setTag(mViewHolder);
        }else{
            mViewHolder =(ViewHolder)view.getTag();
        }

        try{
            SongTestObject item = mData.get(i);
            if(null != item){
                mViewHolder.mNumber.setText(item.getNumber());
                mViewHolder.mInformation.setText(item.getSongInformation());
                Log.i(TAG,"getview number is :"+item.getNumber());
            }else{
                Log.i(TAG,"this position item is null. position is:"+i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    private final class ViewHolder
    {
        TextView mNumber;
        TextView mInformation;
    }
}
