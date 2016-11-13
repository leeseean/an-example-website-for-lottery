/**   
 * 文件名称: ExcelDataUtils.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: zoro<br/>  
 * 创建时间 : 2015-9-26 上午3:22:57<br/>
 */
package com.mh.commons.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.mh.entity.WebGdElectronic;
import com.mh.entity.WebTtgElectronic;

/**
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2015-9-26 上午3:22:57<br/>
 */
@SuppressWarnings("all")
public class ExcelDataUtils2 {
	public static List<WebTtgElectronic> importExcel(String excelFile)
			throws Exception {
		FileInputStream in = new FileInputStream(excelFile);
		HSSFWorkbook book = new HSSFWorkbook(in);

		HSSFSheet sheet = book.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Row title = rows.next(); // 取得标题头行

		Iterator<Cell> cellTitle = title.cellIterator(); // 得到第一行的所有列
		Map<Integer, String> titlemap = new HashMap<Integer, String>();// 将标题的文字内容放入到一个map中。
		List<WebTtgElectronic> list = new ArrayList<WebTtgElectronic>();

		// 循环标题所有的列
		for (int i = 0; cellTitle.hasNext(); i++) {
			Cell cell = cellTitle.next();
			String value = cell.getStringCellValue();
			titlemap.put(i, value);
		}
		// 解析内容行
		for (int i = 0; rows.hasNext(); i++) {
			Row rown = rows.next();
			Iterator<Cell> cellbody = rown.cellIterator();// 行的所有列
			WebTtgElectronic entity = new WebTtgElectronic();
			// 遍历一行的列
			for (int j = 0; cellbody.hasNext(); j++) {
				Cell cell = cellbody.next();
				String tt = titlemap.get(j);
				if ("gameId".equals(tt)) {// ele_game_enname
					entity.setEleGameId(String.valueOf((int) cell
							.getNumericCellValue()));
				}
				if ("startgameName".equals(tt)) {// ele_game_type1
					entity.setEleGameEnname(cell.getStringCellValue());
				}
				if ("gameType".equals(tt)) {// ele_game_cnname
					entity.setEleGameType2(String.valueOf((int) cell
							.getNumericCellValue()));
				}
				if ("fenlei".equals(tt)) {// ele_game_pic
					entity.setEleGameType1(cell.getStringCellValue());
				}
				if ("cname".equals(tt)) {// ele_game_id
					entity.setEleGameCnname(cell.getStringCellValue());
				}
				entity.setStatus("1");
			}
			list.add(entity);
		}
		return list;
	}

	public static List<WebGdElectronic> importExcels(String excelFile)
			throws Exception {
		FileInputStream in = new FileInputStream(excelFile);
		HSSFWorkbook book = new HSSFWorkbook(in);

		HSSFSheet sheet = book.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Row title = rows.next(); // 取得标题头行

		Iterator<Cell> cellTitle = title.cellIterator(); // 得到第一行的所有列
		Map<Integer, String> titlemap = new HashMap<Integer, String>();// 将标题的文字内容放入到一个map中。
		List<WebGdElectronic> list = new ArrayList<WebGdElectronic>();

		// 循环标题所有的列
		for (int i = 0; cellTitle.hasNext(); i++) {
			Cell cell = cellTitle.next();
			String value = cell.getStringCellValue();
			titlemap.put(i, value);
		}
		// 解析内容行
		for (int i = 0; rows.hasNext(); i++) {
			Row rown = rows.next();
			Iterator<Cell> cellbody = rown.cellIterator();// 行的所有列
			WebGdElectronic entity = new WebGdElectronic();
			// 遍历一行的列
			for (int j = 0; cellbody.hasNext(); j++) {
				Cell cell = cellbody.next();
				String tt = titlemap.get(j);
				if ("gamecode".equals(tt)) {// ele_game_enname
					// entity.setEleGameId(String.valueOf((int)cell.getNumericCellValue()));
					String value = cell.getStringCellValue();
					entity.setEleGameId(value);
					entity.setEleGamePic(value.substring(3, value.length()));
				}
				if ("ename".equals(tt)) {// ele_game_type1
					cell.setCellType(1);
					entity.setEleGameEnname(cell.getStringCellValue());
				}
				if ("cname".equals(tt)) {
					cell.setCellType(1);
					entity.setEleGameCnname(cell.getStringCellValue());
				}
				// if("gameType".equals(tt)){//ele_game_cnname
				// entity.setEleGameType2(String.valueOf((int)cell.getNumericCellValue()));
				// }
				// if("fenlei".equals(tt)){//ele_game_pic
				// entity.setEleGameType1(cell.getStringCellValue());
				// }
				// if("cname".equals(tt)){//ele_game_id
				// entity.setEleGameCnname(cell.getStringCellValue());
				// }
				entity.setStatus("1");
			}
			list.add(entity);
		}
		return list;
	}

	public static void getPic() {
		String path = "F:/pics/";
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			String name = tempList[i].getName();
			if(name.indexOf("_icon.jpg") != -1){
				copyFile("F:/pics/" + name, "F:/picss/"+ name);
				for (File file2 : tempList) {
					if(file2.getName().indexOf(name.split("_")[0]) != -1){
						file2.delete();
					}
				}
				tempList[i].delete();
			}
		}
	}

	public static void copyFile(String src, String des) {
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(des);
			byte[] bt = new byte[1024];
			int readNum = 0;
			while ((readNum = fis.read(bt)) != -1) {
				fos.write(bt, 0, bt.length);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			
		} finally {
		}
	}

	public static void main(String[] args) throws Exception {
		// String filePath = "D:/excel/excel.xls";
		// importExcel(filePath);
		getPic();
		//copyFile("F:/pic/1376_icon.png", "F:/pics/1376_icon.png");
	}

}
