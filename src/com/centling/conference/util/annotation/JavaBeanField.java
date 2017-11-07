package com.centling.conference.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要转换的对象字段注解配置类
 * @author yzChen
 * @date 2013-8-16 下午3:48:02
 * <pre>
 *	desc:
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JavaBeanField {

	// 数据字段名称
	// 为了兼容区分大小写，所以一致改为小写
	String value();
	
	// 字段默认值
	// 为了兼容区分大小写，所以一致改为小写
	String defaultVal() default JavaBeanConfig.JAVABEAN_COLUMN_DEFAULT_VAL;
	
	// update时是否使用该字段（一般配置与基础类）
	boolean upadtebase() default JavaBeanConfig.JAVABEAN_COLUMN_UPDATEBASE_DEFAULT_VAL;
}
