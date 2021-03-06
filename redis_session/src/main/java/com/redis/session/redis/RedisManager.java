package com.redis.session.redis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
	private String host = "172.16.139.200";  
    
    private int port = 6379;  
      
    // 0 - never expire  
    private int expire = 0;  
      
    //timeout for jedis try to connect to redis server, not expire time! In milliseconds  
    private int timeout = 0;  
      
    private String password;  
      
    private static JedisPool jedisPool = null;  
      
    public RedisManager(){  
    	init();
    }  
      
    /** 
     * 初始化方法 
     */  
    public void init(){  
    	String classpath = RedisManager.class.getResource("/").getPath();
    	try {
			Properties pro = new Properties();
			pro.load(new FileInputStream(classpath+File.separator+"redis.properties"));
			host = StringUtils.isBlank(pro.getProperty("host"))?host:pro.getProperty("host");
			port = StringUtils.isBlank(pro.getProperty("port"))?port:Integer.parseInt(pro.getProperty("port"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        if(jedisPool == null){  
            if(password != null && !"".equals(password)){  
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);  
            }else if(timeout != 0){  
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port,timeout);  
            }else{  
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port);  
            }  
              
        }  
    }  
      
    /** 
     * get value from redis 
     * @param key 
     * @return 
     */  
    public byte[] get(byte[] key){  
        byte[] value = null;  
        Jedis jedis = jedisPool.getResource();  
        try{  
            value = jedis.get(key);  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
        return value;  
    }  
      
    /** 
     * set  
     * @param key 
     * @param value 
     * @return 
     */  
    public byte[] set(byte[] key,byte[] value){  
        Jedis jedis = jedisPool.getResource();  
        try{  
            jedis.set(key,value);  
            if(this.expire != 0){  
                jedis.expire(key, this.expire);  
            }  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
        return value;  
    }  
      
    /** 
     * set  
     * @param key 
     * @param value 
     * @param expire 
     * @return 
     */  
    public byte[] set(byte[] key,byte[] value,int expire){  
        Jedis jedis = jedisPool.getResource();  
        try{  
            jedis.set(key,value);  
            if(expire != 0){  
                jedis.expire(key, expire);  
            }  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
        return value;  
    }  
      
    /** 
     * del 
     * @param key 
     */  
    public void del(byte[] key){  
        Jedis jedis = jedisPool.getResource();  
        try{  
            jedis.del(key);  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
    }  
      
    /** 
     * flush 
     */  
    public void flushDB(){  
        Jedis jedis = jedisPool.getResource();  
        try{  
            jedis.flushDB();  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
    }  
      
    /** 
     * size 
     */  
    public Long dbSize(){  
        Long dbSize = 0L;  
        Jedis jedis = jedisPool.getResource();  
        try{  
            dbSize = jedis.dbSize();  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
        return dbSize;  
    }  
  
    /** 
     * keys 
     * @param regex 
     * @return 
     */  
    public Set keys(String pattern){  
        Set keys = null;  
        Jedis jedis = jedisPool.getResource();  
        try{  
            keys = jedis.keys(pattern.getBytes());  
        }finally{  
            jedisPool.returnResource(jedis);  
        }  
        return keys;  
    }  
      
    public String getHost() {  
        return host;  
    }  
  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    public int getPort() {  
        return port;  
    }  
  
    public void setPort(int port) {  
        this.port = port;  
    }  
  
    public int getExpire() {  
        return expire;  
    }  
  
    public void setExpire(int expire) {  
        this.expire = expire;  
    }  
  
    public int getTimeout() {  
        return timeout;  
    }  
  
    public void setTimeout(int timeout) {  
        this.timeout = timeout;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    
    public static void main(String[] args) {
		Jedis jedis = new Jedis("172.16.139.200");
		jedis.set("test", "hello word");
		System.out.println(jedis.get("test"));
	}
      
}
