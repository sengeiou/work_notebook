package com.example.my_application_test;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment002 extends Fragment implements View.OnClickListener {
    private MainActivity mActivity;
    private TextView mtextview;
    private EditText username;
    private EditText password;
    private Button confirm;
    private Button cancel;

    private static final String TAG = "Fragment002";
    //    private Fragment001ViewModel mViewModel;
    private View mView;

    public static Fragment002 newInstance() {
        return new Fragment002();
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
        mView = inflater.inflate(R.layout.fragment002_fragment, null);
        initUi();
        initEvent();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        mtextview = (TextView)mView.findViewById(R.id.text002);
        username = (EditText)mView.findViewById(R.id.username);
        password = (EditText)mView.findViewById(R.id.password);
        confirm = (Button) mView.findViewById(R.id.confirm);
        cancel = (Button) mView.findViewById(R.id.cancel);
    }

    private void initEvent(){
        mtextview.setOnClickListener(this);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void confirm(){
        String name = username.getText().toString();
        String pd= password.getText().toString();
        SongTestObject songTestObject = null;
        if(null != name && !name.equals("") && null != pd && !pd.equals("")){
            songTestObject = new SongTestObject(name,pd);
            mActivity.saveItem(songTestObject);
        }else{

        }
        cancel();
    }

    private void cancel(){
        username.setText("");
        password.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text002:
                mActivity.showFragment001();
                break;
            case R.id.confirm:
                confirm();
                break;
            case R.id.cancel:
                cancel();
                mActivity.showFragment001();
                break;
            default:
                break;
        }
    }

}
