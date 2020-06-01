package com.example.my_application_test;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Fragment001 extends Fragment implements View.OnClickListener {
    private MainActivity mActivity;
    private static final String TAG = "Fragment001";
    private TestAdapter001 testAdapter001 = null;
    private TestAdapter002 testAdapter002 = null;
    //    private Fragment001ViewModel mViewModel;
    private View mView;
    private TextView mtextview;
    private ListView mleftlistview;
    private ListView mrightlistview;
    public static Fragment001 newInstance() {
        return new Fragment001();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView");
        mActivity = (MainActivity)getActivity();
        mView = inflater.inflate(R.layout.fragment001_fragment, null);
        initUi();
        initEvent();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(Fragment001ViewModel.class);
        // TODO: Use the ViewModel
        Log.i(TAG,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach");
    }

    private void initUi(){
        mtextview = (TextView)mView.findViewById(R.id.text001);
        mleftlistview = (ListView)mView.findViewById(R.id.listview01_fragment001);
        testAdapter001 = new TestAdapter001(mActivity);
        testAdapter001.setmData(mActivity.queryAllItemData());
        mleftlistview.setAdapter(testAdapter001);

        //mrightlistview = (ListView)mView.findViewById(R.id.listview02_fragment001);
//        testAdapter002 = new TestAdapter002(mActivity);
//        testAdapter002.setmData(null);
//        mrightlistview.setAdapter(testAdapter002);
    }

    private void initEvent(){
        mtextview.setOnClickListener(this);
        mleftlistview.setOnItemClickListener(mLeftItemListener);
    }

//    private List<SongTestObject> getDatalist(){
//        List <SongTestObject> data = new ArrayList<SongTestObject>();
//        SongTestObject songTestObject  = null;
//        for (int i = 1; i < 10;i++){
//            songTestObject = new SongTestObject(i+"","artist00"+i);
//            data.add(songTestObject);
//        }
//        Log.i(TAG,"data size is :"+data.size());
//        return data;
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text001:
                mActivity.showFragment002();
                break;
            default:
                break;
        }
    }

    private AdapterView.OnItemClickListener mLeftItemListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            SongTestObject mSongTestObject = (SongTestObject) testAdapter001.getItem(i);
//            if (null != mSongTestObject) {
//                List<SongTestObject> rightlist = getChangeAdapter002DataFor001(Integer.valueOf(mSongTestObject.getNumber()));
//                testAdapter002.setmData(rightlist);
//            }

        };
    };

    public List<SongTestObject> getChangeAdapter002DataFor001(int i){
        List<SongTestObject> data = new ArrayList<SongTestObject>();
        SongTestObject songTestObject = null;
        if (i<0)
            return null;
        for (int j = 1;j < i+1;j++){
            songTestObject = new SongTestObject(j+"","this data foronclick leftlist "+i);
            data.add(songTestObject);
        }
        return data;
    }

    public void updataUi(){
        testAdapter001.setmData(mActivity.queryAllItemData());
    }
}
