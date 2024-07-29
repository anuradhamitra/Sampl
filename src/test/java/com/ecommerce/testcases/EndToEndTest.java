package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ecommerce.base.BaseClass;
import com.ecommerce.pageobjects.AddToCartPage;
import com.ecommerce.pageobjects.HomePage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.pageobjects.Product_Page;
import com.ecommerce.pageobjects.SearchResultPage;
import com.ecommerce.utility.Log;

public class EndToEndTest extends BaseClass {
	private SearchResultPage searchPage;
	private HomePage indexPage;
	private Product_Page productPage;
	private AddToCartPage cartPage;
	private LoginPage loginPage;
	  @Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	@Test(groups= {"Regression"})
	public void productAvailable() throws Throwable
	{
		Log.startTestCase("SigInTest");
		indexPage= new HomePage();
		Log.info("user is going to click on signin");
		loginPage=indexPage.clickOnSignIn();
		Log.info("enter username and password");
		indexPage=loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
	    String actualURL=indexPage.getCurrURL();
	    String expectedURL="https://automationexercise.com/";
	    Log.info("Verifying whether user is able to login");
	    Assert.assertEquals(actualURL, expectedURL);
		productPage=indexPage.clickOnProduct();
		searchPage=productPage.searchProduct("Blue Top");
		boolean isAvailable=searchPage.isProductAvailable();
	    Assert.assertTrue(isAvailable);
	    cartPage=searchPage.clickOnProduct();
	    cartPage.enterQuantity("3");
	    cartPage.clickOnAddCart();
	    boolean isPresent=cartPage.validateAddtoCart();
	    Assert.assertTrue(isPresent);
	    Log.info("Product added to cart");
	    Log.endTestCase("Test Executed Successfully");
	}
}