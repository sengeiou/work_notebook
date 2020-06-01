package com.example.animation_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyAnimView extends View {

    private String color;

    //圆半径控制
    public float RADIUS ;

    private Point currentPoint;

    private Paint mPaint;

    //开始动画圆形颜色
    private String startColor;

    //结束动画圆形颜色
    private String endColor;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myAnimView);
        RADIUS = typedArray.getFloat(R.styleable.myAnimView_radiusize,50f);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        startColor = typedArray.getString(R.styleable.myAnimView_startColor);
        endColor = typedArray.getString(R.styleable.myAnimView_endColor);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    public void setRADIUS(float RADIUS) {
        this.RADIUS = RADIUS;
    }

    public float getRADIUS() {
        return RADIUS;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            //绘制圆形
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
        Log.i("sgx222","onDraw form MyAnimView");
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(0+RADIUS, getHeight()*2/3);
        Point endPoint = new Point(getWidth()- 2*RADIUS, getHeight()*2/3);

        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                //动态变化圆形半径根据x坐标变化
                invalidate();
            }
        });
        //这里可以设置Interpolator控制运动速率（当前设置为加速的控制类AccelerateInterpolator），默认为先加速再减速（AccelerateDecelerateInterpolator对应此实现类）。
//        anim.setInterpolator(new AccelerateInterpolator(2f));
        anim.setInterpolator(new MyInterprolator());
        anim.setRepeatCount(-1);

        //属性控制，包括颜色和半径
        //备注：对应propertyName需要在当前view中建立好set函数，否则不识别该属性
        ObjectAnimator animator_color = ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),startColor,endColor,startColor);
        ObjectAnimator animator_radius = ObjectAnimator.ofObject(this,"RADIUS",new Radius_Evaluator(),RADIUS,RADIUS*2,RADIUS);
        animator_color.setRepeatCount(-1);
        animator_radius.setRepeatCount(-1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(anim).with(animator_color).with(animator_radius);
        animatorSet.setDuration(5000);
        animatorSet.start();

    }
}