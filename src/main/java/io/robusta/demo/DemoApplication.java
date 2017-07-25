package io.robusta.demo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("demo")
public class DemoApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserDemoResource.class);
		return s;
	}

}
