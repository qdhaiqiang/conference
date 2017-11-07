package com.centling.conference.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否为主键的注解配置类
 * @author yzChen
 * @date 2013-8-16 下午3:48:02
 * <pre>
 *	desc:
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JavaBeanPrimaryKey {

	// 是否为主键
	boolean value() default JavaBeanConfig.JAVABEAN_COLUMN_PRIMARYKEY_DEFAULT_VAL;
	
}
