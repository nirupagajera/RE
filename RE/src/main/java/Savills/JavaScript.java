package Savills;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScript {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void javascript_input() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		WebElement phone = driver.findElement(By.cssSelector("#phone"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('Value','7600320564')", phone);
		
		js.executeScript("arguments[0].value = '7600320564'", phone);
		
		js.executeScript("window.scrollBy(0,500)");
		
		//Scroll at the end of the page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		//scroll till perticular point
		
		WebElement title = driver.findElement(By.xpath("//div[@id = 'HTML1']//h2"));
		
		js.executeScript("arguments[0].scrollIntoView(true);", title);
		
		//file uploader with multiple file
		
		WebElement file = driver.findElement(By.xpath("//input[@id = 'multipleFilesInput']"));
		String file1 = "C:\\Users\\Admin\\Desktop\\CASA Lobo\\2.png";
		String file2 = "C:\\Users\\Admin\\Desktop\\CASA Lobo\\3.png";
		file.sendKeys(file1 +"\n"+ file2);
		
		
	}

}
