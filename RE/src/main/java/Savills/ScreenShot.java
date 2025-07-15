package Savills;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenShot {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void capture_screenshot() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://krishaweb.com");
		
		//FullScreen Screenshot
		
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source_file = ts.getScreenshotAs(OutputType.FILE);
//		File target_file = new File(System.getProperty("user.dir")+"\\Screenshot\\Fullpage.png");
//		source_file.renameTo(target_file);
		
		//Section Screenshot
		
//		WebElement latest_project = driver.findElement(By.xpath("//div[@class = 'work-list-wrap ourwork-block']"));
//		File source_file = latest_project.getScreenshotAs(OutputType.FILE);
//		File target_file = new File(System.getProperty("user.dir")+"\\Screenshot\\latest_project.png");
//		source_file.renameTo(target_file);
		
		//logo Screenshot
		
		WebElement latest_project = driver.findElement(By.xpath("//div[@class = 'logo']"));
		File source_file = latest_project.getScreenshotAs(OutputType.FILE);
		File target_file = new File(System.getProperty("user.dir")+"\\Screenshot\\logo.png");
		source_file.renameTo(target_file);
		
		
	}

}
