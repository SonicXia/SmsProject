package com.dgaotech.dgfw;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import jodd.io.FileUtil;
import jodd.util.ClassLoaderUtil;
/**
 * 
 * @author xiaxf
 *
 */
@Service
public class SmsTemplateService {
	/**
	 *  模板1：尊敬的${customerName}：您已成功支付火车票订单，正在为您努力出票中。稍后将会短信通知您出票结果。
		模板2：尊敬的${customerName}：您已成功订购${ticketNum}张车票，${trainInfo}请您持有效证件及时取票、乘车。
		模板3：尊敬的${customerName}：您所订购的${trainInfo}出票失败，退款将会在${days}个工作日内按照您原有的付款渠道返回到您的账户上。由此带来的不便我们向您道歉！
		模板4：尊敬的${phone}乘客：您已成功订购以下${productNum}件物品：${orderInfo}订单总额：${total}元。餐服员正在处理配送中，如未能及时送达，请主动联系车上餐服员，或拨打${customerPhoneNo}客服专线处理。感谢您的配合！
		模板5：${info}有新订单，手机号码：${phone}，送达位置：${seat}，订单总额：${total}元，${orderInfo}请及时处理
		模板6：尊敬的${customerName}乘客：您已成功充值人民币${balance}元。您可以使用此充值购买火车票、在车上点餐和购买商品。您在使用过程中如有意见或疑问，请拨打${customerPhoneNo}客服专线进行处理。感谢您的支持！
	 * @param type 选用模板的类型。当type=1时对应模板1，type=2时对应模板2，以此类推。
	 * @param dataMap 传入模板的参数
	 * @param username 传入的用户名，用于验证
	 * @param password 传入的用户密码，用于验证
	 */

	public Map<String, String> smsTemplate(String type,JSONObject data, String username, String password, String mobiles){	
		Map<String, String> rsp = new HashMap<String, String>();
		if(validate(username, password)){
			Properties prop = new Properties();
			try {
				prop.load(SmsTemplateService.class.getClassLoader().getResourceAsStream("config.properties"));
			} catch (IOException e3) {
				e3.printStackTrace();
			}			
            String url = prop.getProperty("url");// 应用地址
			String account = prop.getProperty("account");// 账号
			String pswd = prop.getProperty("pswd");// 密码
			String mobile = mobiles;// 手机号码，多个号码使用","分割
			boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
			String product = null;// 产品ID
			String extno = null;// 扩展码
			File templateFile = ClassLoaderUtil.getResourceFile("SmsTemplate.txt");
	    	String[] templateStrs = null;
			try {
				templateStrs = FileUtil.readLines(templateFile);		
			} catch (IOException e2) {
				e2.getMessage();
			}
			//type对应模板类型
			int index = Integer.parseInt(type, 10);
	    	String template = templateStrs[index];
	        String msg = null;
			try {
				msg = new SmsTemplateService().buildMsg(template,data);
//				System.out.println("测试信息 = " + msg);
			} catch (Exception e1) {
				e1.getMessage();
			}                                                                                              			
			try {
				String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
//				System.out.println("returnString = " + returnString);	
				rsp.put("status", "ok");
				rsp.put("msg", "操作成功！");
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String syncTime = sdf.format(date);
				rsp.put("syncTime", syncTime);
			} catch (Exception e) {
				e.getMessage();
				}
		}else {
		rsp.put("status", "false");
		rsp.put("msg", "用户名或密码错误!");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String syncTime = sdf.format(date);
		rsp.put("syncTime", syncTime);
//		System.out.println("对不起，用户名密码验证失败！");
		}
			return rsp;
	}
	
	public static String buildMsg(String template,JSONObject data ) {
        Properties p=new Properties();
        Set<String> keySet = data.keySet();
        for(String key:keySet) {
        	p.put(key, data.get(key));
        }
        String str = "";
		try {
			str =  new SmsTemplateService().composeMessage(template,p);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return str;
	}
	
	public String composeMessage(String template,Properties data) throws Exception{
        Iterator it =data.entrySet().iterator();
        while(it.hasNext()){
            Object o=it.next();
            template=template.replaceFirst("\\$\\{"+o.toString().split("=")[0]+"}", o.toString().split("=")[1]);
        }                                                                                                     
        return template;
    }
	
	public static boolean validate(String username, String password){
		Properties prop = new Properties();
		try {
			prop.load(SmsTemplateService.class.getClassLoader().getResourceAsStream("validate.properties"));
            if(username.equals(prop.getProperty("username")) && password.equals(prop.getProperty("password"))){
            	return true;
            }     
		} catch (IOException e) {
			e.getMessage();
		}  
		return false;
	}
}
