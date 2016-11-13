package com.mh.web.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class FastUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public static String getJson(Object object) {
		return JSON.toJSONString(object);
	}

	public static Object parseObject(String jsonString, Class<?> collectionClass) {
		return JSON.parseObject(jsonString, collectionClass);
	}

	public static List<?> parseArray(String jsonString, Class<?> collectionClass) {
		return JSON.parseArray(jsonString, collectionClass);
	}

}
