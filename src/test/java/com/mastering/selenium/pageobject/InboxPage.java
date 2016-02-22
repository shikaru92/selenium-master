package com.mastering.selenium.pageobject;

import com.mastering.selenium.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by tcbinh on 2/19/2016.
 */
public class InboxPage {
    @FindBy(xpath = "//div[@class='y6']/span[contains(.,'IBM Certified Solution Designer')]")
    private WebElement emailTitleLink;

    public InboxPage() throws Exception {
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    public WebElement getEmailTitleLink()
    {
        return emailTitleLink;
    }

    public InboxPage clickEmailTitleLink()
    {
        emailTitleLink.click();
        return this;
    }
}
