package com.mastering.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by tcbinh on 12/31/2015.
 */
public class SetupChrome {
    private static String CHROME_DRIVER = "/chromedriver.exe";
    private WebDriver driver = setupChromeDriver();

    public WebDriver driver()
    {
        return driver;
    }

    public void close()
    {
        driver.quit();
    }

    private WebDriver setupChromeDriver()
    {
        String localFile = SetupChrome.class.getResource(CHROME_DRIVER).getFile();
        System.setProperty("webdriver.chrome.driver", localFile);
        return driver = new ChromeDriver();
    }
}
