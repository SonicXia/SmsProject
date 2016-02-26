package com.dgaotech.common;

import java.io.IOException;
import java.util.Properties;

import jodd.util.PropertiesUtil;

/**
 * 通过属性文件读取sql语句
 * 
 * @author huangzy
 */
public abstract class PropertiesReader {
	private static Properties sqlProperties = PropertiesUtil.createFromClasspath("/sql.properties");
	private static Properties jpushProperties = PropertiesUtil.createFromClasspath("/jpush.properties");

	public final static String getSql(String key) {
		return sqlProperties.getProperty(key, "").trim();
	}

	public final static String getJPushAppKey() {
		return jpushProperties.getProperty("appKey", "").trim();
	}

	public final static String getJPushMasterSecret() {
		return jpushProperties.getProperty("masterSecret", "").trim();
	}

	public static void main(String[] args) throws IOException {
		// System.out.println(getSql("findUser.sql"));
		System.out.println(getJPushAppKey());
		System.out.println(getJPushMasterSecret());
	}
}
