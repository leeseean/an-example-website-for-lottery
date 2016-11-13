package com.mh.commons.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mh.commons.utils.CommonUtils;


@SuppressWarnings("all")
public class ExcelExportUtils<T> {
	private static final Logger logger = LoggerFactory.getLogger(ExcelExportUtils.class);
	/**  
	 *   
	 * @param title 标题  
	 * @param dataset 集合  
	 * @param out  输出流  
	 */
	@SuppressWarnings("unchecked")
	public HSSFWorkbook exportExcel(String title, List<T> dataset) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			//首先检查数据看是否是正确的   
			Iterator<T> its = dataset.iterator();
			if (dataset == null || !its.hasNext() || title == null) {
				throw new Exception("传入的数据不对！");   
			}

			T ts = (T) its.next();

			HSSFSheet sheet = workbook.createSheet(title);
			sheet.setDefaultColumnWidth(15);// 设置表格默认列宽度为15个字节 
			
			HSSFCellStyle headStyle = workbook.createCellStyle();
			headStyle = ExcelStyle.setHeadStyle(workbook, headStyle);
			
			HSSFCellStyle bodyStyle = workbook.createCellStyle();
			bodyStyle = ExcelStyle.setBodyStyle(workbook, bodyStyle);
			
			Field filed[] = ts.getClass().getDeclaredFields();
			List<Object[]> exportMetas = new ArrayList<Object[]>();
			
			// 遍历整个filed   
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
				// 如果设置了annottion   
				if (exa != null) {
					String exprot = exa.exportName();
					String pattern = exa.pattern();
					Integer order = Integer.valueOf(exa.order());
					
					// 添加到标题   
					exportMetas.add(new Object[]{f.getName(), exprot, pattern, order});
				}
			}
			
			//排序exportMetas
			Collections.sort(exportMetas, new Comparator<Object[]>(){

				/** 根据元注释order 排列顺序*/
				public int compare(Object[] o1, Object[] o2) {
					Integer order1 = (Integer)o1[3];
					Integer order2 = (Integer)o2[3];
					return order1.compareTo(order2);
				}
				
			});
			
			// 产生表格标题行   
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < exportMetas.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(headStyle);
				HSSFRichTextString text = new HSSFRichTextString((String)exportMetas.get(i)[1]);
				cell.setCellValue(text);
			}

			// 循环整个集合   
			for(int i=0; i<dataset.size(); i++){
				row = sheet.createRow(i + 1); //第一行为标题列, 从1开始写excel
				T t = (T) dataset.get(i);
				
				for (int k = 0; k < exportMetas.size(); k++) {
					HSSFCell cell = row.createCell(k);
					String fieldname = (String)exportMetas.get(k)[0];
					String getMethodName = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = getValue(value, exportMetas.get(k));
					HSSFRichTextString richString = new HSSFRichTextString(textValue);
					cell.setCellValue(richString);
					cell.setCellStyle(bodyStyle);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		return workbook;
	}

	public String getValue(Object value, Object[] meta) {
		String textValue = "";
		if (value == null)
			return textValue;

		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "是";
			if (!bValue) {
				textValue = "否";
			}
		} else if (value instanceof Date) {
			String pattern = (String)meta[2];
			if(StringUtils.isBlank(pattern))pattern = "yyyy-MM-dd";
			textValue = CommonUtils.parse2StandardDate((Date)value, pattern);
		} else{
			textValue = value.toString();
		}

		return textValue;
	}

 
}