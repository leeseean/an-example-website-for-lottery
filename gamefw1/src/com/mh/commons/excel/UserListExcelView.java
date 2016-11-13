package com.mh.commons.excel;



import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.mh.commons.conf.CommonConstant;

/**
 * 创建excel视图
 * @author Victor.Chen
 *
 */
@SuppressWarnings("all")
public class UserListExcelView extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		response.setHeader("Content-Disposition", "inline; filename="+ System.currentTimeMillis() + ".xls");  
		
		HSSFSheet sheet = workbook.createSheet("reports");
		
		//List<String> headList = (List<String>) model.get(ExportExcelEnum.HEAD_LIST.getCode()); --wjs 未被调用 注释
		List<Map<String, Object>> dataLists = (List<Map<String, Object>>) model.get(CommonConstant.EXCEL_DATA_LIST);
		
		//创建标题头
//		for(int i = 0; i < headList.size(); i++){
//			HSSFRow header = sheet.createRow(0);
//			header.createCell(i).setCellValue(headList.get(i));
//		}
//		
		//创建内容体
		for (int i = 0; i < dataLists.size(); i++) {
			Map<String, Object> rowMap = dataLists.get(i);
			HSSFRow dataRow = sheet.createRow(i + 1);
			
			int j = 0;
			for(Iterator it = rowMap.entrySet().iterator(); it.hasNext(); ){  
				Entry<String, String> entry = (Entry<String, String>)it.next();  
				Object obj = entry.getValue();
				//String column = entry.getKey();
				String val = null;
				
				if(obj instanceof java.util.Date){
					//val = DateFormatUtils.format((Date)obj, "yyyy-MM-dd HH:mm:ss");
					dataRow.createCell(j++, Cell.CELL_TYPE_STRING).setCellValue((Date)obj);
				}
				else{
					val = obj == null ? "" : obj.toString();
					dataRow.createCell(j++, Cell.CELL_TYPE_STRING).setCellValue(val);
				}
			}

		}
	}

}
