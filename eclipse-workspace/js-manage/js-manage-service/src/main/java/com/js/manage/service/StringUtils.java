package com.js.manage.service;

public class StringUtils {
	public static Object ifNullReturnEmpty(Object obj) {
        return obj == null ? "" : obj;
    }

	public static boolean isEmpty(String input) {
	        return input == null || input.length() == 0;
	}
}
