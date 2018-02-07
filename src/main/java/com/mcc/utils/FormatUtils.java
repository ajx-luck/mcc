package com.mcc.utils;

/**
 * Created by B04e on 2018/2/7.
 */
public class FormatUtils {
    public static String showCoin(long coin){
        return String.format("%.6f",coin/1000000f);
    }
}
