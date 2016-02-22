package com.mastering.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by tcbinh on 1/18/2016.
 */
public class IframeTestWD extends DriverFactory {

    @Test
    public void getSourceOfIFrame() throws MalformedURLException, FileNotFoundException {
        WebDriver driver = getDriver();

        driver.get("http://suckhoe.vnexpress.net/");

        WebDriverWait wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        Map<String, String> iframeAndIncodeKey = new HashMap<String, String>();
        int i = 0;
        String flag = null;
        String frameHTMLCode = null;
        for (WebElement iframe : iframeList) {
            flag = "AA"+i+"AA";
            frameHTMLCode = iframe.getAttribute("outerHTML").replace("</iframe>", "");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            String pageSourceIncludeIframeCode = frameHTMLCode + "\n" + driver.getPageSource() + "\n" + "</iframe>";
            iframeAndIncodeKey.put(flag, pageSourceIncludeIframeCode);
            driver.switchTo().defaultContent();
            ((JavascriptExecutor) driver).executeScript("return $(arguments[0]).after(arguments[1]);",iframe,flag);
            ((JavascriptExecutor) driver).executeScript("return $(arguments[0]).remove();",iframe);
            i++;
        }
        String pageSourceRoot = driver.getPageSource();
        for (String key : iframeAndIncodeKey.keySet()) {
            pageSourceRoot = pageSourceRoot.replace(key, iframeAndIncodeKey.get(key));
        }
        PrintWriter printWriter = new PrintWriter("D:\\devtools\\pageSourceV2Final.html");
        printWriter.write(pageSourceRoot);
        printWriter.close();
    }

    @Test
    public void testHTML5() throws MalformedURLException {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://dev.goplay.la/account/register");

        WebElement username = driver.findElement(By.id("username"));
        WebElement country = driver.findElement(By.id("country-select_value"));

        username.clear();
        username.sendKeys("ABCSAHI");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        country.click();
//        wait.until(Predicates.inputIsValid(username));


        country.clear();
        country.sendKeys("Ame");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".angucomplete-title.ng-binding.ng-scope")));

        List<WebElement> countryDropdownList = driver.findElements(By.cssSelector(".angucomplete-title.ng-binding.ng-scope"));
        for (WebElement element : countryDropdownList)
        {
            if ("Cameroon".equals(element.getText()))
            {
                element.click();
                break;
            }
        }
    }


    public boolean isFieldValid(WebDriver driver, String idField){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (Boolean) js.executeScript("return document.getElementById('"+idField+"').validity.valid");
    }


}
