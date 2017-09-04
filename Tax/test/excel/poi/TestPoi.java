package excel.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontCharset;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPoi {

	public void poi2003() {
		try {
			// 1. 创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 2.创建工作表
			HSSFSheet sheet = workbook.createSheet("Miku");
			// 3.创建行
			HSSFRow row = sheet.createRow(2);
			// 4.创建单元格
			HSSFCell cell = row.createCell(2);
			// 5.设置内容
			cell.setCellValue("Hello World!");
			// 6.新建输出
			FileOutputStream fos = new FileOutputStream("abc.xls");
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 输出Excel文件

	public void poiWrite() {
		try {
			String path = "ttt.xlsx";
			if (path.matches("^.+\\.(?i)(xls|xlsx)$")) {
				FileOutputStream fos = new FileOutputStream(path);
				// 1. 创建工作簿
				boolean is03version = true;
				// 判断文件格式
				if (path.matches("^.+\\.(?i)(xlsx)$")) {
					is03version = false;
				}
				Workbook workbook = is03version ? new HSSFWorkbook()
						: new XSSFWorkbook();
				// 1.2创建合并单元格
				CellRangeAddress rangeAddress = new CellRangeAddress(2, 4, 2, 4);
				// 1.3创建样式
				CellStyle style = workbook.createCellStyle();
				style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
				style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 设置填充模式
				// style.setFillBackgroundColor(HSSFColorPredefined.YELLOW.getIndex());//
				// 设置背景色
				style.setFillForegroundColor(HSSFColorPredefined.RED.getIndex());// 设置前景色
				// 1.4创建字体
				Font font = workbook.createFont();
				font.setBold(true);// 加粗
				font.setColor(HSSFColorPredefined.WHITE.getIndex());// 颜色
				font.setFontHeightInPoints((short) 16); // 字号为16
				font.setFontName("黑体");// 字体类型
				style.setFont(font);// 添加字体到样式
				// 2.创建工作表
				Sheet sheet = workbook.createSheet("Miku");
				sheet.addMergedRegion(rangeAddress);
				// 3.创建行
				Row row = sheet.createRow(rangeAddress.getFirstRow());
				sheet.setDefaultColumnWidth(15);//设置默认单元格宽度
				// 4.创建单元格
				Cell cell = row.createCell(rangeAddress.getFirstColumn());
				// 5.设置内容
				cell.setCellValue("223344");
				// 5.2设置样式
				cell.setCellStyle(style);
				// 6.输出
				workbook.write(fos);
				workbook.close();
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 输入Excel文件
	@Test
	public void poiRead() {
		try {
			String path = "ttt.xlsx";
			if (path.matches("^.+\\.(?i)(xls|xlsx)$")) {
				FileInputStream fis = new FileInputStream(path);
				// 1.获取工作簿
				boolean is03version = true;
				// 判断文件格式
				if (path.matches("^.+\\.(?i)(xlsx)$")) {
					is03version = false;
				}
				Workbook workbook = is03version ? new HSSFWorkbook(fis)
						: new XSSFWorkbook(fis);
				// 2.获取工作表
				Sheet sheet = workbook.getSheetAt(0);
				// 3.获取行
				Row row = sheet.getRow(sheet.getFirstRowNum());
				// 4.获取单元格
				Cell cell = row.getCell(row.getFirstCellNum());
				// 5.获取内容
				System.out.println(cell.getStringCellValue());
				workbook.close();
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
