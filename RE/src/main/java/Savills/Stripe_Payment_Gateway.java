package Savills;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Savills.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Stripe_Payment_Gateway {
	
	WebDriver driver;
	
	@BeforeMethod
	public void stripe_payment_gateway() throws Exception
	{
		
		WebElement firstname, lastname, email, phone, password;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://theartofselfcraftsmanship.org/product/digital-calendar/");
		
		ExtentManager.getInstance();
				
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//webpushrpromptbtnapprove2[text()='YES']")));
	
		driver.findElement(By.xpath("//webpushrpromptbtndeny2[text()='NOT YET']")).click();
		
		driver.findElement(By.xpath("//button[text()='Get Started Now']")).click();
		
		firstname = driver.findElement(By.xpath("//input[@name='billing_first_name']"));
		firstname.sendKeys("Kian");
		
		lastname = driver.findElement(By.xpath("//input[@name='billing_last_name']"));
		lastname.sendKeys("stokes");
		
		phone = driver.findElement(By.xpath("//input[@name='billing_phone']"));
		phone.sendKeys("9809889807");
		  
		email = driver.findElement(By.xpath("//input[@name='billing_email']"));
		email.sendKeys("kianstokes@gmail.com");
		
		password = driver.findElement(By.xpath("//input[@name='account_password']"));
		password.sendKeys("Kian@1234");
		
		driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
			
	}
	
	@AfterSuite
	public void flush_report() throws Exception
	{
		ExtentManager.getInstance().flush();
	}
	
	@Test(priority=1)
	public void invalid_card_detail() throws Exception
	{
		WebElement card_number, card_number_frame, expiry_date, expiry_date_frame, cvc_frame, cvc;
		
		card_number_frame =  driver.findElement(By.xpath("//iframe[@title='Secure card number input frame']"));
		
		driver.switchTo().frame(card_number_frame);
		
		card_number = driver.findElement(By.xpath("//input[@name='cardnumber']"));
		card_number.sendKeys("4242424242424242");
		
		driver.switchTo().defaultContent();
		
		expiry_date_frame = driver.findElement(By.xpath("//iframe[@title='Secure expiration date input frame']"));
		
		driver.switchTo().frame(expiry_date_frame);
		
		expiry_date = driver.findElement(By.xpath("//input[@name='exp-date']"));
		expiry_date.sendKeys("0226");
		
		driver.switchTo().defaultContent();
		
		cvc_frame = driver.findElement(By.xpath("//iframe[@title='Secure CVC input frame']"));
		
		driver.switchTo().frame(cvc_frame);
		
		cvc = driver.findElement(By.xpath("//input[@name='cvc']"));
		cvc.sendKeys("123");
		
		driver.switchTo().defaultContent();
		
		ExtentTest test = ExtentManager.getInstance().createTest("Checking invalid card detail");
		
		//driver.findElement(By.xpath("//input[@name='terms']")).click();
		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
				
		String expected_url = "https://theartofselfcraftsmanship.org/checkout/";
		String current_url = driver.getCurrentUrl();
		
		String expected_result = "When user submit the invalid card detail then form should not move on next step";
		String actual_result = "When user enter invalid detail then form is not moving on next step.";
		
		String failed_result = "When user enter invalid detail then form is moving on next step";
		
		if(current_url.equals(expected_url))
		{
			test.pass("You have entered invalid card details.");
			
        	String path = ScreenshotUtil.captureScreenshot(driver, "Card_Detail Invalid");
            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			test.info(expected_result);
			test.info(actual_result);
		}
		else
		{
			test.fail("Card detail is working proper.");
			
			String path = ScreenshotUtil.captureScreenshot(driver, "Card_Detail Valid");
            test.pass("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			test.info(expected_result);
			test.info(failed_result);
		}
	}
	
	@Test(priority=2)
	public void expired_card_detail() throws Exception
	{
		WebElement card_number, card_number_frame, expiry_date, expiry_date_frame, cvc_frame, cvc;
		
		card_number_frame =  driver.findElement(By.xpath("//iframe[@title='Secure card number input frame']"));
		
		driver.switchTo().frame(card_number_frame);
		
		card_number = driver.findElement(By.xpath("//input[@name='cardnumber']"));
		card_number.clear();
		card_number.sendKeys("4242424242424242");
		
		driver.switchTo().defaultContent();
		
		expiry_date_frame = driver.findElement(By.xpath("//iframe[@title='Secure expiration date input frame']"));
		
		driver.switchTo().frame(expiry_date_frame);
		
		expiry_date = driver.findElement(By.xpath("//input[@name='exp-date']"));
		expiry_date.clear();
		expiry_date.sendKeys("0212");
		
		driver.switchTo().defaultContent();
		
		cvc_frame = driver.findElement(By.xpath("//iframe[@title='Secure CVC input frame']"));
		
		driver.switchTo().frame(cvc_frame);
		
		cvc = driver.findElement(By.xpath("//input[@name='cvc']"));
		cvc.clear();
		cvc.sendKeys("123");
		
		driver.switchTo().defaultContent();
		
		ExtentTest test = ExtentManager.getInstance().createTest("Checking expired card detail");
		
		//driver.findElement(By.xpath("//input[@name='terms']")).click();
		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
				
		String expected_url = "https://theartofselfcraftsmanship.org/checkout/";
		String current_url = driver.getCurrentUrl();
		
		String expected_result = "When user submit the expired card detail then form should not move on next step";
		String actual_result = "When user enter expired card detail then form is not moving on next step.";
		
		String failed_result = "When user enter expired card detail then form is moving on next step";
		
		if(current_url.equals(expected_url))
		{
			test.pass("You have entered expired card details.");
			
        	String path = ScreenshotUtil.captureScreenshot(driver, "Card_Detail Expired");
            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			test.info(expected_result);
			test.info(actual_result);
		}
		else
		{
			test.fail("Card detail is working proper.");
			
			String path = ScreenshotUtil.captureScreenshot(driver, "Card_Detail Valid");
            test.pass("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			test.info(expected_result);
			test.info(failed_result);
		}
	}
	
	@Test(priority=3)
	public void insufficient_fund() throws Exception
	{
		WebElement card_number, card_number_frame, expiry_date, expiry_date_frame, cvc_frame, cvc;
		
		card_number_frame =  driver.findElement(By.xpath("//iframe[@title='Secure card number input frame']"));
		
		driver.switchTo().frame(card_number_frame);
		
		card_number = driver.findElement(By.xpath("//input[@name='cardnumber']"));
		card_number.clear();
		card_number.sendKeys("4000000000009995");
		
		driver.switchTo().defaultContent();
		
		expiry_date_frame = driver.findElement(By.xpath("//iframe[@title='Secure expiration date input frame']"));
		
		driver.switchTo().frame(expiry_date_frame);
		
		expiry_date = driver.findElement(By.xpath("//input[@name='exp-date']"));
		expiry_date.clear();
		expiry_date.sendKeys("0228");
		
		driver.switchTo().defaultContent();
		
		cvc_frame = driver.findElement(By.xpath("//iframe[@title='Secure CVC input frame']"));
		
		driver.switchTo().frame(cvc_frame);
		
		cvc = driver.findElement(By.xpath("//input[@name='cvc']"));
		cvc.clear();
		cvc.sendKeys("123");
		
		driver.switchTo().defaultContent();
		
		ExtentTest test = ExtentManager.getInstance().createTest("Checking Insufficient fund card error.");
		
		//driver.findElement(By.xpath("//input[@name='terms']")).click();
		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
				
		String expected_url = "https://theartofselfcraftsmanship.org/checkout/";
		String current_url = driver.getCurrentUrl();
		
		String expected_result = "When user submit the insufficient fund card detail then form should not move on next step";
		String actual_result = "When user enter insufficient fund card detail then form is not moving on next step.";
		
		String failed_result = "When user enter insufficient fund detail then form is moving on next step";
		
		if(current_url.equals(expected_url))
		{
			test.pass("You have entered insufficient card details.");
			
        	String path = ScreenshotUtil.captureScreenshot(driver, "Card_Detail showing insufficient fund.");
            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			test.info(expected_result);
			test.info(actual_result);
		}
		else
		{
			test.fail("Card detail is working proper.");
			
			String path = ScreenshotUtil.captureScreenshot(driver, "Card_Detail Valid");
            test.pass("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			test.info(expected_result);
			test.info(failed_result);
		}
		
	}
	
}
