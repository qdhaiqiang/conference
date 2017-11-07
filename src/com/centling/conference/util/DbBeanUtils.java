package com.centling.conference.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.centling.conference.util.annotation.JavaBeanField;

/**
 * 数据库获取到的List<Map>结果集转化为List<JavaBean>工具类
 * @author yzChen
 * @date 2013-8-16 下午3:50:35
 * <pre>
 *	desc:
 * </pre>
 */
public class DbBeanUtils {
	
	/**
	 * 根据List<Map<String, Object>>数据转换为List<JavaBean>数据
	 * @param datas
	 * @param beanClass
	 * @return
	 * @throws CommonException
	 */
	public static <T> List<T> ListMap2JavaBean(List<Map<String, Object>> datas, Class<T> beanClass) {
		// 返回数据集合
		List<T> list = null;
		// 对象字段名称
		String fieldname = "";
		// 对象方法名称
		String methodname = "";
		// 对象方法需要赋的值
		Object methodsetvalue = "";
		try {
			list = new ArrayList<T>();
			// 得到对象所有字段
			Field fields[] = beanClass.getDeclaredFields();
			// 遍历数据
			for (int i=0; i<datas.size(); i++) {
				Map<String, Object> mapData = datas.get(i);
				// 创建一个泛型类型实例
				T t = beanClass.newInstance();
				// 遍历所有字段，对应配置好的字段并赋值
				for (Field field : fields) {
					// 获取注解配置
					JavaBeanField javaBeanField = field.getAnnotation(JavaBeanField.class);
					if(null != javaBeanField) {	// 有注解配置，下一步操作
						// 全部转化为大写
						String dbfieldname = javaBeanField.value().toUpperCase();
						// 获取字段名称
						fieldname = field.getName();
						// 拼接set方法
						methodname = "set" + StringUtils.capitalize(fieldname);
						// 获取data里的对应值
						methodsetvalue = mapData.get(dbfieldname);
						// 赋值给字段
						Method m = beanClass.getDeclaredMethod(methodname, field.getType());
						m.invoke(t, methodsetvalue);
					}
				}
				// 存入返回列表
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return list;
	}
}