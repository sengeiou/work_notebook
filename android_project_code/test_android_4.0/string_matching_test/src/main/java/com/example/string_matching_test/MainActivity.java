package com.example.string_matching_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG="sgx111";

    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,this.getPackageName()+" onCreate ");
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edt_input);
        mButton = findViewById(R.id.btn_confirm);
        Intent intent = getIntent();
        if (intent != null){
            Log.i(TAG,this.getPackageName()+" onCreate intent is not null");
            if(null != intent.getStringExtra("intent_set_string_values")){
                Log.i(TAG,this.getPackageName()+" onCreate intent getStringExtra is not null");
                mEditText.setText(intent.getStringExtra("intent_set_string_values"));
            }
        }
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_confirm:
                confirm();
                break;
            default:
                break;
        }
    }


    /**
     * 使用android API Pattern 和Matcher来正则匹配String
     */
    private void confirm(){
        String string = mEditText.getText().toString();
        Pattern pattern = Pattern.compile("[sgx]");
        Matcher matcher = pattern.matcher(string);

        String result = matcher.find() ? "\'匹配成功":"\'匹配失败";

        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("字符串\'"+string+result)
                .setPositiveButton("好的",null)
                .show();
    }
}
