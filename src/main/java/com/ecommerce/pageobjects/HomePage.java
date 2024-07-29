package com.ecommerce.pageobjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import com.ecommerce.utility.Log;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HomePage extends BaseClass{

@FindBy(xpath = "//a[@href='/login']") 
private WebElement signInBtn;
@FindBy(xpath = "//a[@href='/products']") 
private WebElement products;
@FindBy(xpath = "//a[@href='/product_details/1']") 
private WebElement eachProduct;

@FindBy(xpath = "//img[@alt='Website for automation practice']")
private WebElement myStoreLogo;

@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
private WebElement myCart;

@FindBy(xpath = "//a[text()='View Product']")
private WebElement myViewPro;

public HomePage() {
	PageFactory.initElements(getDriver(), this);
}
public LoginPage clickOnSignIn() throws Throwable {
	Action.explicitWait(getDriver(), signInBtn, 100 );
	Action.click(getDriver(), signInBtn);
	return new LoginPage();
}
public boolean validateLogo() throws Throwable {
	Action.explicitWait(getDriver(), myStoreLogo, 100 );
	return Action.isDisplayed(getDriver(), myStoreLogo);
}
public boolean validateCart() throws Throwable {
	Action.explicitWait(getDriver(), myCart, 100 );
	return Action.isDisplayed(getDriver(), myCart);
}
public String getCurrURL()
{
	String homePageURL=getDriver().getCurrentUrl();
	return homePageURL;
}
public boolean validateViewPro() throws Throwable {
	return Action.isDisplayed(getDriver(), myViewPro);
}
public String getMyStoreTitle() {
	String ecommerceTitel=getDriver().getTitle();
	return ecommerceTitel;
}




public Product_Page clickOnProduct() throws Throwable {
    Log.info("Attempting to click on the product");
    
    Action.fluentWait(getDriver(), products, 10);
    Action.click(getDriver(), products);
    Log.info("Clicked on the product element");

    // Wait for the URL to change or the expected page to load
    Thread.sleep(5000); // Adjust the time as needed

    // Check if the URL contains the fragment and remove it
    String currentURL = getCurrURL();
    Log.info("Current URL before removing fragment: " + currentURL);
    if (currentURL.contains("#google_vignette")) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.location.hash = ''");
        Thread.sleep(1000); // Give the browser some time to update the URL
        Log.info("Fragment #google_vignette removed from URL");
    }

    // Verify if the URL is correctly navigated to /products
    currentURL = getCurrURL();
    Log.info("Current URL after removing fragment: " + currentURL);

    if (!currentURL.contains("/products")) {
        Log.warn("Expected to navigate to /products, but current URL is: " + currentURL);
        // Retry clicking on the product if necessary
        Action.click(getDriver(), products);
        Thread.sleep(5000); // Adjust the time as needed
        currentURL = getCurrURL();
        Log.info("Current URL after retrying click: " + currentURL);
    }
    return new Product_Page();
}

public AddToCartPage addToCartProduct() throws Throwable {
	 Action.scrollByVisibilityOfElement(getDriver(), eachProduct);
	Action.fluentWait(getDriver(), eachProduct, 10);
    Action.click(getDriver(), eachProduct);
    Thread.sleep(2000);
    String currentURL = getDriver().getCurrentUrl();
    if (currentURL.contains("#google_vignette")) {
        String cleanURL = currentURL.replace("#google_vignette", "");
        getDriver().get(cleanURL);
    }
    
    return new AddToCartPage();
}
}