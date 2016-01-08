package com.mastering.selenium;

import com.mastering.selenium.config.DriverType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
    private final boolean useRemoteWebdriver = Boolean.getBoolean("remoteDriver");

    public WebDriver getDriver() throws MalformedURLException {
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

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");

        if (useRemoteWebdriver)
        {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty())
            {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }
            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty())
            {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }
            driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        }
        else
        {
            driver = selectedDriverType.getWebDriverObject(desiredCapabilities);
        }
    }

}
