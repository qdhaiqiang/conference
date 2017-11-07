package com.centling.conference.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * simple introduction
 * 
 * <p>
 * detailed comment
 * 
 * @author lisa.yang 2013-10-27
 * @see
 * @since 1.0
 */
public class JSONUtil {

	public static <T> T json2Bean(String jsonStr, Class<T> object) {
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			if (jsonObject instanceof JSONObject) {
				T returnValue = object.newInstance();
				Iterator<String> iterator = jsonObject.keys();
				while (iterator.hasNext()) {
					String keyname = iterator.next();
					Object object01 = (Object) jsonObject.get(keyname);
					if (object01 != null) {
						setFieldValue(returnValue, keyname, object01.toString());
					}
					else {
						setFieldValue(returnValue, keyname, "");
					}
				}
				return returnValue;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			
		}
		return null;
	}

	private static void setFieldValue(Object returnValue, String propertyName, String propertyValue) throws Exception {
		Field[] f = returnValue.getClass().getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			if (f[i].getName().equals(propertyName)) {
				Field field = returnValue.getClass().getDeclaredField(propertyName);
				field.setAccessible(true);
				Class<?> type = field.getType();
				if (type.equals(int.class)) {
					field.setInt(returnValue, Integer.valueOf(propertyValue));
				}else if (type.equals(Integer.class)) {
					field.set(returnValue, Integer.valueOf(propertyValue));
				}
				else if (type.equals(float.class)) {
					field.setFloat(returnValue, Float.valueOf(propertyValue));
				}
				else if (type.equals(boolean.class)) {
					field.setBoolean(returnValue, Boolean.valueOf(propertyValue));
				}
				else {
					field.set(returnValue, propertyValue);
				}
			}
		}

	}


	public static <T> List<T> getJsonListVo(JSONArray jsonarray, Class<T> object) {
		List<T> returnList = new ArrayList<T>();
		try {
			for (int i = 0; i < jsonarray.length(); i++) {
				T t = (T) object.newInstance();
				t = json2Bean(jsonarray.get(i).toString(), object);
				returnList.add(t);
			}
		} catch (Exception e) {
		}
		return returnList;

	}


}
