package com.example.animation_test;

import android.animation.TypeEvaluator;

public class Radius_Evaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        float result = 0;
        float startRadius = (float) o;
        float endRadius = (float) t1;
        result =(startRadius + v*(endRadius - startRadius));
        return result;
    }
}
