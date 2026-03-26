package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils01 {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;
	
	public ExcelUtils01(String path) {
		this.path=path;
	}
	
	public int getRowCount(String xlsheet) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rownum=ws.getLastRowNum();
		System.out.println(rownum);
		wb.close();
		fi.close();
		return rownum;
	}
	
	public  int getCellCount(String xlsheet,int rownum) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellnum=row.getLastCellNum();
		System.out.println(cellnum);
		wb.close();
		fi.close();
		return cellnum;
	}
	
	public String getCellData(String xlsheet,int rownum, int celnum) throws IOException{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(celnum);
		System.out.println(cell.toString());
		String data;
		try {
			data=cell.toString();//direct we can use
//			DataFormatter formatter=new DataFormatter();//another way to convert data to string format
//			data=formatter.formatCellValue(cell);
		}catch (Exception e) {
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCellValue(String sheetname,int rownum,int cellnum,String data)throws IOException {
		File xl=new File(path);
		if(xl.exists()) {   //if file not exit then create file
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetname)==-1) {//if sheet not exit then create sheet
			wb.createSheet(sheetname);
			ws=wb.getSheet(sheetname);
		}
		
		if(ws.getRow(rownum)==null) {//if row not exit then create row
			ws.createRow(rownum);
			row=ws.getRow(rownum);
		}
		
		cell=row.createCell(cellnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreenColor(String xlsheet,int rownum,int celnum)throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(celnum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String xlsheet,int rownum,int celnum)throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(celnum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	

}
