package com.dbs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

	// 利用时间戳生成独一无二的id
	public static String generateId() {
		String uuid = null;
		Date date = new Date();
		uuid = String.valueOf(date.getTime());
		// uuid =uuid.replaceAll(":", "");
		// 需要利用正则表达式将字段里面的空格去除
		return uuid;
	}

	// 空值判断
	public static String ckeckNull(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	// 写一个方法生成房产编号
	public static String generateNO(int num) {
		// 房产编号的格式2019-05-11+第多少个
		SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd");
		String date = df.format(new Date());
		String count = buzu(num);
		StringBuffer no = new StringBuffer();
		no.append(date);
		no.append(count);
		return no.toString();
	}

	// 补足0的方法,生成一个四位的编码
	public static String buzu(int num) {
		String number = "";
		if (num < 10) {
			number = "000" + num;
		} else if (num < 100) {
			number = "00" + num;
		} else if (num < 1000) {
			number = "0" + num;
		} else if (num < 10000) {
			number = "" + num;
		} else {
			number = "XXXX";
		}
		return number;
	}

	// 将yyyy-MM-dd
	public static java.sql.Date formDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new java.sql.Date(sdf.parse(date).getTime());
	}
	

	public static Date formDate2(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new Date(sdf.parse(date).getTime());
	}
	
	public static String formDate3(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
