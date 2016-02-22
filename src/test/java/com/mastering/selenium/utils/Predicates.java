package com.mastering.selenium.utils;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by tcbinh on 2/3/2016.
 */
public class Predicates {
    public static Predicate<WebDriver> elementIsLoaded(final WebElement element) {
        return new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                return element.getAttribute("class").contains("field-valid");
            }
        };
    }
}
