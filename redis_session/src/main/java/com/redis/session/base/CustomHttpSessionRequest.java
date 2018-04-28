package com.redis.session.base;

import java.io.Serializable;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;

import com.redis.session.redis.RedisManager;
import com.redis.session.util.SerializeUtils;

public class CustomHttpSessionRequest extends HttpServletRequestWrapper{
	

	private String keyPrefix = "shiro_redis_session:";
	
	private RedisManager redisManager = new RedisManager();
	
	private ServletContext servletContext=null;
	
	private HttpSession session;
	
	public CustomHttpSessionRequest(HttpServletRequest request,HttpSession session) {
		super(request);
		if (null != session) {
            this.session = session;
        }
	}
	
	@Override
    public HttpSession getSession() {
        return getSession(true);
    }
	
	@Override
	public HttpSession getSession(boolean create) {
		if(create && session == null){
			SessionKey sessionKey = new SessionKey() {
				
				@Override
				public Serializable getSessionId() {
					String sessionId = UUID.randomUUID().toString();
					return sessionId;
				}
			};
			SimpleSession simpleSession = new SimpleSession();
			simpleSession.setHost(getRequest().getRemoteHost());
			simpleSession.setId(sessionKey.getSessionId());
//			session = new ShiroHttpSession(simpleSession, this,servletContext);
			session = new CustomSession(simpleSession, this);
			cacheSession();
		}
		return session;
	}
	
	private void cacheSession(){
		if(session==null){
			return;
		}
		String keyPrefix = "shiro_redis_session:";
		String preKey = keyPrefix + session.getId();
		byte[] key = preKey.getBytes();
		byte[] value = SerializeUtils.serialize(session);
		redisManager.set(key, value);
	}
	


	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(String sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}
}
