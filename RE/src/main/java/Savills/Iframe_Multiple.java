package Savills;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Iframe_Multiple {
	
	WebDriver driver;
	
	//@Test(priority = 1)
	public void switch_multiple_frame() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/html/html_iframe.asp");
		System.out.println(driver.getTitle());
		
		WebElement frame = driver.findElement(By.xpath("//iframe[@title='W3Schools HTML Tutorial']"));
		
		driver.switchTo().frame(frame);
		driver.switchTo().frame(0);
		//driver.switchTo().parentFrame();
				
		WebElement iframe_text = driver.findElement(By.xpath("//a[text()='CSS']"));
		iframe_text.click();
		
		driver.switchTo().defaultContent();
	}
	
	//@Test(priority = 2)
	public void iframe_5th() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://ui.vision/demo/webtest/frames/");
		
		WebElement frame5 = driver.findElement(By.xpath("//frame[@src = 'frame_5.html']"));
		
		driver.switchTo().frame(frame5);
		
		driver.findElement(By.xpath("//a[text()='https://a9t9.com']")).click();
				
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//html[contains(@class,'js flexbox flexboxlegacy')]")));
		WebElement logo = driver.findElement(By.xpath("//img[@alt='Ui.Vision by a9t9 software - Image-Driven Automation']"));
		boolean logo_status = logo.isDisplayed();
		System.out.println(logo_status);
		
	
	}
	
	//@Test(priority = 3)
	public void select_dropdown() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://phppot.com/demo/jquery-dependent-dropdown-list-countries-and-states/");
		
		driver.findElement(By.xpath("//select[@id='country-list']")).click();
		
		WebElement select_dropdown = driver.findElement(By.id("country-list"));
		Select dropdown = new Select(select_dropdown);
		dropdown.selectByVisibleText("China");
	}
	
	//@Test(priority = 4)
	public void multiple_dropdown() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		List<WebElement> sel_drp = driver.findElements(By.xpath("//select[@id='colors']//option"));
		for(WebElement dropdown : sel_drp)
		{
			String text = dropdown.getText().trim();
			
			if(text.equalsIgnoreCase("yellow") || text.equalsIgnoreCase("green") || text.equalsIgnoreCase("red"))
			{
				dropdown.click();
			}
		}
	}
	
	@Test(priority = 5)
	public void hide_element() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//a//span[text()='PIM']")).click();
		WebElement open_dropdown = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div"));
		open_dropdown.click();
		
		WebElement list = driver.findElement(By.xpath("//div[@role='listbox']"));
		List<WebElement> dropdown = list.findElements(By.xpath("//div[@role='option']//span"));
		for(WebElement drp : dropdown)
		{
			String text = drp.getText().trim();
			System.out.println(text);
			if(text.equalsIgnoreCase("Financial Analyst"))
			{
				drp.click();
			}
		}
	}

}
