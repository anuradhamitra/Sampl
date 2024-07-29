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
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.pageobjects.Product_Page;
import com.ecommerce.pageobjects.SearchResultPage;
import com.ecommerce.utility.Log;
public class EcommerceEToE extends BaseClass {
    private SearchResultPage searchPage;
    private HomePage indexPage;
    private Product_Page productPage;
    private AddToCartPage cartPage;
    private LoginPage loginPage;
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true, groups= {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        Log.info("Setting up test with browser: {}"+ browser);
        launchApp(browser);}
    @AfterMethod(alwaysRun = true,groups= {"Smoke","Sanity","Regression"})
    public void tearDown() {
        Log.info("Tearing down the test");
        getDriver().quit();
        Log.info("Driver quit successfully");}
    @Test(dataProvider="credentials", dataProviderClass = DataProviders.class, priority = 1, enabled = true, groups="Smoke")
    public void signIn(String Email, String Password) throws Throwable {
        Log.info("Starting SignInTest with Email: {} and Password: {}"+ Email+ Password);
        Log.startTestCase("SignInTest");
        Log.info("User is going to click on sign-in");
        indexPage = new HomePage();
        loginPage = indexPage.clickOnSignIn();
        Log.info("Entering username and password");
        indexPage = loginPage.login(Email, Password); // Using DataProvider
        Log.endTestCase("SignInTest executed successfully");
        Log.info("SignInTest completed");}
    @Test(dependsOnMethods = "signIn", priority = 2, enabled = true, groups="Smoke")
    public void verifyLogin() {
        Log.info("Starting VerifyLoginTest");
        Log.startTestCase("VerifyLoginTest");
        String actualURL = indexPage.getCurrURL();
        String expectedURL = "https://automationexercise.com/";
        Log.info("Verifying whether the user is able to login. Actual URL: , Expected URL: "+ actualURL+ expectedURL);
        Assert.assertEquals(actualURL, expectedURL);
        Log.endTestCase("VerifyLoginTest executed successfully");
        Log.info("VerifyLoginTest completed");}
    @Test(dataProvider="searchProduct", dataProviderClass = DataProviders.class, dependsOnMethods = "verifyLogin", priority = 3, enabled = true, groups="Smoke")
    public void searchProduct(String Product) throws Throwable {
        Log.info("Starting SearchProductTest");
        Log.startTestCase("SearchProductTest");
        indexPage = new HomePage();
        productPage = indexPage.clickOnProduct();
        searchPage = productPage.searchProduct(Product);
        boolean isAvailable = searchPage.isProductAvailable();
        Log.info("Product availability for 'Blue Top': "+ isAvailable);
        Assert.assertTrue(isAvailable);
        Log.endTestCase("SearchProductTest executed successfully");
        Log.info("SearchProductTest completed");}
    @Test(groups="Regression")
    public void addToCart() throws Throwable {
        Log.info("Starting AddToCartTest");
        Log.startTestCase("AddToCartTest");
        indexPage = new HomePage();
        cartPage = indexPage.addToCartProduct();
             Log.info("Entering quantity: 3");
        cartPage.enterQuantity("3");
               Log.info("Clicking on Add to Cart");
        cartPage.clickOnAddCart();
              boolean isPresent = cartPage.validateAddtoCart();
        Log.info("Product added to cart validation result: "+ isPresent);     
        Assert.assertTrue(isPresent);
        Log.info("Product added to cart successfully");
        Log.endTestCase("AddToCartTest executed successfully");
        Log.info("AddToCartTest completed");}}