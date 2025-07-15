package Savills;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Data_Driven_Testing {
	
	WebDriver driver;
	
	//@Test(priority = 1)
	public void read_csv() throws Exception
	{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\Test_Data\\Data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int row = sheet.getLastRowNum();
		int cell = sheet.getRow(row).getLastCellNum();
		
		System.out.println(row);
		System.out.println(cell);
		
		for(int r = 0; r<=row; r++)
		{
			XSSFRow current_row = sheet.getRow(r);
			for(int c = 0; c<cell; c++)
			{
				XSSFCell current_cell = current_row.getCell(c);
				System.out.print(current_cell.toString() + "\t");
			}
			
			System.out.println();
		}
		
		workbook.close();
		file.close();
	}
	
	
	//@Test(priority = 2)
	public void write_data() throws Exception
	{
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\Test_Data\\Write_data.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Data");
		
		XSSFRow row1 = sheet.createRow(0);
		
			row1.createCell(0).setCellValue("Name");
			row1.createCell(1).setCellValue("Email");
			row1.createCell(2).setCellValue("Mobile Number");
			
		XSSFRow row2 = sheet.createRow(1);
		
			row2.createCell(0).setCellValue("Nirupa");
			row2.createCell(1).setCellValue("nirupa@krishaweb.com");
			row2.createCell(2).setCellValue("7788798789");
			
		XSSFRow row3 = sheet.createRow(2);
		
			row3.createCell(0).setCellValue("Neel");
			row3.createCell(1).setCellValue("neel@gmail.com");
			row3.createCell(2).setCellValue("232132137632");
			
		workbook.write(file);
		workbook.close();
		file.close();
		
	
	}
	
	@Test(priority = 3)
	public void write_user() throws Exception
	{
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\Test_Data\\write_data_User.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Data_By_User");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Number of Rows");
		int noOfRows = sc.nextInt();
		
		System.out.println("Enter Number of Columns");
		int noOfCells = sc.nextInt();
		
		for(int r = 0; r<= noOfRows; r++)
		{
			XSSFRow row = sheet.createRow(r);
			for(int c = 0; c<noOfCells; c++)
			{
				XSSFCell cell = row.createCell(c);
				cell.setCellValue(sc.next());
			}
		}
		
		workbook.write(file);
		workbook.close();
		file.close();
		
		
	}

}
