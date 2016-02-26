package com.dgaotech.common;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.dgaotech.dgfw.Application;

/**
 * spring boot项目打包成war需要的初始化类
 * 
 * @author huangzy
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	// 这里可以对application进行预处理
	  
    return application.sources(Application.class);
  }

}