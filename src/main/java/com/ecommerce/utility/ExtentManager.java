package com.ecommerce.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    
    // Declare the ExtentSparkReporter, ExtentReports, and ExtentTest instances
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    
    public static void setExtent() {
        // Initialize the htmlReporter with the desired report path
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport/" + "PassReport.html");
        htmlReporter.config().setDocumentTitle("MyEcommerceProject");
        htmlReporter.config().setReportName("MyEcommerceProjectReport");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // Set system information for the report
        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "ECommerceProject");
        extent.setSystemInfo("Tester", "Anuradha");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Browser", "Chrome");
    }
    
    public static void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
