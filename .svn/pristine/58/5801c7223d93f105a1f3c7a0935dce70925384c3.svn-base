package com.dgaotech.dgfw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dgaotech.common.JPushUtil;

import cn.jpush.api.push.PushResult;
import jodd.util.StringUtil;

/**
 * 推送控制器
 * 
 * @author Administrator
 *
 */
@ResponseBody
@RestController
public class PushController {

	/**
	 * 根据不同类型推送消息服务
	 * 
	 * @param type
	 *  推送类型          0-->to all,1-->by alias,2-->by tag
	 * @param params 
	 * 推送参数 {"msg":"消息内容","alias":"消息别名,多个用英文逗号分隔","tag":"消息tag,多个用英文逗号分隔"}
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/push/{type}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject push(@PathVariable("type") int type, @RequestBody JSONObject params) throws Exception {
		JSONObject obj = new JSONObject();
		String msg = params.getString("msg");
		String alias = params.getString("alias");
		String tag = params.getString("tag");
		PushResult pushResult = null;
		switch (type) {
		case JPushUtil.PUSH_TYPE_ALL:
			pushResult = JPushUtil.sendMsgToAll(msg);
			break;
		case JPushUtil.PUSH_TYPE_ALIAS:
			if (StringUtil.isNotBlank(alias) && alias.contains(",")) {
				String[] aliasArr = alias.split(",");
				pushResult = JPushUtil.sendMsgByAlias(msg, aliasArr);
			} else {
				pushResult = JPushUtil.sendMsgByAlias(msg, alias);
			}
			break;
		case JPushUtil.PUSH_TYPE_TAG:
			if (StringUtil.isNotBlank(tag) && tag.contains(",")) {
				String[] tagArr = tag.split(",");
				pushResult = JPushUtil.sendMsgByTag(msg, tagArr);
			} else {
				pushResult = JPushUtil.sendMsgByTag(msg, tag);
			}

			break;
		default:
			pushResult = new PushResult();
		}
		obj.putAll(buildReturnResult(pushResult));
		return params;
	}

	private Map<? extends String, ? extends Object> buildReturnResult(PushResult pushResult) {
		Map<String, Object> retObj = new HashMap<String, Object>();
		retObj.put("optDate", new Date());
		if (pushResult.isResultOK()) {
			retObj.put("status", "success");
		} else {
			retObj.put("status", "fail");
		}
		retObj.put("result", pushResult.toString());
		return retObj;
	}

}
