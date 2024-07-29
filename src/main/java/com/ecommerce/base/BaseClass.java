package com.ecommerce.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.ecommerce.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {
	public static Properties prop;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	@BeforeSuite(groups= {"Smoke","Sanity","Regression"})
	public void loadConfig() throws IOException
	{
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(
						System.getProperty("user.dir") + "\\Configuration\\config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static WebDriver getDriver() {
		return driver.get();
	}	
		public void launchApp(String browserName) {
			//String browserName = prop.getProperty("browser");
			if (browserName.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				// Set Browser to ThreadLocalMap
				driver.set(new ChromeDriver());
			} else if (browserName.equalsIgnoreCase("FireFox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
	            options.setCapability("marionette", true);
	            driver.set(new FirefoxDriver());
			} else if (browserName.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver.set(new InternetExplorerDriver());
			}
			else if (browserName.equalsIgnoreCase("Edge")) {
			    WebDriverManager.edgedriver().setup();
			    driver.set(new EdgeDriver());
			}
			//Maximize the screen
			getDriver().manage().window().maximize();
			//Delete all the cookies
			getDriver().manage().deleteAllCookies();
			//Launching the URL
			getDriver().get(prop.getProperty("url"));
		}
		@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
		public void afterSuite() {
			ExtentManager.endReport();
		}
}