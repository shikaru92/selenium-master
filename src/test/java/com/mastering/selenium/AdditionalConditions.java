package com.mastering.selenium;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by tcbinh on 1/12/2016.
 */
public class AdditionalConditions {
    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted()
    {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor)driver).executeScript("return (window.jQuery!=null) && (jQuery.active === 0);");
            }
        };
    }

    public static ExpectedCondition<Boolean> angularHasFinishedProcessing()
    {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor)driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
            }
        };
    }
}
