package com.test.Pages;

import com.test.Utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends BasePage{

    public DashBoardPage(){

        PageFactory.initElements(Driver.get(), this);

    }

}
