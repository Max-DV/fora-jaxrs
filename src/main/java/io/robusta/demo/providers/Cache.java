package io.robusta.demo.providers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.CacheControl;

public class Cache {

	
	Map<Object, Object> cache = new HashMap<>();
	
	public Cache() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(Object key, Object data, CacheControl cc){
		
		if (cc.isMustRevalidate()){
			cache.put(key, data);
		}
		
	}
}
