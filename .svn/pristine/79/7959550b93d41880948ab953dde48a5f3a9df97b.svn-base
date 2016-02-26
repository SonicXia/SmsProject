package com.dgaotech.dgfw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	

    // 用于读取配置文件的信息,默认从resources目录下的application.properties文件中读取
    @Value("${EmailQueueListener.enabled}")
    public boolean EmailQueueListenerEnabled;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
     @PostConstruct
        public void init() {
           // 初始化一些服务,例如队列服务等
        }
    
     
     @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
     public List<String> sendMail(String title, String body, String... to) {
    	List s = new ArrayList();
    //	s.add(title);
    	s=this.jdbcTemplate.queryForList("select * from line_info");
        return s;
     }
     
     public boolean getEnabled() {
         return EmailQueueListenerEnabled;
      }
}
