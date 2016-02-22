package com.mastering.selenium.pageobject;

import com.mastering.selenium.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

/**
 * Created by tcbinh on 2/4/2016.
 */
public class AbstractPageObject extends DriverFactory{
    public void waitingForElementIsVisible(WebElement element) throws MalformedURLException {
        WebDriverWait wait = new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
