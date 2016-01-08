package com.mastering.selenium;

import com.mastering.selenium.config.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by tcbinh on 1/7/2016.
 */
public class WebdriverThread {
    private WebDriver driver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = DriverType.FIREFOX;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final String browser = System.getProperty("browser").toUpperCase();

    public WebDriver getDriver()
    {
        if (null == driver) {
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }
        return driver;
    }

    public void quitDriver()
    {
        if (null != driver)
        {
            driver.quit();
            driver = null;
        }
    }

    private DriverType determineEffectiveDriverType()
    {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        return driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities)
    {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");
        driver = selectedDriverType.getWebDriverObject(desiredCapabilities);
    }

}
