package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class SearchResultPage extends BaseClass {
Action action= new Action();
	
	@FindBy(xpath="//a[@href='/product_details/1']")
	private WebElement productResult;
	
	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean isProductAvailable() throws Throwable {
		Action.explicitWait(getDriver(), productResult, 100 );
		Action.scrollByVisibilityOfElement(getDriver(), productResult);
		return Action.isDisplayed(getDriver(), productResult);
	}
	
	public AddToCartPage clickOnProduct() throws Throwable {
		Action.explicitWait(getDriver(), productResult, 100 );
		Action.scrollByVisibilityOfElement(getDriver(), productResult);
		Action.click(getDriver(), productResult);
		return new AddToCartPage();
	}
	
}
