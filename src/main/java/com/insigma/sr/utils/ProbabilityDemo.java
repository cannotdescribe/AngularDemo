package com.insigma.sr.utils;

import java.math.BigDecimal;

public class ProbabilityDemo {
    public static double success(int end){
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal end1 = new BigDecimal(end);
        BigDecimal bd = b1.divide(end1, 200, BigDecimal.ROUND_HALF_UP);
        return Math.pow((1-bd.doubleValue()), end);
    }
    public static void main(String[] args) {
        double d = success(1000000);
        System.out.println("成功期望: " + d);
        System.out.println("e: " + 1/d);

        System.out.println("java 给的e: "+Math.E);
    }
}
