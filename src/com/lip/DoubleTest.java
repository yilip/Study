package com.lip;


import com.lip.uitl.MathUtil;

import java.math.BigDecimal;

/**
 * @Author Lip
 * @Date 2016-10-31 11:38
 */
public class DoubleTest {
    public static void main(String[] args) {
        System.out.println(roundup(mul(34.3,0.75),2));
        System.out.println(MathUtil.sub(0.45,0.3));
    }
    private static double roundup(double orig, int precision) {
        System.out.println(orig);
        return new BigDecimal(String.valueOf(orig)).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double mul(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }
}
