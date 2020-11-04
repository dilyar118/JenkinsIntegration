package com.test.Pages;

import com.test.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    public LoginPage() {

        PageFactory.initElements(Driver.get(), this);

    }
        @FindBy(xpath="//input[@type='text']")
        @CacheLookup
        WebElement userName;

        @FindBy(xpath="//input[@type='password']")
        @CacheLookup
        WebElement password;


        @FindBy(xpath="//button[text()='Login']")
        @CacheLookup
        WebElement submit;


        public void login(String userNameStr, String passwordStr){

            userName.sendKeys(userNameStr);
            password.sendKeys(passwordStr);
            submit.click();


        }

}
