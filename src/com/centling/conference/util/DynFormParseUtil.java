package com.centling.conference.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.centling.conference.dyn.field.entity.ConfDynField;
import com.centling.conference.dyn.value.entity.ConfDynValue;

/**
 * 解析动态表单JSON字符串工具类
 * @author Dirk
 *
 */
public class DynFormParseUtil {
	/**
	 * 解析动态表单
	 * @param payload 待解析的json字符串
	 * @return 解析后的字段集合列表
	 */
	public static List<ConfDynField> parse(String payload) {
		if (StringUtils.isEmpty(payload)) {
			return null;
		}
		List<ConfDynField> confDynFieldList = new ArrayList<ConfDynField>();
		// 获取数组
		JSONArray jsonArray = JSONObject.fromObject(payload).getJSONArray("fields");
		for (int i=0; i<jsonArray.size(); i++) {
			ConfDynField confDynField = new ConfDynField();
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			// 设置标题
			confDynField.setName(StringUtils.isNotEmpty(jsonObject.getString("label"))?jsonObject.getString("label"):"");
			// 设置类型
			confDynField.setType(StringUtils.isNotEmpty(jsonObject.getString("field_type"))?jsonObject.getString("field_type"):"");
			// 设置是否必填
			confDynField.setRequired(StringUtils.isNotEmpty(jsonObject.getString("required"))?jsonObject.getString("required"):"");
			// 设置CID
			confDynField.setCid(StringUtils.isNotEmpty(jsonObject.getString("cid"))?jsonObject.getString("cid"):"");
			// 获取field_options
			if (jsonObject.containsKey("field_options")) {
				JSONObject optJsonObj = jsonObject.getJSONObject("field_options");
				if (optJsonObj.containsKey("description")) {
					confDynField.setDescription(StringUtils.isNotEmpty(optJsonObj.getString("description"))?optJsonObj.getString("description"):"");
				} else {
					confDynField.setDescription(StringUtils.EMPTY);
				}
				if (optJsonObj.containsKey("options")) {
					confDynField.setOptions(StringUtils.isNotEmpty(optJsonObj.getString("options"))?optJsonObj.getString("options"):"");
				} else {
					confDynField.setOptions(StringUtils.EMPTY);
				}
			}
			confDynFieldList.add(confDynField);
		}
		return confDynFieldList;
	}
	
	public static List<ConfDynValue> parseValue(String payload) {
		if (StringUtils.isEmpty(payload)) {
			return null;
		}
		// 获取数组
		org.json.JSONArray jsonArray =  new org.json.JSONArray(payload);
		if(jsonArray.length()>0){
			return JSONUtil.getJsonListVo(jsonArray, ConfDynValue.class);
		}
		return null;
	//	return JSONArray.toList(jsonArray, ConfDynValue.class);
	}
	
	public static String parsePayload(String payload, Map<String, String> numberMap) {
		JSONObject jsonObject = JSONObject.fromObject(payload);
		JSONArray jsonArray = jsonObject.getJSONArray("fields");
		List<String> li = new ArrayList<String>();
		li.addAll(jsonArray);
		for (int i = 0; i < jsonArray.size(); i++) {
			li.set(Integer.parseInt(numberMap.get(jsonArray.getJSONObject(i).get("cid")))-1,jsonArray.get(i).toString());
		}
		
		return "{\"fields\":["+StringUtils.join(li, ",")+"]}";
	}
}