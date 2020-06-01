package com.example.animation_test;

import android.animation.TypeEvaluator;

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point)startValue;
        Point endPoint = (Point)endValue;
        float x = startPoint.getX() +fraction*(endPoint.getX() - startPoint.getX());
//        float y = startPoint.getY() +fraction*(endPoint.getY() - startPoint.getY());
        //endPoint.getY()为中线高度 ，200为振幅大小
        float y = endPoint.getY() + (float) (200 * Math.sin(2*Math.PI*fraction));
        Point point = new Point(x,y);
        return point;
    }
}
