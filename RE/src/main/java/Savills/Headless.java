package Savills;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Headless {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void headless() throws Exception
	{
		ChromeOptions option = new ChromeOptions();
		//option.addArguments("--headless=new");
		option.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
		//Open in Incognito mode
		
		//option.addArguments("--incognito");
		
		driver = new ChromeDriver(option);
		
		driver.get("https://krishaweb.com");
		System.out.println("Done");
	}

}
