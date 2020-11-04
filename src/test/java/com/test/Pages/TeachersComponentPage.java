package com.test.Pages;

import com.test.Utilities.Driver;
import org.jsoup.Connection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TeachersComponentPage extends BasePage {


    public TeachersComponentPage(){
        PageFactory.initElements(Driver.get(), this);
    }


    @FindBy(xpath = "//div[@class='profile-widget']/h4/a")
    public List<WebElement> teachearsName;

    @FindBy(xpath = "//div[@class='small text-muted']")
    public List<WebElement> titleName;






}
