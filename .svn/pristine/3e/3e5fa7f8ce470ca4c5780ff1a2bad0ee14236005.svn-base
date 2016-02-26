package com.dgaotech.dgfw;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	  public static void main(String[] args) {
	      JedisPool pool = new JedisPool("112.26.201.209", 6379);
	      Jedis jedis = pool.getResource();
	      jedis.set("site", "dataguru");
	      System.out.println(jedis.get("site"));
	  }
}
