package com.ecommerce.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseClass;
import com.ecommerce.dataprovider.DataProviders;
import com.ecommerce.pageobjects.AccountCreationPage;
import com.ecommerce.pageobjects.HomePage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.utility.Log;

public class AccountPageTest extends BaseClass {
    private LoginPage loginPage;
    private HomePage indexPage;
    private AccountCreationPage accountPage;

    @Parameters("browser")
    @BeforeMethod(groups= {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        Log.info("Setting up test with browser: " + browser);
        launchApp(browser); 
        Log.info("Setup completed");
    }

    @AfterMethod(groups= {"Smoke","Sanity","Regression"})
    public void tearDown() {
        Log.info("Starting tearDown method");
        getDriver().quit();
        Log.info("Driver quit successfully");
    }

    @Test(enabled = false, groups="Sanity", dataProvider="accountsignupemail", dataProviderClass = DataProviders.class)
    public void verifyCreateAccountPageTest(String Name, String Email) throws Throwable {
        Log.info("Starting verifyCreateAccountPageTest");
        indexPage = new HomePage();
        loginPage = indexPage.clickOnSignIn();
        accountPage = loginPage.createNewAccount(Name, Email);

        // HardAssert: Critical check, stop test if this fails
        String actualURL = accountPage.getCurrURL();
        String expectedURL = "https://automationexercise.com/signup";
        Log.info("Actual URL: " + actualURL);
        Log.info("Expected URL: " + expectedURL);
        Assert.assertEquals(actualURL, expectedURL, "URL verification failed");
        Log.info("URL verification passed");

        // SoftAssert: Non-critical checks, continue test even if these fail
        SoftAssert softAssert = new SoftAssert();
        boolean result = accountPage.validateAcountCreatePage();
        Log.info("Account creation page validation result: " + result);
        softAssert.assertTrue(result, "Account creation page validation failed");

        // Call assertAll to report any soft assertion failures
        softAssert.assertAll();

        Log.info("verifyCreateAccountPageTest completed successfully");
    }

    @Test(groups = "Regression", dataProvider = "newAcountDetailsData", dataProviderClass = DataProviders.class)
    public void createAccountTest(HashMap<String, String> hashMapValue) throws Throwable {
        Log.startTestCase("createAccountTest");
        indexPage = new HomePage();
        loginPage = indexPage.clickOnSignIn();
        accountPage = loginPage.createNewAccount(hashMapValue.get("Name"), hashMapValue.get("Email"));
        Thread.sleep(2000);

        // SoftAssert: Non-critical checks
        SoftAssert softAssert = new SoftAssert();

        accountPage.createAccount(
            hashMapValue.get("Gender"),
            hashMapValue.get("FirstName"),
            hashMapValue.get("SetEmail"),
            hashMapValue.get("SetPassword"),
            hashMapValue.get("Day"),
            hashMapValue.get("Month"),
            hashMapValue.get("Year"),
            hashMapValue.get("CustomerName"),
            hashMapValue.get("LastName"),
            hashMapValue.get("Company"),
            hashMapValue.get("Address"),
            hashMapValue.get("City"),
            hashMapValue.get("State"),
            hashMapValue.get("Zipcode"),
            hashMapValue.get("Country"),
            hashMapValue.get("MobilePhone")
        );

        indexPage = accountPage.validateRegistration();
        String actualURL = indexPage.getCurrURL();
        String expectedURL = "https://automationexercise.com/account_created";
        Log.info("Actual URL: " + actualURL);
        Log.info("Expected URL: " + expectedURL);
        
        // HardAssert: Critical check, stop test if this fails
        Assert.assertEquals(actualURL, expectedURL, "Account creation verification failed");

        // Call assertAll to report any soft assertion failures
        softAssert.assertAll();

        Log.endTestCase("createAccountTest");
    }
}
