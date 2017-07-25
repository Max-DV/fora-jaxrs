package io.robusta.demo.cache;

import io.robusta.demo.providers.CacheableFilter;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("cache")
public class CacheApplication extends Application{

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(CacheResource.class);
		//s.add(CacheableProvider.class);
		s.add(CacheableFilter.class);

		return s;
	}
}
