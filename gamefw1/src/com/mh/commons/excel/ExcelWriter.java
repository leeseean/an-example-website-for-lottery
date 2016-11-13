package com.mh.commons.excel;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.math.NumberUtils;

import com.mh.commons.utils.CommonUtils;

@SuppressWarnings("all")
public class ExcelWriter {
	
	private File excelFile = null;
	private WritableWorkbook workbook = null;
	private WritableSheet defSheet = null;

	private WritableCellFormat contentFromart = new WritableCellFormat(
			NumberFormats.TEXT);

	public ExcelWriter(File excelFile) {
		try {
			this.excelFile = excelFile;
			this.workbook = Workbook.createWorkbook(excelFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sheet CreateSheet(String name, int idx) {
		this.defSheet = this.workbook.createSheet(name, idx);
		return this.defSheet;
	}

	public void addStringCell(int row, int col, String text) {
		Label label = new Label(col, row, text, this.contentFromart);
		try {
			this.defSheet.addCell(label);

			int width = NumberUtils.max(new int[] {
					this.defSheet.getColumnWidth(col), measurePixWidth(text) });
			this.defSheet.setColumnView(col, width + 1);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public void addNumberCell(int row, int col, double n) {
		Number number = new Number(col, row, n);
		try {
			this.defSheet.addCell(number);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.workbook.write();
			this.workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int measurePixWidth(String showStr) {
		int ret = 0;
		if (showStr == null) {
			return ret;
		}
		char[] ch = showStr.toCharArray();

		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (CommonUtils.isChinese(c))
				ret += 2;
			else {
				ret++;
			}
		}
		return ret;
	}
}