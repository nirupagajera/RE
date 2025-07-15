package Savills;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class table_demo {
	
	WebDriver driver;
	
	//@Test(priority = 1)
	public void simple_travel_book() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://blazedemo.com/");
		
		WebElement departure = driver.findElement(By.xpath("//select[@name = 'fromPort']"));
		departure.click();
		
		Select departure_location = new Select(departure);
		departure_location.selectByVisibleText("Portland");
		
		WebElement destination = driver.findElement(By.xpath("//select[@name = 'toPort']"));
		destination.click();
		
		Select destination_location = new Select(destination);
		destination_location.selectByVisibleText("New York");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		List <WebElement> price_column = driver.findElements(By.xpath("//table[@class='table']//tr//td[6]"));
		for(WebElement pc : price_column)
		{
			String text = pc.getText().trim();
			System.out.println(text);
			
			int[] value = {472,432,200,765,233};
			Arrays.sort(value);
			System.out.println(Arrays.toString(value));
			break;
		}
		
		WebElement table = driver.findElement(By.xpath("//table[@class='table']/tbody"));

		List<WebElement> row = table.findElements(By.tagName("tr"));
		for(int i = 0;i<row.size();i++)
		{
			List<WebElement> column = row.get(i).findElements(By.xpath("//table[@class='table']//tr//td[6]"));
			WebElement pricet = column.get(i);
			String priceto = pricet.getText().trim();
			System.out.println(priceto);
				
				if(priceto.contains("200"))
				{
					WebElement choose_flight = column.get(i).findElement(By.xpath("//table[@class='table']//tr//td[1]"));					
					choose_flight.click();
					break;
					
				}
		}
		
		driver.findElement(By.xpath("//input[@id='inputName']")).sendKeys("Kian Stokes");
		driver.findElement(By.xpath("//input[@id='address']")).sendKeys("Bapunagar");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Ahmedabad");
		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Gujarat");
		driver.findElement(By.xpath("//input[@id='zipCode']")).sendKeys("382350");
		WebElement card_type = driver.findElement(By.xpath("//select[@id='cardType']"));
		Select card = new Select(card_type);
		card.selectByVisibleText("Diner's Club");
		
		driver.findElement(By.xpath("//input[@id='creditCardNumber']")).sendKeys("4242424242424242");
		driver.findElement(By.xpath("//input[@id='nameOnCard']")).sendKeys("Kian Stokes");
		driver.findElement(By.xpath("//input[@id='rememberMe']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String expected_url = "https://blazedemo.com/confirmation.php";
		if(expected_url.equals(driver.getCurrentUrl()))
		{
			System.out.println("Form is submitted successfully");
		}
		else
		{
			System.out.println("Form having error");
		}
	}
	
	//@Test(priority = 2)
	public void admin_table() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
				
		driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		
		
		driver.findElement(By.xpath("//a[@href = '/web/index.php/admin/viewAdminModule']")).click();
		
		WebElement table = driver.findElement(By.xpath("//div[@role='table']//div[@role='rowgroup'][2]"));
		List<WebElement> table_row = table.findElements(By.xpath("//div[@role = 'row']"));
		
		for(int i =0;i<table_row.size();i++)
		{
			List<WebElement> username_column = table_row.get(i).findElements(By.xpath("//div[@role='table']//div[@role='rowgroup'][2]//div[@role = 'cell'][2]"));
			WebElement username = username_column.get(i);
			String username_text = username.getText().trim();	
			
			List<WebElement> role_column = table_row.get(i).findElements(By.xpath("//div[@role='table']//div[@role='rowgroup'][2]//div[@role = 'cell'][3]"));
			WebElement user_role = role_column.get(i);
			String userrole_text = user_role.getText().trim();	
			System.out.println("Username is " + username_text + " and role is " + userrole_text);
			
		}	
		
	}
	
	//@Test(priority = 3)
	public void pagination_table() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		List<WebElement> pagination_count = driver.findElements(By.xpath("//ul[@class='pagination']//li"));
		
		
		for(int count = 1; count<=pagination_count.size();count++)
		{
			WebElement table_id = driver.findElement(By.xpath("//table[@id='productTable']//tbody"));
			List<WebElement> table_row = table_id.findElements(By.tagName("tr"));
			for(int i = 0; i<table_row.size(); i++)
			{
				List<WebElement> table_column = table_row.get(i).findElements(By.xpath("//table[@id='productTable']//tr//td[4]/input[@type='checkbox']"));
									
				WebElement checkbox = table_column.get(i);
				checkbox.click();			
				Thread.sleep(1000);
			}
			
			if(count<pagination_count.size())
			{
			WebElement next_page = pagination_count.get(count);
			next_page.click();
			}
		}
		
	}
	
	//@Test(priority = 4)
	public void date_picker() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://dummy-tickets.com/buyticket");
		
		driver.findElement(By.xpath("//input[@name='source[]']")).sendKeys("ahmedabad");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='col-sm-12 p-0 suggestion-container'][1]//ul//li[1]")).click();
		
		driver.findElement(By.xpath("//input[@name = 'destination[]']")).sendKeys("mumbai");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='col-sm-12 p-0 suggestion-container'][2]//ul//li[1]")).click();
		
		driver.findElement(By.xpath("//input[@name = 'departure[]']")).sendKeys("26-06-2025");
		
		driver.findElement(By.xpath("//input[@type = 'submit']")).click();
	}
	
	//@Test(priority = 5)
	public void dummy_ticket_visa() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://www.dummyticket.com/dummy-ticket-for-visa-application/");
		
		driver.findElement(By.xpath("//input[@name = 'travname']")).sendKeys("Kian");
		
		driver.findElement(By.xpath("//input[@name = 'travlastname']")).sendKeys("Stokes");
		
		//Date of Birth Selection
		
		driver.findElement(By.xpath("//input[@name = 'dob']")).click();
		
		WebElement year_options = driver.findElement(By.xpath("//select[@class = 'ui-datepicker-year']"));
		
		Select option = new Select(year_options);
		option.selectByVisibleText("2001");
		
		//Thread.sleep(1000);
		
		WebElement month_option = driver.findElement(By.xpath("//select[@class = 'ui-datepicker-month']"));
		
		Select month = new Select(month_option);
		month.selectByVisibleText("Sep");
		
		WebElement day_pick = driver.findElement(By.xpath("//table[@class = 'ui-datepicker-calendar']//tbody"));
		List<WebElement> day_row = day_pick.findElements(By.tagName("tr"));
		
		for(int i = 0; i<day_row.size();i++)
		{
			List<WebElement> day_column = day_row.get(i).findElements(By.xpath("//td//a"));
			
			for(int j = 0;j<day_column.size();j++)
			{	
				WebElement col = day_column.get(j);
				String text = col.getText().trim();
				System.out.println(text);
				
				if(text.equalsIgnoreCase("29"))
				{
					col.click();
				}
			}
			break;
		}
		
		driver.findElement(By.xpath("//input[@id = 'sex_2']")).click();
		
		driver.findElement(By.xpath("//input[@id = 'addmorepax']")).click();
		
		WebElement add_passanger = driver.findElement(By.xpath("//span[@class= 'selection']"));
		add_passanger.click();
		
		WebElement list_ul = driver.findElement(By.xpath("//span//ul[@class = 'select2-results__options']"));
		List<WebElement> list = list_ul.findElements(By.tagName("li"));
		list.get(0).click();
		
		driver.findElement(By.xpath("//input[@name = 'fromcity']")).sendKeys("ahmedabad");
		driver.findElement(By.xpath("//input[@name = 'tocity']")).sendKeys("dwarka");
		
		WebElement departure_date = driver.findElement(By.cssSelector("#departon"));
		departure_date.click();
		
		WebElement year = driver.findElement(By.xpath("//select[@class = 'ui-datepicker-year']"));
		
		Select year_option1 = new Select(year);
		year_option1.selectByVisibleText("2026");
		
		WebElement month1 = driver.findElement(By.xpath("//select[@class = 'ui-datepicker-month']"));
		
		Select month_option1 = new Select(month1);
		month_option1.selectByVisibleText("Jun");
						
		List<WebElement> date_picker = driver.findElements(By.xpath("//table[@class = 'ui-datepicker-calendar']//tbody//tr"));
		
		for(int i = 0; i<date_picker.size(); i++)
		{
			List<WebElement> date_column = date_picker.get(i).findElements(By.tagName("td"));
			
			for(int j = 0; j< date_column.size(); j++)
			{
				WebElement col_ele = date_column.get(j);
				String col_txt = col_ele.getText().trim();
				System.out.println(col_txt);
				
				if(col_txt.equalsIgnoreCase("20")) 
				{
				col_ele.click();
				}
				
			}
		}
		
		WebElement dummy_ticket = driver.findElement(By.xpath("//span[@title='Visa application']"));
		dummy_ticket.click();
		
		List<WebElement> ticket_list = dummy_ticket.findElements(By.xpath("//span[@class = 'select2-results']//ul//li"));
		ticket_list.get(0).click();
		
		driver.findElement(By.xpath("//input[@id = 'deliverymethod_3']")).click();
		
		//Billing Detail
		
		driver.findElement(By.cssSelector("#billname")).sendKeys("Kian Stokes");
		
		driver.findElement(By.cssSelector("#billing_phone")).sendKeys("23913821312");
		
		driver.findElement(By.cssSelector("#billing_email")).sendKeys("kianstokes@gmail.com");
		
		driver.findElement(By.cssSelector("#select2-billing_country-container")).click();
		
		List<WebElement> country = driver.findElements(By.xpath("//ul[@id = 'select2-billing_country-results']//li"));
		country.get(2).click();
		
		driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("Bapunagar");
		
		driver.findElement(By.cssSelector("#billing_city")).sendKeys("Ahmedabad");
		
		driver.findElement(By.cssSelector("#select2-billing_state-container")).click();
		
		List<WebElement> state = driver.findElements(By.xpath("//ul[@id='select2-billing_state-results']//li"));
		state.get(6).click();
		
		driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("382350");
		
		driver.findElement(By.cssSelector("#place_order")).click();
		
		
	}
	
	//@Test(priority = 6)
	public void action_event() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		//Double click
		
		WebElement double_click_copy = driver.findElement(By.xpath("//button[@ondblclick='myFunction1()']"));
		
		Actions act = new Actions(driver);
		act.doubleClick(double_click_copy).build().perform();
		
		//Drag and Drop functionality
		
		WebElement drag_card = driver.findElement(By.xpath("//div[@id = 'draggable']"));
		WebElement drop_card = driver.findElement(By.xpath("//div[@id = 'droppable']"));
		
		act.dragAndDrop(drag_card, drop_card).perform();

	}
	
	//@Test(priority = 7)
	public void drag_drop_functionality() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://www.demo.guru99.com/test/drag_drop.html");
		
		Actions act = new Actions(driver);
		
		WebElement bank_drag_element = driver.findElement(By.xpath("//li[@id= 'credit2']"));
		WebElement bank_drop_element = driver.findElement(By.xpath("//ol[@id='bank']//li"));
		
		act.dragAndDrop(bank_drag_element, bank_drop_element).build().perform();
		
		WebElement amount_drag = driver.findElement(By.xpath("//li[@id = 'fourth']"));
		WebElement amount_drop = driver.findElement(By.xpath("//ol[@id = 'amt7']//li"));
		
		act.dragAndDrop(amount_drag, amount_drop).build().perform();
		
		WebElement account_drag = driver.findElement(By.xpath("//li[@id = 'credit1']"));
		WebElement account_drop = driver.findElement(By.xpath("//ol[@id = 'loan']//li"));
		
		act.dragAndDrop(account_drag, account_drop).build().perform();
		
		WebElement credit_amount_drag = driver.findElement(By.xpath("//li[@id = 'fourth']"));
		WebElement credit_amount_drop = driver.findElement(By.xpath("//ol[@id = 'amt8']//li"));
		
		act.dragAndDrop(credit_amount_drag, credit_amount_drop).build().perform();
		
	}
	
	//@Test(priority = 8)
	public void price_slider() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://www.jqueryscript.net/demo/Price-Range-Slider-jQuery-UI/");
		
		WebElement min_slider = driver.findElement(By.xpath("//div[@id = 'slider-range']//span[1]"));
		System.out.println(min_slider.getLocation());
		
		Actions act = new Actions(driver);
		act.dragAndDropBy(min_slider, 100, 250).build().perform();
		
		WebElement max_slider = driver.findElement(By.xpath("//div[@id = 'slider-range']//span[2]"));
		System.out.println(max_slider.getLocation());
		
		act.dragAndDropBy(max_slider, -90, 250).build().perform();
	}
	
	//@Test(priority = 9)
	public void keyboard_event() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://text-compare.com/");
		driver.manage().window().maximize();
		
		WebElement textarea1 = driver.findElement(By.xpath("//textarea[@id= 'inputText1']"));
		textarea1.sendKeys("Welcome");
		
		//select the text
		
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		
		//copy text
		
		act.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).perform();
		
		//click on tab key
		
		act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
		
		//paste the text
		
		act.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).perform();
		
	}
	
	@Test(priority = 10)
	public void switch_tab() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
		
//		WebElement regi_link = driver.findElement(By.xpath("//a[@class = 'ico-register']"));
//		Actions act = new Actions(driver);
//		
//		act.keyDown(Keys.CONTROL).click(regi_link).keyUp(Keys.CONTROL).build().perform();
//		
//		Set<String> id = driver.getWindowHandles();
//		ArrayList<String> tab = new ArrayList<String>(id); 
//		
//		driver.switchTo().window(tab.get(1));
//		
//		driver.findElement(By.xpath("//input[@id = 'FirstName']")).sendKeys("Nirupa");
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("https://krishaweb.com");
		
	}

}
