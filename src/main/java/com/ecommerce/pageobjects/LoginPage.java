package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class LoginPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(xpath="//input[@data-qa='login-email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@data-qa='login-password']")
	private WebElement password;

	@FindBy(xpath="//button[@data-qa='login-button']")
	private WebElement signInBtn;
	
	@FindBy(xpath="//input[@data-qa='signup-name']")
	private WebElement nameForNewAccount;
	
	@FindBy(xpath="//input[@data-qa='signup-email']")
	private WebElement emailForNewAccount;
	
	@FindBy(xpath="//button[@data-qa='signup-button']")
	private WebElement createNewAccountBtn;
	
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	public HomePage login(String email_log, String pass_log)
	{
		Action.type(email, email_log);
		Action.type(password, pass_log);
		Action.click(getDriver(), signInBtn);
		return new HomePage();
	}
	public AccountCreationPage createNewAccount(String new_name, String new_email)
	{
		Action.type(nameForNewAccount, new_name);
		Action.type(emailForNewAccount, new_email);
		Action.click(getDriver(), createNewAccountBtn);
		return new AccountCreationPage();
	}
	
}