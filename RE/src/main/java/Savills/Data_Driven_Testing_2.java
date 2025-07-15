package Savills;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Data_Driven_Testing_2 {
	
	public WebDriver driver;
	
	@Test(priority = 1)
	public void demo_contact() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://qavalidation.com/demo-form/");
		
		WebElement fullname, email, phone_number;
		
		String filePath = System.getProperty("user.dir")+"\\Test_Data\\contact_data.xlsx";
		
		int rows = ExcelUtils.getRowCount(filePath, "sheet1");
		
		for(int i = 1; i<=rows; i++)
		{
			String Fullname = ExcelUtils.getCellData(filePath, "sheet1", i, 0);
			String Email = ExcelUtils.getCellData(filePath, "sheet1", i, 1);
			String Phone_Number = ExcelUtils.getCellData(filePath, "sheet1", i, 2);
			String Gender = ExcelUtils.getCellData(filePath, "sheet1", i, 3);
			
			fullname = driver.findElement(By.cssSelector("#g4072-fullname"));
			fullname.clear();
			fullname.sendKeys(Fullname);
			
			email = driver.findElement(By.cssSelector("#g4072-email"));
			email.clear();
			email.sendKeys(Email);
			
			phone_number = driver.findElement(By.cssSelector("#g4072-phonenumber"));
			phone_number.clear();
			phone_number.sendKeys(Phone_Number);
			
			Select gender = new Select(driver.findElement(By.cssSelector("#g4072-gender")));
			gender.selectByVisibleText(Gender);
			
			Thread.sleep(2000);

		}
		
	}
}
