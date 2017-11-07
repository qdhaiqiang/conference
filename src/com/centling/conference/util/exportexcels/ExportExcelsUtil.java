package com.centling.conference.util.exportexcels;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportExcelsUtil {
	/**
	 * 
	 * @param dataset 数据
	 * @param out 流
	 */
	public static <T> void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel(0,0,0,"sheet1", null, dataset, out, "yyyy-MM-dd");
    }

	/**
	 * 
	 * @param headers 标题
	 * @param dataset 数据
	 * @param out
	 */
    public static <T> void exportExcel(String[] headers, Collection<T> dataset,OutputStream out) {
        exportExcel(0,0,0,"sheet1", headers, dataset, out, "yyyy-MM-dd");
    }

    /**
     * 
     * @param headers 标题
     * @param dataset 数据
     * @param out 
     * @param pattern 日期格式
     */
    public static <T> void exportExcel(String[] headers, Collection<T> dataset,OutputStream out, String pattern) {
        exportExcel(0,0,0,"sheet1", headers, dataset, out, pattern);
    }
    
    /**
     * 导出数据
     * @param colSplit 需要冻结的列数目，如果没有传0
     * @param rowSplit 需要冻结的行数目，如果没有传0
     * @param dataBeginIndex 数据从第几个字段开始导出，每一条的记录，如果没有传0
     * @param sheetName 表格名字，sheet1的名称
     * @param headers 标题
     * @param dataset 数据
     * @param out 输出流
     */
    public static <T> void exportExcel(int colSplit,int rowSplit,int dataBeginIndex,String sheetName,String[] headers, Collection<T> dataset,OutputStream out){
    	exportExcel(colSplit,rowSplit,dataBeginIndex,sheetName, headers, dataset, out, null);
    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     * @param <T>
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            1.需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     *            2.或者Map,key是string类型，value属性
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"，目前不需要
     */
    @SuppressWarnings("unchecked")
    public static <T> void exportExcel(int colSplit,int rowSplit,int dataBeginIndex,String title, String[] headers,Collection<T> dataset, OutputStream out, String pattern) {
    	// 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = (title == null || title.equals("")) ? "sheet1" : title;
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetName);
    	try{
    		if(colSplit != 0 || rowSplit != 0){
    			sheet.createFreezePane( colSplit, rowSplit, colSplit, rowSplit );//冻结首行(0,1,0,1)；2,1
    		}
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            HSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.VIOLET.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            style.setWrapText(true);//设置自动换行
            
            // 生成并设置另一个样式
            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            HSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);
            style2.setWrapText(true);//设置自动换行

            // 声明一个画图的顶级管理器
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            // 定义注释的大小和位置,详见文档  
            //HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
            // 设置注释内容  
            //comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
            // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容. 
            //comment.setAuthor("leno");

            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }

            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                if(t instanceof Class){
                	// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                    Field[] fields = t.getClass().getDeclaredFields();
        			for (short i = 0; i < fields.length; i++) {
        				HSSFCell cell = row.createCell(i);
        				cell.setCellStyle(style2);
        				Field field = fields[i];
        				String fieldName = field.getName();
        				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        				Class tCls = t.getClass();
        				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
        				Object value = getMethod.invoke(t, new Object[] {});
        				// 判断值的类型后进行强制类型转换
        				String textValue = null;
        				if (value instanceof byte[]) {
        					// 有图片时，设置行高为60px;
        					row.setHeightInPoints(600);
        					// 设置图片所在列宽度为80px,注意这里单位的一个换算
        					sheet.setColumnWidth(i, (short) (35.7 * 400));
        					// sheet.autoSizeColumn(i);
        					byte[] bsValue = (byte[]) value;
        					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 600, i, index, (short) (i+1), index+1);
        					anchor.setAnchorType(2);
        					HSSFPicture pic = patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
        					pic.resize();//这句话一定不要，这是用图片原始大小来显示
        				} else {
        					// 其它数据类型都当作字符串简单处理
        					textValue = value.toString();
        				}
        				
        				if (textValue != null){
        					HSSFRichTextString richString = new HSSFRichTextString(textValue);
             				HSSFFont font3 = workbook.createFont();
             				font3.setColor(HSSFColor.BLUE.index);
             				richString.applyFont(font3);
             				cell.setCellValue(richString);
        				}
        			}
                }else if(t instanceof ListOrderedMap || t instanceof HashMap){
                	//HashMap<String,String> map = null;
                	ListOrderedMap map = (ListOrderedMap) t;
                	Iterator<String> keys = map.keySet().iterator();
                	short i = 0;
                	while(keys.hasNext()){
                		HSSFCell cell = row.createCell(i);
        				cell.setCellStyle(style2);
                		String keyname = keys.next();
                		//此处根据keyname生成标题
                		Object keyValue = map.get(keyname);
                		String textValue = null;
                		if(keyValue instanceof byte[]){
                			// 有图片时，设置行高为60px;
        					row.setHeightInPoints(300);
        					// 设置图片所在列宽度为80px,注意这里单位的一个换算
        					sheet.setColumnWidth(i, (short) (35.7 * 200));
        					// sheet.autoSizeColumn(i);
        					byte[] bsValue = (byte[]) keyValue;
        					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, i, index, (short) (i+1), index+1);
        					anchor.setAnchorType(2);
        					HSSFPicture pic = patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
        					//pic.resize();//这句话一定不要，这是用图片原始大小来显示
                		}else{
                			textValue = keyValue.toString();
                		}
                		if (textValue != null){
       					 	HSSFRichTextString richString = new HSSFRichTextString(textValue);
            				HSSFFont font3 = workbook.createFont();
            				font3.setColor(HSSFColor.BLUE.index);
            				richString.applyFont(font3);
            				cell.setCellValue(richString);
       				 	}
                		i++;
                	}//end while(keys.hasNext())
                }else if(t instanceof Object[]){
                	Object[] object = (Object[])t;
                	//忽略前两个id字段，之后可以作为参数传入
                	for(int len = dataBeginIndex;len<object.length;len++){                        
                		String textValue = "";
                		if(object[len] != null){
                			textValue = object[len].toString();
                		}
                		HSSFCell cell = row.createCell(len-dataBeginIndex);
        				cell.setCellStyle(style2);
                		HSSFRichTextString richString = new HSSFRichTextString(textValue);
        				HSSFFont font3 = workbook.createFont();
        				font3.setColor(HSSFColor.BLUE.index);
        				richString.applyFont(font3);
        				cell.setCellValue(richString);
                	}
                }
                
            }
            workbook.write(out);
            out.flush();
            out.close();
        }catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 清理资源
        	
        }

    }

}
