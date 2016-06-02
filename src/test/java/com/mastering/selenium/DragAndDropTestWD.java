package com.mastering.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by tcbinh on 1/18/2016.
 */
public class DragAndDropTestWD extends DriverFactory{
    @Test(enabled = false)
    public void automateJavaScriptDragAndDropWithOffsetsStep5() throws Exception {
        WebDriver driver = getDriver();

        driver.get("C:\\Users\\tcbinh\\Desktop\\jsDragAndDropWithHandle.html");
        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));
        WebElement firstBox = driver.findElement(By.id("one"));
        WebElement firstBoxText = driver.findElement(By.cssSelector("#one > span"));

        Assert.assertEquals(driver.findElements(destroyableBoxes).size(),5);

        CalculateOffsetPosition op = new CalculateOffsetPosition(firstBox, firstBoxText, CalculateOffsetPosition.CursorPosition.CENTER);

        advancedActions.moveToElement(firstBox)
                .moveByOffset(op.getXOffset(), op.getYOffset())
                .clickAndHold()
                .moveToElement(obliterator)
                .release()
                .perform();

        Assert.assertEquals(driver.findElements(destroyableBoxes).size(),4);
    }
}
