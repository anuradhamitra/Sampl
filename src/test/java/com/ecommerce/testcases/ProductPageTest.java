package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseClass;
import com.ecommerce.dataprovider.DataProviders;
import com.ecommerce.pageobjects.AddToCartPage;
import com.ecommerce.pageobjects.HomePage;

import com.ecommerce.pageobjects.Product_Page;
import com.ecommerce.pageobjects.SearchResultPage;
import com.ecommerce.utility.Log;

public class ProductPageTest extends BaseClass {
    private SearchResultPage searchPage;
    private HomePage indexPage;
    private Product_Page productPage;
    private AddToCartPage cartPage;

    @Parameters("browser")
    @BeforeMethod(groups= {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        Log.info("Setting up test with browser: " + browser);
        launchApp(browser);
    }

    @AfterMethod(groups= {"Smoke","Sanity","Regression"})
    public void tearDown() {
        Log.info("Tearing down the test");
        getDriver().quit();
        Log.info("Driver quit successfully");
    }

   @Test(dataProvider="searchProduct", dataProviderClass = DataProviders.class)
    public void productAvailable(String Product) throws Throwable {
        Log.startTestCase("productAvailable");
        
        indexPage = new HomePage();
        Log.info("Navigated to HomePage");
        
        productPage = indexPage.clickOnProduct();
        Log.info("Clicked on Product page");
        
        searchPage = productPage.searchProduct(Product);
        Log.info("Searched for product: Blue Top");

        boolean isAvailable = searchPage.isProductAvailable();
        Log.info("Product availability: " + isAvailable);
        Assert.assertTrue(isAvailable);
        
        cartPage = searchPage.clickOnProduct();
        Log.info("Clicked on the product");

        cartPage.enterQuantity("3");
        Log.info("Entered quantity: 3");
        
        cartPage.clickOnAddCart();
        Log.info("Clicked on Add to Cart");

        boolean isPresent = cartPage.validateAddtoCart();
        Log.info("Product added to cart validation result: " + isPresent);
        Assert.assertTrue(isPresent);
        
        Log.endTestCase("productAvailable");
    }
}
