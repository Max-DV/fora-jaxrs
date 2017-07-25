package io.robusta.demo.providers;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding // Because we don't want automatic Filter, only those that are @Cacheable
@Retention(RetentionPolicy.RUNTIME) // If not, it will disappear at Runtime
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Cacheable {

	
	/**
	 * @return time in minutes
	 * 0 = no cache; -1 = for ever
	 * Used for Expires, not for Validation
	 * Absolutely Not recommended for Web Services
	 */
	int time() default 0;

	/**
	 * ETAG is a hash built from a content. It must be
	 * calculated fast
	 * ETAG is used with IF-NONE-MATCH
	 * @return ETAG
     */
	boolean etag() default false;

	/**
	 * Returns date of the last update
	 * Used with IF-MODIFIED-SINCE
	 * @return
     */
	boolean date() default false;
	
}
