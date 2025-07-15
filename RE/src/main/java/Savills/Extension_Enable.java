package Savills;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Extension_Enable {
	
	 WebDriver driver;
	 
	 @Test(priority = 1)
	 public void enable_extension() throws Exception
	 {
		 ChromeOptions option = new ChromeOptions();
		 File file = new File("C:\\maven projects\\SelectorsHub.crx");
		 option.addExtensions(file);
		 
		 driver = new ChromeDriver(option);
		 driver.get("https://krishaweb.com");
		 
				
	 }

}
