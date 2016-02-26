package com.dgaotech.common;

import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 * 
 * @author huangzy
 */
public class JPushUtil {
	// 广播发送类型
	public static final int PUSH_TYPE_ALL = 0;
	// 按照alias发送
	public static final int PUSH_TYPE_ALIAS=1;
	// 按照tag发送
	public static final int PUSH_TYPE_TAG=2;
	
	// 极光推送相关常量
	@Value("Jpush.appKey")
	private static String APP_KEY;
	
	@Value("Jpush.masterSecret")
	private static String MASTER_KEY;
	//private static final String APP_KEY = PropertiesReader.getJPushAppKey();
	//private static final String MASTER_KEY = PropertiesReader.getJPushMasterSecret();
	private static Logger logger = LogManager.getLogger(JPushUtil.class);
	private static JPushClient client = new JPushClient(MASTER_KEY, APP_KEY, null, getClientConfig());
	private static final ClientConfig getClientConfig() {
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(3);
		return config;
	}

	/**
	 * 发送消息给所有用户
	 * 
	 * @param msg
	 * @param alias
	 */
	public final static PushResult sendMsgToAll(final String msg) {
		
		PushPayload payload = buildPushObjectAll(msg);
		PushResult result = new PushResult();
		try {
			result = client.sendPush(payload);
			logger.info("推送结果 - " + result);
		} catch (APIConnectionException e) {
			logger.error("连接错误, 应该会稍后重试。", e);
		} catch (APIRequestException e) {
			logger.error("从JPush服务器错误响应。应该检查并修复它. ", e);
		}
		
		return result;
	}
	
	public final static PushResult sendMsgByAlias(final String msg,final String... alias) {
		PushPayload payload = buildByAlias(msg,alias);
		PushResult result  = new PushResult();
		try {
			result = client.sendPush(payload);
			logger.info("推送结果 - " + result);
		} catch (APIConnectionException e) {
			logger.error("连接错误, 应该会稍后重试。", e);
		} catch (APIRequestException e) {
			logger.error("从JPush服务器错误响应。应该检查并修复它. ", e);
		}
		
		return result;
	}

	public final static PushResult sendMsgByTag(final String msg,final String... tag) {
		PushPayload payload = buildByTag(msg, tag);
		PushResult result  = new PushResult();
		try {
			result= client.sendPush(payload);
			logger.info("推送结果 - " + result);
		} catch (APIConnectionException e) {
			logger.error("连接错误, 应该会稍后重试。", e);
		} catch (APIRequestException e) {
			logger.error("从JPush服务器错误响应。应该检查并修复它. ", e);
		}
		
		return result;
	}
	private final static PushPayload buildByAlias(final String msg,final String... alias) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(msg)).build();
	}
	
	private final static PushPayload buildByTag(final String msg,final String... tag) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tag))
				.setNotification(Notification.alert(msg)).build();
	}
	
	public final static PushPayload buildPushObjectAll(final String msg) {
		return PushPayload.alertAll(msg);
	}
	public static void main(String[] args) {
		sendMsgByAlias("test1234"+new Random().nextLong(), "cfz");
//		sendMsgByAlias("测试消息001", "18656550580");
//		sendMsgToAll("测试消息发送");
	}
}
