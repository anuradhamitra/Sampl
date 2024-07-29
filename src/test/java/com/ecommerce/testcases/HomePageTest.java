package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.HomePage;
import com.ecommerce.utility.Log;


public class HomePageTest extends BaseClass {
   
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

    @Test(groups="Smoke")
    public void verifyLogo() throws Throwable {
        Log.info("Starting test verifyLogo");
        Log.startTestCase("verifyLogo");

        indexPage = new HomePage();
        boolean result = indexPage.validateLogo();
        
        Assert.assertTrue(result);

        Log.endTestCase("verifyLogo");
        Log.info("verifyLogo test completed");
    }

    @Test(groups="Smoke")
    public void verifyCartIcon() throws Throwable {
        Log.info("Starting test verifyCartIcon");
        Log.startTestCase("verifyCartIcon");

        indexPage = new HomePage();
        boolean result = indexPage.validateCart();
        
        Log.info("Cart icon validation result: "+ result);
        Assert.assertTrue(result);

        Log.endTestCase("verifyCartIcon");
        Log.info("verifyCartIcon test completed");
    }

    @Test(groups="Smoke")
    public void verifyTitle() {
        Log.info("Starting test verifyTitle");
        Log.startTestCase("verifyTitle");

        indexPage = new HomePage();
        String actTitle = indexPage.getMyStoreTitle();
        
        Log.info("Actual title: , Expected title: Automation Exercise"+ actTitle);
        Assert.assertEquals(actTitle, "Automation Exercise");

        Log.endTestCase("verifyTitle");
        Log.info("verifyTitle test completed");
    }
}