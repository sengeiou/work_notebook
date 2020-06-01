package com.example.animation_test;

import android.animation.TypeEvaluator;
import android.util.Log;

public class ColorEvaluator implements TypeEvaluator {

    private int mCurrentRed = -1;

    private int mCurrentGreen = -1;

    private int mCurrentBlue = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        String startColor = (String) startValue;
        String endColor = (String) endValue;
        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);


        // 初始化颜色的值
        if (mCurrentRed == -1) {
            mCurrentRed = startRed;
        }
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }
        // 计算初始颜色和结束颜色之间的差值
        int redDiff = Math.abs(startRed - endRed);
        int greenDiff = Math.abs(startGreen - endGreen);
        int blueDiff = Math.abs(startBlue - endBlue);
        int colorDiff = redDiff + greenDiff + blueDiff;

        //以下为三位分开逐步变色
        if (mCurrentRed != endRed) {
            mCurrentRed = getCurrentColor(startRed, endRed, colorDiff, 0,
                    fraction);
        }else if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDiff,
                    redDiff, fraction);
        }else if (mCurrentBlue != endBlue) {
            mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDiff,
                    redDiff + greenDiff, fraction);
        }

        //以下为三位同时变色
//        if (mCurrentRed != endRed) {
//            mCurrentRed = getCurrentColor(startRed, endRed, fraction);
//        }
//        if (mCurrentGreen != endGreen) {
//            mCurrentGreen = getCurrentColor(startGreen, endGreen, fraction);
//        }
//        if (mCurrentBlue != endBlue) {
//            mCurrentBlue = getCurrentColor(startBlue, endBlue, fraction);
//        }
        // 将计算出的当前颜色的值组装返回
        String currentColor = "#" + getHexString(mCurrentRed)
                + getHexString(mCurrentGreen) + getHexString(mCurrentBlue);
        Log.i("sgx111","currentColor is :"+currentColor);
        return currentColor;
    }


    /**
     * 根据传入startcolor和endcolor以及fraction来直接算出对应色位的值，这里相当于每次三个色位都有变化，所以调用时，需要三个if语句
     * @param startColor
     * @param endColor
     * @param fraction
     * @return
     */
    private int getCurrentColor(int startColor, int endColor, float fraction){
        int currentColor;
        currentColor =startColor +(int) ((endColor - startColor)*fraction);
        return currentColor;
    }



    /**
     * 根据fraction值来计算当前的颜色。
     * sgx：补充这里是先对每一位的数据先渐变，完成后再渐变下一位，故只需要一个if else语句即可
     */
    private int getCurrentColor(int startColor, int endColor, int colorDiff,
                                int offset, float fraction) {
        //Log.i("sgx111","getCurrentColor() fraction is :"+fraction);
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * colorDiff - offset));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor + (fraction * colorDiff - offset));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

}