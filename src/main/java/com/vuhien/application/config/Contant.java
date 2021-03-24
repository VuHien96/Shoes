package com.vuhien.application.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contant {

    // 7 ngày
    public static final int MAX_AGE_COOKIE = 7 * 24 * 60 * 60;
    //Limit
    public static final int LIMIT_BRAND = 5;
    public static final int LIMIT_CATEGORY = 5;
    public static final int LIMIT_PRODUCT = 5;

    //Size giày
    public static final List<Integer> SIZE_VN = new ArrayList<>(Arrays.asList(35, 36, 37, 38, 39, 40, 41, 42));
    public static final double[] SIZE_US = {2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5};
    public static final double[] SIZE_CM = {21.3, 22.2, 23, 23.8, 24.6, 25.4, 26.2, 27.1};
}
