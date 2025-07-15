package Savills;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Broken_Link {
	
	WebDriver driver;
	
	//@Test(priority = 1)
	public void broken_link() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://www.japanconsultingoffice.com/");
		
		int noOfBrokenUrl = 0;
		List<WebElement> Link = driver.findElements(By.tagName("a"));
		for(WebElement link:Link)
		{
			String linkatt = link.getAttribute("href");
			if(linkatt == null || linkatt.isEmpty())
			{
				System.out.println("Link tag is empty");
				continue;
			}
			
			URL linkURL = new URL(linkatt);
			HttpsURLConnection URLCode = (HttpsURLConnection) linkURL.openConnection();
			URLCode.connect();
			
			if(URLCode.getResponseCode()>=400)
			{
				System.out.println(URLCode + " Broken Link " + URLCode.getResponseCode());
				noOfBrokenUrl++;
			}
			else
			{
				System.out.println(URLCode + " Valid Link " + URLCode.getResponseCode());
			}
			
		}
		
		System.out.println("How many number of Broken Link are there: " + noOfBrokenUrl );

	}
	
	@Test(priority = 2)
	public void shadow_root() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/"); 
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3500)");
		
		WebElement shadow = driver.findElement(By.xpath("//div[@id = 'shadow_host']"));
		SearchContext shadow_root = shadow.getShadowRoot();
		WebElement shadow_input = shadow_root.findElement(By.cssSelector("input[type=text]:nth-child(5)"));

		shadow_input.sendKeys("Test");
	}

}
