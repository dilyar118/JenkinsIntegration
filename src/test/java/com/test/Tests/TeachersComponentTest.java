package com.test.Tests;

import com.test.Pages.DashBoardPage;
import com.test.Pages.LoginPage;
import com.test.Pages.TeachersComponentPage;
import com.test.Utilities.BrowserUtilis;
import com.test.Utilities.ConfigurationReader;
import com.test.Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TeachersComponentTest {

    WebDriver driver;
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    TeachersComponentPage teachersComponentPage = new TeachersComponentPage();

    @BeforeMethod
    public void setUp(){

        this.driver= Driver.get();
        String url = ConfigurationReader.get("url");
        driver.get(url);
        String accualTitle = driver.getTitle();
        Assert.assertEquals(accualTitle, "Preschool - Bootstrap Admin Template");
        BrowserUtilis.waitFor(2);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        String username = ConfigurationReader.get("userName");
        String pass = ConfigurationReader.get(("password"));
        loginPage.login(username, pass);
        String pageTtitle = driver.getTitle();
        Assert.assertEquals(pageTtitle, "Preschool - Admin Account");

    }



    @Test
    public void componentTest(){

        dashBoardPage.teacherComp.click();
        dashBoardPage.allTeachers.click();
        String actualTit = driver.getTitle();
        Assert.assertEquals(actualTit, "School - Admin");


        int size = teachersComponentPage.teachearsName.size();
        System.out.println("The table size is\t"+size);
        for(int row = 1; row <= size;  row++ ){

            String teachName = driver.findElement(By.xpath("(//div[@class='profile-widget']/h4/a)[" + row +"]")).getText();

            String titleName = driver.findElement(By.xpath("(//div[@class='small text-muted'])[" + row + "]")).getText();

            System.out.println(row + "The Teachers Name is:\t" + teachName + "\tand The Title Name is:\t" + titleName + "\t" + row);


        }



    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
