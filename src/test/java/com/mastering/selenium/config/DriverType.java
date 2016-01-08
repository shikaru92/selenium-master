package com.mastering.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by tcbinh on 1/7/2016.
 */
public enum DriverType implements DriverSetup{

    FIREFOX {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new FirefoxDriver(desiredCapabilities);
        }

        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            return capabilities;
        }
    },

    CHROME {
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            SetupChrome setupChrome = new SetupChrome();
            return setupChrome.driver();
        }

        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));

            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            return capabilities;
        }
    },
}
