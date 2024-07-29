package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class Product_Page extends BaseClass {
	Action action= new Action();
	@FindBy(xpath="//input[@placeholder='Search Product']")
	public WebElement searchProductBox;
	@FindBy(id="submit_search")
	public WebElement searchButton;
	public Product_Page() {
		PageFactory.initElements(getDriver(), this);
	}
	public SearchResultPage searchProduct(String productName) throws Throwable {
		Action.explicitWait(getDriver(), searchProductBox, 700 );
		Action.scrollByVisibilityOfElement(getDriver(), searchProductBox);
		
		Action.type(searchProductBox, productName);
		Action.click(getDriver(), searchButton);
		
		return new SearchResultPage();
	}
	public String getCurrURL()
	{
		String homePageURL=getDriver().getCurrentUrl();
		return homePageURL;
	}
}
