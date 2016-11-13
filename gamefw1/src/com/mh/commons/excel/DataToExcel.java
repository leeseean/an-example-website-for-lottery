package com.mh.commons.excel;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mh.commons.utils.CommonUtils;

@SuppressWarnings("all")
public class DataToExcel {

	public String dataToExcel(List<Map<String, Object>> colsList,
			List<Map<String, Object>> rowsList, String filepath)
			throws IOException {

		ExcelWriter excelWriter = null;
		/** Excel文件存放路径* */
		if (!new File(filepath).exists()) {
			new File(filepath).mkdirs();
		}
		String fileName = UUID.randomUUID().toString() + ".xls";
		if (!filepath.endsWith(File.separator)) {
			filepath = filepath + File.separator;
		}
		File outfile = new File(filepath, fileName);
		outfile.createNewFile();

		excelWriter = new ExcelWriter(outfile);
		excelWriter.CreateSheet("export", 0);
		writeExcelHead(excelWriter, colsList, 0, 0);
		writeExcelRows(excelWriter, colsList, rowsList, 1, 0);
		excelWriter.close();
		return fileName;
	}

	/**
	 * Excel表头名称 只有一行
	 * 
	 * @param ew
	 *            ExcelWriter对象
	 * @param c
	 *            数据集
	 * @param row
	 *            行起始位置
	 * @param col
	 *            列起始位置 描述 ：数据集以map来封装 map中表头以titleName-value来存储
	 *            要隐藏字段以hide-true来存储
	 */
	private void writeExcelHead(ExcelWriter ew, List<Map<String, Object>> c,
			int row, int col) {
		if (c != null && c.size() > 0) {
			for (int i = 0; i < c.size(); i++) {
				Map m = (HashMap) c.get(i);
				if ((!CommonUtils.isNull(m.get("hide")))
						&& (!"false".equals(m.get("hide"))))
					continue;
				ew.addStringCell(row, col, (String) m.get("titleName"));
				col++;
			}
		}
	}

	/**
	 * 
	 * @param ew
	 *            ExcelWriter对象
	 * @param c
	 *            表头
	 * @param rows
	 *            数据集
	 * @param row
	 *            行起始位置
	 * @param col
	 *            列起始位置 描述 ：数据集以map来封装
	 * 
	 */
	private void writeExcelRows(ExcelWriter ew, List<Map<String, Object>> c,
			List<Map<String, Object>> rows, int row, int col) {
		if (c != null && c.size() > 0) {
			/** 获取数据集导出值的位置* */
			List temp = new ArrayList();
			for (int i = 0; i < c.size(); i++) {
				Map m = (HashMap) c.get(i);
				if ((!CommonUtils.isNull(m.get("hide")))
						&& (!"false".equals(m.get("hide"))))
					continue;
				temp.add(Integer.valueOf(i));
			}
			/** 数据集导出值 * */
			for (int i = 0; i < rows.size(); i++) {
				Map result = (HashMap) rows.get(i);
				for (int j = 0; j < temp.size(); j++) {
					int index = ((Integer) temp.get(j)).intValue();
					if (index + 1 > result.size()) {
						continue;
					}
					ew.addStringCell(row + i, col + j, CommonUtils
							.null2Str(result.get((index + 1) + "")));
				}
			}

		}
	}

	public void DownloadFiles(HttpServletRequest request,
			HttpServletResponse response, String fileName, String savePath)
			throws ServletException, IOException {

		// web绝对路径
//		String path = request.getSession().getServletContext().getRealPath("/");
//		savePath = path + "upload";

		// 设置为下载application/x-download
		response.setContentType("application/x-download");
		// 即将下载的文件在服务器上的绝对路径
		String filenamedownload = savePath + "/" + fileName;
		// 下载文件时显示的文件保存名称
		String filenamedisplay = fileName;
		// 中文编码转换
		filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filenamedisplay);
		java.io.OutputStream os = null;
		java.io.FileInputStream fis = null;
		try {
			os = response.getOutputStream();
			fis = new java.io.FileInputStream(
					filenamedownload);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fis != null)fis.close();
			if(os != null)os.close();
		}

	}
}
