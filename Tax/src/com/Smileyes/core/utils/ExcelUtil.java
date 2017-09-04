package com.Smileyes.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.Smileyes.nsfw.user.entity.User;

/*
 * 用于处理Excel的导入与导出
 * 
 * @author Smileyes
 *
 */
public class ExcelUtil {
	// 设置工作簿内容
	public static void exportExcel(HSSFWorkbook workbook, List<User> list) {
		// 1.设置标题栏项目
		String[] title = { "序号", "姓名", "帐号", "密码", "部门", "性别", "状态", "生日", "邮箱", "手机号码",
				"描述" };
		// 2.创建工作表
		HSSFSheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(12);
		// 3.创建样式
		HSSFCellStyle style1 = createStyle(workbook, 20, true);
		HSSFCellStyle style2 = createStyle(workbook, 16, true);
		HSSFCellStyle style3 = createStyle(workbook, 10, false);

		// 4.第一行的列表头
		CellRangeAddress ra = new CellRangeAddress(0, 0, 0, title.length);
		sheet.addMergedRegion(ra);
		HSSFRow row1 = sheet.createRow(ra.getFirstRow());
		HSSFCell cell1 = row1.createCell(ra.getFirstColumn());
		cell1.setCellValue("用户列表");
		cell1.setCellStyle(style1);
		// 5.第二行的列表项目栏
		HSSFRow row2 = sheet.createRow(1);
		for (int i = 0; i < title.length; i++) {
			HSSFCell cell2 = row2.createCell(i);
			cell2.setCellValue(title[i]);
			cell2.setCellStyle(style2);
		}
		// 6.第三行及以后的列表项目
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = null;
		for (int i = 0; i < list.size(); i++) {
			user = list.get(i);
			HSSFRow row3 = sheet.createRow(i + 2);
			HSSFCell[] cell3 = new HSSFCell[title.length];
			for (int j = 0; j < title.length; j++) {
				cell3[j] = row3.createCell(j);
				cell3[j].setCellStyle(style3);
			}
			// 序号
			cell3[0].setCellValue(i + 1);
			// 姓名
			cell3[1].setCellValue(user.getName());
			// 账号
			cell3[2].setCellValue(user.getAccount());
			// 密码
			cell3[3].setCellValue(user.getPassword());
			// 部门
			cell3[4].setCellValue(user.getDept());
			// 性别
			cell3[5].setCellValue(user.isGender() ? "男" : "女");
			// 状态
			cell3[6].setCellValue(
					user.USER_STATE_VALID.equals(user.getState()) ? "有效" : "无效");
			// 生日
			cell3[7].setCellValue(sdf.format(user.getBirthday()));
			// 邮箱
			cell3[8].setCellValue(user.getEmail());
			// 手机号码
			cell3[9].setCellValue(user.getMobile());
			// 描述
			cell3[10].setCellValue(user.getMemo());
		}
	}

	// 设置样式
	public static HSSFCellStyle createStyle(HSSFWorkbook workbook, int fontSize,
			boolean isBold) {
		// 1.创建样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 1.字体： 创建字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) fontSize);
		// 设置字体加粗
		font.setBold(isBold);
		// 添加字体到样式
		style.setFont(font);
		// 2.样式：设置居中
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}

	public static List<User> importExcel(Workbook workbook) {
		List<User> list = new ArrayList<User>();
		// 1.获取工作表
		Sheet sheet = workbook.getSheetAt(0);
		// 2.获取有效行数
		int rowsNum = sheet.getPhysicalNumberOfRows();
		// 3.判断行数
		if (rowsNum > 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 4.开始读取数据
			for (int i = 2; i < rowsNum; i++) {
				Row row = sheet.getRow(i);
				Cell[] cell = new Cell[11];
				for (int j = 0; j < 11; j++) {
					cell[j] = row.getCell(j);
				}
				User user = new User();
				// 姓名
				user.setName(cell[1].getStringCellValue());
				// 账号
				user.setAccount(cell[2].getStringCellValue());
				// 密码
				String pwd = cell[3].getStringCellValue();
				// 密码项为空，或者为空格
				if (pwd == null | "".equals(pwd.trim())) {
					pwd = "123456";
				}
				user.setPassword(pwd);
				// 部门
				user.setDept(cell[4].getStringCellValue());
				// 性别
				user.setGender("男".equals(cell[5].getStringCellValue()));
				// 状态
				user.setState("有效".equals(cell[6].getStringCellValue())
						? User.USER_STATE_VALID
						: User.USER_STATE_INVALID);
				// 生日
				Date date = null;
				try {
					date = sdf.parse(cell[7].getStringCellValue());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				user.setBirthday(date);
				// 邮箱
				user.setEmail(cell[8].getStringCellValue());
				// 手机号码
				try {
					user.setMobile(cell[9].getStringCellValue());
				} catch (Exception e) {
					user.setMobile("" + cell[9].getNumericCellValue());
				}
				// 描述
				user.setMemo(cell[10].getStringCellValue());
				// 添加用户到集合
				list.add(user);
			}
			return list;
		} else {
			return null;
		}

	}
}
