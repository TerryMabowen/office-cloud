/**
 * 
 */
package cn.mbw.oc.web.interceptor.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Mabowen
 * @date 2019-12-26 09:20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Auth {
	public String name() default "";
}
