package Savills;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil 
{
	public static String captureScreenshot(WebDriver driver, String screenshotName) 
	 {
	        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String screenshotPath = "test-output/screenshots/" + screenshotName + "_" + timestamp + ".png";
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File dest = new File(screenshotPath);

	        try 
	        {
	            dest.getParentFile().mkdirs(); // Ensure the folder exists
	            Files.copy(src.toPath(), dest.toPath());
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        return screenshotPath;
	 }
}
