package com.js.manage.service;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class NumberUtils {
	public static String numberFormat(double num, int degree) {
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();
        df.setMaximumFractionDigits(degree);
        return df.format(num);
    }
}
