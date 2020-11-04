package com.test.Tests;

import com.test.Pages.LoginPage;
import com.test.Utilities.BrowserUtilis;
import com.test.Utilities.ConfigurationReader;
import com.test.Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage = new LoginPage();

    @BeforeMethod
    public void setUp(){

        String url = ConfigurationReader.get("url");
        Driver.get().manage().window().maximize();
        Driver.get().get(url);
        Driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Driver.get().manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        this.driver=Driver.get();

    }

    @Test
    public void login() throws InterruptedException {

        String username = ConfigurationReader.get("userName");
        String password = ConfigurationReader.get("password");
        loginPage.login(username, password);
        String accualTitle = driver.getTitle();
        System.out.println("The Home Page Title is : "+accualTitle);
        Assert.assertEquals(accualTitle, "Preschool - Admin Account");

    }

    @AfterMethod
    public void tearDown(){

        driver.quit();

    }

}
