package com.example.animation_test;

import android.animation.TimeInterpolator;

public class MyInterprolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float v) {
        // 这里相当于传入值v（0-1）和返回值result的一个二元函数
        // 动画所依赖的fraction其实就是result，所以这里可以控制动画速率的快慢，甚至可以控制动画的起始点
        float result = 0;
        result = (float)(-4*(v-0.5)*(v-0.5) + 1);
        return result;
    }
}
