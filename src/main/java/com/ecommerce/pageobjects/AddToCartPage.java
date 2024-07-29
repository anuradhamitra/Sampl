package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;


public class AddToCartPage extends BaseClass {
Action action= new Action();
	
	@FindBy(id="quantity")
	private WebElement quantity;
	
	
	@FindBy(xpath="//button[@type='button' and contains(@class, 'btn btn-default cart')]")
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//div[@id='cartModal']//h4[contains(text(), 'Added!')]")
	private WebElement addToCartMessag;
	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckOutBtn;
	
	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}
	public void enterQuantity(String quantity1) throws Throwable {
		Action.explicitWait(getDriver(), quantity, 2000 );
		Action.type(quantity, quantity1);
	}
	public void clickOnAddCart() throws Throwable {
		
		Action.click(getDriver(), addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		Action.fluentWait(getDriver(), addToCartMessag, 10);
		return Action.isDisplayed(getDriver(), addToCartMessag);
	}
	
	
	
}
