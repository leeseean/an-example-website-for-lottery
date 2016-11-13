package com.mh.commons.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


/**
 * excel操作工具类
 * @author Victor.Chen
 *
 */
public class ExcelOptUtil {
	private static final Logger logger = LoggerFactory.getLogger(ExcelOptUtil.class);

	// 不可实例化该类
	private ExcelOptUtil() {}
	
	
	/**
	 * 获取book
	 * @param filePath
	 * @return
	 */
	public static HSSFWorkbook getWorkbook(String filePath) {
		HSSFWorkbook workbook = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath)) ;
			workbook = new HSSFWorkbook(fs);
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("read excel failed", e);
		}finally{
			if(fs != null){
				try{
					fs.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return workbook;
	}
	
	/**
	 * 获取sheet
	 * @param book
	 * @return
	 */
	public static List<HSSFSheet> getSheets(HSSFWorkbook workbook) {
		// 获取sheet数
		int sheetIdx = workbook.getNumberOfSheets();
		List<HSSFSheet> sheets = new ArrayList<HSSFSheet>();
		for (int s = 0; s < sheetIdx; s++) {
			HSSFSheet sheet = workbook.getSheetAt(s);
			sheets.add(sheet);
		}
		return sheets;
	}
	
	/**
	 * 获取row
	 * @param sheets
	 * @return
	 */
	public static List<List<HSSFCell>> getCells(HSSFSheet sheet) {
		List<List<HSSFCell>> rows = Lists.newArrayList();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow row = sheet.getRow(i);
			List<HSSFCell> cessList = Lists.newArrayList();
			
			for(int j = 1; j < row.getPhysicalNumberOfCells(); j++){
				HSSFCell cell = row.getCell(j);
				cessList.add(cell);
			}
			rows.add(cessList);
		}
		return rows;
	}
	
	/**
	 * 
	 * @param wb
	 * @param type 
	 * 			1:head 2 je  
	 * @param statu
	 * @return
	 */
	public HSSFCellStyle setCellStyleHead(HSSFWorkbook wb, int type, int statu) {
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 创建单元格样式   
		if (type != 2) {
			// 指定单元格居中对齐   
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐   
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		} else {
			// 指定单元格居中对齐   
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			// 指定单元格垂直居中对齐   
			cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_RIGHT);
		}

		//设置单元格边框和颜色   
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		HSSFFont font = wb.createFont();
		
		if (type == 1) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		
		if (statu == 2) {
			font.setColor(HSSFColor.RED.index);
		}
		font.setFontName("宋体");
		cellStyle.setFont(font);
		//字体大小   
		//font.setFontHeight((short) 200);   
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

		// 指定当单元格内容显示不下时自动换行   
		cellStyle.setWrapText(true);

		return cellStyle;
	}
}
