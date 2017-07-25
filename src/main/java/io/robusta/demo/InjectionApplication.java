package io.robusta.demo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("inject")
public class InjectionApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(InjectionResource.class);		
		return s;
	}
}
