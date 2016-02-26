package com.dgaotech.dgfw.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dgaotech.dgfw.SmsTemplateService;
/**
 * 
 * @author xiaxf
 *
 */
@ResponseBody
@RestController
public class SmsTemplateController {
	
	@Autowired
	private SmsTemplateService smsTemplateService;
	
	  @ResponseBody
	  @RequestMapping(value = "/smsTemplate", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	  public Map<String, String> smsTemplate(@RequestBody JSONObject jsoncode)throws Exception {

	       return smsTemplateService.smsTemplate(jsoncode.getString("type"), jsoncode.getJSONObject("data"),
	    		   jsoncode.getString("username"), jsoncode.getString("password"),jsoncode.getString("mobiles"));
	    }
	
	  

}
