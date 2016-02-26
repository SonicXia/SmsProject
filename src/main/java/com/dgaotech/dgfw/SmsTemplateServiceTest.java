package com.dgaotech.dgfw;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author xiaxf
 *
 */
public class SmsTemplateServiceTest {
	
//	public static Map<String, String> template1(String type, JSONObject data, ){
//		return null;
//		
//	}
	public static void main(String[] args) {
    	JSONObject data =new JSONObject();
    	data.put("customerName", "张三");
    	data.put("balance", "100.00");
    	data.put("customerPhoneNo", "0551-68151155");
    	String mobiles = "13625512317";
    	SmsTemplateService sts = new SmsTemplateService(); 
    	Map<String, String> rsp = sts.smsTemplate("6", data, "1dgfw", "test_dgfw", mobiles);
    	System.out.println("status = " + rsp.get("status") + 
    			", syncTime = " + rsp.get("syncTime") + ", msg = " + rsp.get("msg"));
	}

}
