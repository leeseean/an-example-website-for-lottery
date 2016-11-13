package com.mh.commons.excel;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mh.commons.utils.CommonUtils;

/**
 * 导入EXCEL导入工具, 产出指定pojo 列表
 * @author Victor.Chen
 *
 * @param <T>
 */
@SuppressWarnings("all")
public class ExcekImportUtils<T> {
	private static final Logger logger = LoggerFactory.getLogger(ExcekImportUtils.class);
	

	public ExcekImportUtils() {
	}

	/**
	 * 解析excel文档
	 * @param file
	 * @return
	 */
	public List<T> importExcel(File file, Class<T> clazz) {
		List<T> dist = new ArrayList<T>();
		
		try {
			Field filed[] = clazz.getDeclaredFields();
			
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中   
			Map<String, Object[]> fieldmap = new HashMap<String, Object[]>();
			
			// 循环读取所有字段   
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				ExcelAnnotation exa=f.getAnnotation(ExcelAnnotation.class);
				if (exa != null) {
					
					// 构造设置了Annotation的字段的Setter方法   
					String fieldname =f.getName();
					String setMethodName = "set"
							+ fieldname.substring(0, 1).toUpperCase()
							+ fieldname.substring(1);
					
					// 构造调用的method，   
					Method setMethod = clazz.getMethod(setMethodName, new Class[] { f.getType() });
					String pattern = exa.pattern();
					
					// 将这个method以Annotaion的名字为key来存入。   
					fieldmap.put(exa.exportName(), new Object[]{ setMethod, pattern });
				}
			}

			FileInputStream in = new FileInputStream(file);
			HSSFWorkbook book = new HSSFWorkbook(in);
			
			HSSFSheet sheet = book.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			Row title = rows.next(); //取得标题头行
			
			Iterator<Cell> cellTitle = title.cellIterator(); // 得到第一行的所有列  
			Map<Integer, String> titlemap = new HashMap<Integer, String>();// 将标题的文字内容放入到一个map中。   
			
			
			// 循环标题所有的列   
			for (int i=0; cellTitle.hasNext(); i++) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				titlemap.put(i, value);
			}

			//解析内容行 
			for (int i=0; rows.hasNext(); i++) {
				Row rown = rows.next();
				
				Iterator<Cell> cellbody = rown.cellIterator();// 行的所有列   
				T tObject = clazz.newInstance(); // 行的所有列   
				
				// 遍历一行的列   
				for (int j=0; cellbody.hasNext(); j++) {
					Cell cell = cellbody.next();
					   
					String titleString = (String) titlemap.get(j);// 这里得到此列的对应的标题
					
					// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值   
					if (fieldmap.containsKey(titleString)) {
						Method setMethod = (Method) fieldmap.get(titleString)[0];
						   
						Type[] ts = setMethod.getGenericParameterTypes();//得到setter方法的参数
						String xclass = ts[0].toString(); //只要一个参数   
						
						//判断参数类型   
						if (xclass.equals("class java.lang.String")) {
							setMethod.invoke(tObject, this.getCellValue(cell));
						}
						
						else if (xclass.equals("class java.util.Date")) {
							String pattern = (String) fieldmap.get(titleString)[1];
							if(StringUtils.isBlank(pattern))pattern = "yyyy-MM-dd";
							
							setMethod.invoke(tObject, CommonUtils.parseString2Date(this.getCellValue(cell), pattern));
						} 
						
						else if (xclass.equals("class java.lang.Boolean")) {
							Boolean boolname = true;
							if (this.getCellValue(cell).equals("否")) {
								boolname = false;
							}
							setMethod.invoke(tObject, boolname);
						}
						
						else if (xclass.equals("class java.lang.Integer")) {
							setMethod.invoke(tObject, Integer.parseInt(this.getCellValue(cell)));
						}

						else if (xclass.equals("class java.lang.Long")) {
							setMethod.invoke(tObject, new Long(this.getCellValue(cell)));
						}
					}
				}
				dist.add(tObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		}

		return dist;
	}
	
	
    public String getCellValue(Cell cell) {                              
        return cell.getStringCellValue();
    }

 
}