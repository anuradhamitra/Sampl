package com.ecommerce.pageobjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class AccountCreationPage extends BaseClass {
	
	Action action= new Action();

	@FindBy(xpath = "//b[text()='Enter Account Information']")
	private WebElement formTitle;
	@FindBy(id = "id_gender1")
	private WebElement mr;
	
	@FindBy(id = "id_gender2")
	private WebElement mrs;

	@FindBy(xpath="//input[@data-qa='name']")
	private WebElement firstName;
	@FindBy(xpath="//input[@data-qa='email']")
	private WebElement email;

	@FindBy(xpath="//input[@data-qa='password']")
	private WebElement passWord;

	@FindBy(name = "days")
	private WebElement days;

	@FindBy(name = "months")
	private WebElement months;

	@FindBy(name = "years")
	private WebElement years;

	@FindBy(name = "first_name")
	private WebElement customerFirstName;

	@FindBy(name = "last_name")
	private WebElement customerLastName;

	@FindBy(name = "company")
	private WebElement companyName;

	@FindBy(name = "address1")
	private WebElement address;

	@FindBy(name = "city")
	private WebElement city;

	@FindBy(name = "state")
	private WebElement state;

	@FindBy(name = "zipcode")
	private WebElement postCode;

	@FindBy(name = "country")
	private WebElement country;

	@FindBy(name = "mobile_number")
	private WebElement mobile;

	@FindBy(xpath="//button[@data-qa='create-account']")
	private WebElement registerBtn;
	public AccountCreationPage() {
		PageFactory.initElements(getDriver(), this);
	}
	public void createAccount(String gender,String fName,
			String emailL, 
			String pswd, 
			String day, 
			String month, 
			String year,
			String customerfirstName, 
			String lName,
			String comPany, 
			String addr, 
			String countryName,
			String stateName, 
			String cityString, 
			String zip, 
			String mobilePhone) throws Throwable {	
		if(gender.equalsIgnoreCase("Mr")) {
			Action.click(getDriver(), mr);
		} else {
			Action.click(getDriver(), mrs);
		}
		Action.type(firstName, fName);
		Action.type(customerFirstName, customerfirstName);
		Action.type(email,emailL);
		Action.type(customerLastName, lName);
		Action.type(passWord, pswd);
		Action.selectByValue(days, day);
		Action.selectByValue(months, month);
		Action.selectByValue(years, year);
		Action.type(companyName, comPany);
		Action.type(address, addr);
		Action.selectByVisibleText(countryName, country);
		Action.type(state,stateName);
		Action.type(city, cityString);
		Action.type(postCode, zip);
		Action.type(mobile, mobilePhone);
	}
	public HomePage validateRegistration() throws Throwable {
		registerBtn.click();
		return new HomePage();
	}
	public boolean validateAcountCreatePage() throws Throwable {
		Action.explicitWait(getDriver(), formTitle, 100 );
		 return Action.isDisplayed(getDriver(), formTitle);
		 
	}
	public String getCurrURL()
	{
		String accountPageURL=getDriver().getCurrentUrl();
		return accountPageURL;
	}
}