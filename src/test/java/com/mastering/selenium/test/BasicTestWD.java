package com.mastering.selenium.test;

import java.net.MalformedURLException;

import com.mastering.selenium.pageobject.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mastering.selenium.DriverFactory;

/**
 * Created by tcbinh on 1/7/2016.
 */
public class BasicTestWD extends DriverFactory{
    private void googleExample(final String searchString) throws MalformedURLException {
        //add comment bbb
        final WebDriver driver = DriverFactory.getDriver();
        driver.get("http://google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys(searchString);
        System.out.println("Page title is: " + driver.getTitle());
        searchField.submit();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
            }
        });
        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test(groups={"test_group"})
    public void googleCheeseExample() throws MalformedURLException {
        googleExample("Cheese!");
    }

    @Test(groups={"test_group"})
    public void googleMilkExample() throws MalformedURLException {
        googleExample("Milk!");
    }
}
