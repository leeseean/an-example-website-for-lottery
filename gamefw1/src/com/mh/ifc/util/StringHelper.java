package com.mh.ifc.util;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.impl.cookie.DateUtils;

/**
 *
 */
public class StringHelper {

	public static final String WORD_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random random = new Random();

	/**
	 * 将byte类型转为hex字符串，未满2位则前面补零
	 * 
	 * @param byteval
	 * @return
	 */
	public static String format(byte byteval) {
		return format(Integer.toHexString(byteval), 2);
	}

	/**
	 * 将int类型转为hex字符串，未满8位则前面补零
	 * 
	 * @param intval
	 * @return
	 */
	public static String format(int intval) {
		return format(Integer.toHexString(intval), 8);
	}

	/**
	 * 将long类型转为hex字符串，未满16位则前面补零
	 * 
	 * @param longval
	 * @return
	 */
	public static String format(long longval) {
		return format(Long.toHexString(longval), 16);
	}

	/**
	 * 将short类型转为hex字符串，未满4位，则前面补零
	 * 
	 * @param shortval
	 * @return
	 */
	public static String format(short shortval) {
		return format(Integer.toHexString(shortval), 4);
	}

	/**
	 * 将指定字符串格式换成指定长度，如字符串小于指定长度未满则前面补零
	 * 
	 * @param formatted
	 *            指定字符串
	 * @param length
	 *            指定长度
	 * @return
	 */
	public static String format(final String formatted, final int length) {
		return format(formatted, length, '0');
	}

	/**
	 * 将指定字符串格式换成指定长度，如字符串小于指定长度未满则前面补充指定字符
	 * 
	 * @param formatted
	 *            指定字符串格
	 * @param length
	 *            指定长度
	 * @param infilling
	 *            指定字符
	 * @return
	 */
	public static String format(final String formatted, final int length, final char infilling) {
		if (StringUtils.isBlank(formatted) || formatted.length() > length) {
			return formatted;
		}
		final StringBuilder builder = new StringBuilder(length);
		while (builder.length() < length) {
			builder.append(infilling);
		}
		builder.replace(length - formatted.length(), length, formatted);
		return builder.toString();
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static String getLineTimeStamp() {
		return DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS");
	}

	public static String getNewStringFrom(String... strings) {
		StringBuilder builder = new StringBuilder();
		if (!ArrayUtils.isEmpty(strings)) {
			for (String string : strings) {
				builder.append(string);
			}
		}
		return builder.toString();
	}

	public static String getPrimalPhoneNumberFrom(String mdn) {
		if (StringUtils.isNotBlank(mdn)) {
			if (mdn.startsWith("+")) {
				mdn = mdn.substring(1, mdn.length());
			}
			if (mdn.startsWith("0")) {
				mdn = mdn.substring(1, mdn.length());
			}
			if (mdn.startsWith("86")) {
				mdn = mdn.substring(2, mdn.length());
			}
		}
		return mdn;
	}

	/**
	 * 获取长度为3的随机数字字符串
	 * 
	 * @return
	 */
	public static String getRandomNumber() {
		return getRandomNumber(3);
	}

	/**
	 * 根据长度获取随机字符串数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(final int length) {
		final int rand = random.nextInt(); // 随机整数
		String number = Integer.toString(Math.abs(rand));
		if (number.length() > length) {
			number = number.substring(number.length() - length, number.length());
		} else {
			while (length - number.length() > 0) {
				number = 0 + number;
			}
		}
		return number;
	}

	public static String getRandomStringFrom(final int radix) {
		return getRandomStringFrom(WORD_CHARACTERS, radix);
	}

	public static String getRandomStringFrom(final String text, final int radix) {
		StringBuilder builder = new StringBuilder();
		if (StringUtils.isNotBlank(text)) {
			for (int i = 0; i < NumberUtils.max(new int[] { 0, radix }); i++) {
				builder.append(text.charAt(random.nextInt(text.length())));
			}
		}
		return builder.toString();
	}

	/**
	 * 判断是否为整型数据
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断是否为long类型字符串
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isLong(String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static long toLong(String value, int radix) {
		return toLong(WORD_CHARACTERS, value, radix);
	}

	public static long toLong(String wordCharacters, String value, int radix) {
		return toLong(wordCharacters, value, radix, 0);
	}

	public static long toLong(String wordCharacters, String value, int radix, long defaultValue) {
		wordCharacters = StringUtils.trim(wordCharacters);
		if (StringUtils.isBlank(wordCharacters) || value == null) {
			return defaultValue;
		}
		radix = radix < 2 || radix > wordCharacters.length() ? wordCharacters.length() : radix;
		wordCharacters = wordCharacters.substring(0, radix);
		final int length = value.length();
		long result = 0;
		for (int i = 0; i < length; i++) {
			char c = value.charAt(length - i - 1);
			long val = wordCharacters.indexOf(c);
			if (val < 0) {
				return defaultValue;
			}
			result += val * Math.pow(radix, i);
		}
		return result;
	}

	public static String toLowerCase(String text) {
		return text == null ? null : text.toLowerCase();
	}

	public static String toLowerCase(String text, Locale locale) {
		return text == null ? null : text.toLowerCase(locale);
	}

	public static String toString(long value, int radix) {
		return toString(WORD_CHARACTERS, value, radix);
	}

	public static String toString(String wordCharacters, long value, int radix) {
		wordCharacters = StringUtils.trim(wordCharacters);
		if (StringUtils.isBlank(wordCharacters)) {
			return wordCharacters;
		}
		radix = radix < 2 || radix > wordCharacters.length() ? wordCharacters.length() : radix;
		long mod = value % radix; // 取模
		long div = value / radix; // 取整
		String text = String.valueOf(wordCharacters.charAt((int) mod));
		if (div != 0) {
			if (div < radix) {
				text = wordCharacters.charAt((int) div) + text;
			} else {
				text = toString(wordCharacters, div, radix) + text;
			}
		}
		return text;
	}

	public static String toUpperCase(String text) {
		return text == null ? null : text.toUpperCase();
	}

	public static String toUpperCase(String text, Locale locale) {
		return text == null ? null : text.toUpperCase(locale);
	}

	/**
	 * 判断是否为byte类型字符串
	 * 
	 * @param input
	 * @return
	 */
	public boolean isByte(String input) {
		try {
			Byte.parseByte(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 随机字母与数字组合
	 * 
	 * @param length
	 * @return
	 */

	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = 97; // 取得大写字母65还是小写字母97
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}

		return val;
	}
}
