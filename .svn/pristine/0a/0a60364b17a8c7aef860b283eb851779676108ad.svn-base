package com.dgaotech.dgfw.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class BaseController {

	 public void processJSON(HttpServletResponse response, Map<String, Object> data) throws IOException {
	     if (data == null) data = new HashMap<String, Object>();
	 
	     response.setContentType("text/html; charset=utf-8");
	     response.setCharacterEncoding("utf-8");
	 
	     Writer out = response.getWriter();
	     String json = JSON.toJSONString(data);
	     //LOG.debug(json);
	     out.write(json);
	   }
}
