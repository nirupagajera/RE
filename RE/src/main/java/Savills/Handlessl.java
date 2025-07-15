package Savills;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Handlessl {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void handlessl() throws Exception
	{
		ChromeOptions option = new ChromeOptions();
		option.setAcceptInsecureCerts(true);
		
		driver = new ChromeDriver(option);
		driver.get("https://expired.badssl.com/");
	}

}
