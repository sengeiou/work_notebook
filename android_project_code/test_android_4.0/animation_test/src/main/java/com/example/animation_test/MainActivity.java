package com.example.animation_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = "sgx111";

    private ImageView imageView001;
    private ImageView imageView002;

    private Animation scale;
    private Animation rotate;
    private Animation alpha;
    private Animation translate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"oncreate start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImageView animationImg1 = (ImageView) findViewById(R.id.test_animation);
//        AnimationDrawable animationDrawable1 = (AnimationDrawable) animationImg1.getBackground();
//        animationDrawable1.start();

//        ImageView animationImg2 = (ImageView) findViewById(R.id.test_alpha_animation);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
//        animationImg2.startAnimation(animation);
        initUI();
        //imageView001.animate().setDuration(5000).alpha(0.1f).x(500).y(500);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"ondestroy start");
        super.onDestroy();
    }

    private void initUI(){
        imageView001 = findViewById(R.id.test_animation);
        imageView002 = findViewById(R.id.test_alpha_animation);

        scale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        rotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        alpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        translate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        bindViewAcion();
    }

    private void bindViewAcion(){
        final int[] SourceID = {
                R.id.animation_scale,R.id.animation_translate,R.id.animation_alpha,
                R.id.animation_rotate,R.id.animation_attribute_java,R.id.animation_attribute_xml,
                R.id.animation_single_attribute,R.id.animation_attribute_my_view,R.id.btn_start_activity,
                R.id.btn_start_browser,
        };

        for(int id : SourceID){
            View v = findViewById(id);
            if (v != null) {
                v.setOnClickListener(this);
            }
        }
    }

    //属性动画通过xml来实现效果
    private void attribute_animset_xml(){
        @SuppressLint("ResourceType") Animator animator = AnimatorInflater.loadAnimator(this,R.anim.anim_attribute);
        animator.setTarget(imageView002);
        animator.start();
    }

    //属性动画——多属性变化_组合变化
    private void attribute_animset(){
        ObjectAnimator remove = ObjectAnimator.ofFloat(imageView002,"translationX",0f,-500f,0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView002,"rotation",0f,720f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView002,"alpha",1f,0.5f,1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView002,"scaleX",1f,3f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView002,"scaleY",1f,3f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(remove).with(rotate).with(alpha).with(scaleX).with(scaleY);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    //属性动画——单属性变化
    private void attribute_animation(){
        float curtranslationx = imageView002.getTranslationX();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView002,"translationX",curtranslationx,-500,curtranslationx);
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }

    //属性动画——自定义对象属性
    private void point_animation(){
        Point point1 = new Point(100,100);
        Point point2 = new Point(400,400);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),point1,point2);
        animator.setDuration(5000);
        animator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.animation_scale:
                imageView002.startAnimation(scale);
                break;
            case R.id.animation_alpha:
                imageView002.startAnimation(alpha);
                break;
            case R.id.animation_rotate:
                imageView002.startAnimation(rotate);
                break;
            case R.id.animation_translate:
                imageView002.startAnimation(translate);
                break;
            case R.id.animation_attribute_java:
                attribute_animset();//java代码实现属性动画
                break;
            case R.id.animation_attribute_xml:
                attribute_animset_xml();//xml实现属性动画，需要调用java接口
                break;
            case R.id.animation_single_attribute:
                attribute_animation();
                break;
            case R.id.animation_attribute_my_view:
                point_animation();
                break;
            case R.id.btn_start_activity:
                Intent intent = new Intent();
                intent.setClassName("com.example.string_matching_test","com.example.string_matching_test.MainActivity");
                intent.putExtra("intent_set_string_values","sgx is handsome boy!");
                startActivity(intent);
                break;
            case R.id.btn_start_browser:
                Uri uri = Uri.parse("https://www.baidu.com");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent1);
            default:
                break;
        }
    }
}
