package com.mastering.selenium.test;

import com.mastering.selenium.DriverFactory;
import com.mastering.selenium.pageobject.InboxPage;
import com.mastering.selenium.pageobject.LoginPage;
import com.mastering.selenium.utils.Predicates;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

/**
 * Created by tcbinh on 2/4/2016.
 */
public class TestLoginGoogle extends DriverFactory{
    private LoginPage loginPage;
    private InboxPage inboxPage;
    private WebDriver driver;

    @BeforeMethod
    public void init() throws Exception {
        driver = DriverFactory.getDriver();
        driver.get("http://gmail.com");
        loginPage = new LoginPage();
        inboxPage = new InboxPage();
    }

    @Test
    public void testLoginGmail() throws Exception {
        loginPage.enterEmail("thienlong1234@gmail.com")
                .clickNext()
                .waitingForElementIsVisible(loginPage.getStaySignIn());
        loginPage.uncheckStaySignIn()
                .enterPassword("solutionpro10101992")
                .clickSignIn();
    }

    @Test
    public void testLoginGmail2() throws Exception {
        loginPage.enterEmail("").clickNext();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessageEmail()));
        Assert.assertTrue(loginPage.getErrorMessageEmail().isDisplayed());
    }

    @Test
    public void testLoginGmail3() throws Exception {
        loginPage.enterEmail("thienlong1234@gmail.com")
                .clickNext()
                .enterPassword("")
                .uncheckStaySignIn()
                .clickSignIn()
                .waitingForElementIsVisible(loginPage.getErrorMessagePassword());
        Assert.assertTrue(loginPage.getErrorMessagePassword().isDisplayed());
    }

    @Test
    public void testClickEmailTitle() throws Exception
    {
        loginPage.enterEmail("thienlong1234@gmail.com")
                .clickNext()
                .waitingForElementIsVisible(loginPage.getStaySignIn());
        loginPage.uncheckStaySignIn()
                .enterPassword("solutionpro10101992")
                .clickSignIn();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(inboxPage.getEmailTitleLink()));
        inboxPage.clickEmailTitleLink();
    }
}
