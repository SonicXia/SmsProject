package com.dgaotech.dgfw.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class LineDao extends BaseDao {
	
	//从属性文件中得到sql;
//	@Value("${line.getall}")
//	public String getAll;
	
	public List getAll(){
		return this.jdbctemplate.queryForList("select * from line_info");
	}

	public List getById(String id) {
		 return this.jdbctemplate.queryForList("select * from line_info where id=?", new Object[]{id});
	}
	
	
	
	public void insert_success() throws Exception{
		for(int i=0;i<10;i++){
	        jdbctemplate.update("insert into z_test(name,age) values(?,?)", new Object[]{"sonic"+i,i});
	    }
		System.out.println("插入成功！！");
	}
	
	public void insert_rollback() throws Exception{
		for(int i=0;i<10;i++){
	        if(i==8)throw new Exception("====插入错误，回滚=======");	            
	        jdbctemplate.update("insert into z_test(name,age) values(?,?)", new Object[]{"sonic"+i,i});
	    }
		System.out.println("插入成功！！");
	}
	
}
