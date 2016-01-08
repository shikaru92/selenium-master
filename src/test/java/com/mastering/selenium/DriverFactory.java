package com.mastering.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tcbinh on 1/7/2016.
 */
public class DriverFactory {

    private static List<WebdriverThread> webdriverThreadPool = Collections.synchronizedList(new ArrayList<WebdriverThread>());
    private static ThreadLocal<WebdriverThread> driverThread;

    @BeforeSuite
    public static void instantiateDriverObject()
    {
        driverThread = new ThreadLocal<WebdriverThread>(){
            @Override
            protected WebdriverThread initialValue()
            {
                WebdriverThread webdriverThread = new WebdriverThread();
                webdriverThreadPool.add(webdriverThread);
                return webdriverThread;
            }
        };
    }

    public static WebDriver getDriver()
    {
        return driverThread.get().getDriver();
    }

    @AfterMethod
    public static void clearCookies()
    {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public static void closeDriverObject()
    {
        for (WebdriverThread webdriverThread : webdriverThreadPool)
        {
            webdriverThread.quitDriver();
        }
    }
}