package Savills;

import java.lang.module.ModuleDescriptor.Exports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {
	
		private static ExtentReports extent;

		public static ExtentReports getInstance() 
		{
			 if (extent == null) 
			 {
		            ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReport.html");
		            reporter.config().setReportName("Automation Test Report");
		            reporter.config().setDocumentTitle("Zadoon Project Report");
		            reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
		            extent = new ExtentReports();
		            extent.attachReporter(reporter);
		            reporter.config().setDocumentTitle("NC Automation Report");
		            reporter.config().setReportName("NC Automation Report");
		            reporter.config().setTheme(Theme.STANDARD);
		            reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			 }
			 return extent;
		}

}
