package Savills;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SVG_Interaction {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void svg_interaction() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		
		Thread.sleep(1000);
		WebElement recruitment_svg = driver.findElement(By.xpath("//*[name() = 'path' and contains(@d,'M293.5,250c35.841,0,65')]/ancestor::a"));
		recruitment_svg.click();
	}
	

}
