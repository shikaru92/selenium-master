package com.mastering.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by tcbinh on 1/18/2016.
 */
public class IframeTestWD extends DriverFactory{

    @Test
    public void getSourceOfIFrame() throws MalformedURLException {
        WebDriver driver = getDriver();

        driver.get("http://suckhoe.vnexpress.net/");

        WebDriverWait wait = new WebDriverWait(driver,10,500);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));

        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
        int i = 0;
        PrintWriter printWriter = null;
        for(WebElement iframe : iframeList) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

            try {
                printWriter = new PrintWriter("D:\\devtools\\pageSource"+i+".txt");
                printWriter.write(driver.getPageSource());
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            i++;
            driver.switchTo().defaultContent();
        }



    }
}
