package com.ecommerce.testcases;

import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.HomePage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.utility.Log;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class LoginPageTest extends BaseClass {
   
    private LoginPage loginPage;
    private HomePage indexPage;

    @Parameters("browser")
    @BeforeMethod(groups= {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        Log.info("Setting up test with browser:"+ browser);
        launchApp(browser);
    }

    @AfterMethod(groups= {"Smoke","Sanity","Regression"})
    public void tearDown() {
        Log.info("Tearing down the test");
        getDriver().quit();
        Log.info("Driver quit successfully");
    }

    @Test(groups={"Smoke","Sanity"})
    public void loginTest() throws Throwable {
        Log.info("Starting loginTest");
        
        indexPage = new HomePage();
        Log.info("Navigated to HomePage");
        
        loginPage = indexPage.clickOnSignIn();
        Log.info("Clicked on sign-in");

        indexPage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
        Log.info("Entered login credentials");

        String actualURL = indexPage.getCurrURL();
        System.out.println(actualURL);
        String expectedURL = "https://automationexercise.com/";
        
        Log.info("Actual URL:" +actualURL);
        Log.info("Expected URL: "+ expectedURL);
        Assert.assertEquals(actualURL, expectedURL);

        Log.info("loginTest completed successfully");
    }
}