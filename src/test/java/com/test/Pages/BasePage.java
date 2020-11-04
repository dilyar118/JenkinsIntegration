package com.test.Pages;

import com.test.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){

        PageFactory.initElements(Driver.get(), this);
    }


    @FindBy(xpath="(//div[@class='dash-widget-info']/h3)[1]")
    @CacheLookup
    WebElement studentCount;

    @FindBy(xpath="(//div[@class='dash-widget-info']/span)[1]")
    @CacheLookup
    WebElement student;

    @FindBy(xpath="(//div[@class='dash-widget-info']/h3)[2]")
    @CacheLookup
    WebElement teacherCount;

    @FindBy(xpath="(//div[@class='dash-widget-info']/span)[2]")
    @CacheLookup
    WebElement teacher;

    @FindBy(xpath="(//div[@class='dash-widget-info']/h3)[3]")
    @CacheLookup
    WebElement parentCount;

    @FindBy(xpath="(//div[@class='dash-widget-info']/span)[3]")
    @CacheLookup
    WebElement parent;

    @FindBy(xpath="(//div[@class='dash-widget-info']/h3)[4]")
    @CacheLookup
    WebElement totalEarning;

    @FindBy(xpath="(//div[@class='dash-widget-info']/span)[4]")
    @CacheLookup
    WebElement ernings;

    @FindBy(xpath="(//li[@class='submenu']/a/span)[1]")
    @CacheLookup
    public  WebElement teacherComp;

    @FindBy(xpath = "(//ul[@class='list-unstyled']/li/a)[1]")
    @CacheLookup
    public  WebElement allTeachers;

    public void student_info() {
        String stuCount = studentCount.getText().toString();
        String stuName = student.getText().toString();
        System.out.println("The " + stuName + "total count is:  " + stuCount);
    }

    public void teacher_info() {
        String teaCount = teacherCount.getText().toString();
        String teaName = teacher.getText().toString();
        System.out.println("The " + teaName + "total count is:  " + teaCount);
    }

    public void parent_info() {
        String parCount = parentCount.getText().toString();
        String parName = parent.getText().toString();
        System.out.println("The " + parName + "total count is: " + parCount);
    }


    public void earning_info() {
        String earn = ernings.getText().toString();
        String totalEarn = totalEarning.getText().toString();
        System.out.println("The " + earn + " total earning is: " + totalEarn );

    }
}
