package Savills;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;


public class savills {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void setup() throws Exception
	{
		
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	
	driver.get("https://www.savills.com/");
	driver.manage().window().maximize();
	
	System.out.println(driver.getTitle());
	
	System.out.println(driver.getCurrentUrl());
	
	//System.out.println(driver.getPageSource());
	
	System.out.println(driver.getWindowHandle());
	
	}
	
	@Test(priority = 2)
	public void search() throws Exception
	{
	WebElement find_property = driver.findElement(By.xpath("//a[text()='Find a property']"));
	find_property.click();
	
	WebElement search = driver.findElement(By.xpath("//input[@id='sv-search-location']"));
	
	Actions action = new Actions(driver);
	action.moveToElement(search).click().sendKeys("horsell").pause(2000).sendKeys(Keys.ENTER).build().perform();
	//action.moveToElement(search).click().keyDown(Keys.SHIFT).sendKeys("horsell").build().perform();
	}
		
	@Test(priority = 3)
	public void all_price() throws Exception
	{
		Thread.sleep(5000);
		WebElement all_price = driver.findElement(By.xpath("//div[@class='sv-filter-label__values']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", all_price);
		
		//all_price.click();
		
		WebElement currency_list_main_div = driver.findElement(By.xpath("//div[@class = 'sv-filter-control__select-component']"));
		List<WebElement> c_list = currency_list_main_div.findElements(By.tagName("button"));
		for(WebElement currency : c_list)
		{
			String value = currency.getText().trim();
			System.out.println(value);
			
			if(value.equalsIgnoreCase("inr"))
			{
				currency.click();
				break;
			}
		}
		
		Thread.sleep(2000);
		WebElement minimum_currency_div = driver.findElement(By.xpath("//div[@class='sv-filter-control__option-list'][2]/div[@class='sv-filter-control__select-component']"));		
		List<WebElement> minimum_price_list = minimum_currency_div.findElements(By.tagName("button"));		
		for(WebElement min_price : minimum_price_list)
		{
			String min_price_value = min_price.getText().trim();
			System.out.println(min_price_value);
			if(min_price_value.equalsIgnoreCase("₹ 12,50,00,000"))
			{
				min_price.click();
				break;
			}
			
			js.executeScript("arguments[0].scrollIntoView(true);", min_price);
		}
		
		
		
		Thread.sleep(2000);
		
		WebElement maximum_currency_div = driver.findElement(By.xpath("//div[@class='sv-filter-control__option-list'][3]/div[@class='sv-filter-control__select-component']"));
		List<WebElement> maximum_price_list = maximum_currency_div.findElements(By.tagName("button"));		
		for(WebElement max_price : maximum_price_list)
		{
			String max_price_value = max_price.getText().trim();
			System.out.println(max_price_value);
			if(max_price_value.equalsIgnoreCase("₹ 3,00,00,00,000"))
			{
				max_price.click();
				break;
			}
			
			js.executeScript("arguments[0].scrollIntoView(true);", max_price);
		}
		
		
		WebElement property_type = driver.findElement(By.xpath("//button[@data-control-id='PropertyTypes']"));
		property_type.click();
		
		driver.findElement(By.xpath("//label//span[text()='Bungalow']")).click();
		driver.findElement(By.xpath("//button[@type='submit']//span[contains(text(),'Search')]")).click();
	}
	
	

}
