package com.centling.conference.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * filePath: Excel文件路径;  Entity: entity Class; columnHeads: entity 类名并以Excel导入文件表头为顺序
 * 返回包含转换成entity对象的ArrayList
 * @author jeremy
 *
 */
public class ExcelOperations {
	public ArrayList<Object> readExcel(String filePath, Class entity,
			String[] columnHeads) throws Exception {
		ArrayList<Method> list = new ArrayList<Method>();
		ArrayList<Object> objs = new ArrayList<Object>();

		try {
			for (int i = 0; i < columnHeads.length; i++) {
				char f = columnHeads[i].charAt(0);
				if (!Character.isUpperCase(f)) {
					columnHeads[i] = String.valueOf(Character.toUpperCase(f))
							+ columnHeads[i].substring(1);
				}
				Method methodGet = entity.getMethod("get" + columnHeads[i]);
				Method methodSet = entity.getMethod("set" + columnHeads[i], methodGet.getReturnType());
				list.add(methodSet);
			}
			InputStream inputstream = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(inputstream);
			Sheet sheet1 = wb.getSheetAt(0);
			for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
				try {
					Object obj = entity.newInstance();
					Cell cell = null;
					for (int k = 0; k < list.size(); k ++) {
						cell = sheet1.getRow(i).getCell(k);
						list.get(k).invoke(obj, cell.toString());
					}
					objs.add(obj);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Excel 文件第" + i + "行格式错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return objs;
	}
}
