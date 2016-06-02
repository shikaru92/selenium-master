package com.mastering.selenium.test;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mastering.selenium.DriverFactory;

public class dragAndDropTestWD extends DriverFactory{
	
	@Test(enabled = false)
	public void dragAndDropTest() throws MalformedURLException
	{
		WebDriver driver = DriverFactory.getDriver();
		driver.get("C:\\Users\\Administrator\\Desktop\\Mastering Selenium WebDriver\\1784394351\\Mastering Selenium WebDriver\\Chapter 6\\src\\main\\webapp\\jsDragAndDropWithHandle.html");
		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement secondBox = driver.findElement(By.id("two"));
		
		Assert.assertEquals(driver.findElements(destroyableBoxes).size(), 5);
		
		advancedActions.clickAndHold(firstBox).moveToElement(obliterator).release().perform();
		Assert.assertEquals(driver.findElements(destroyableBoxes).size(), 4);
		
		advancedActions.dragAndDrop(secondBox, obliterator).perform();
		Assert.assertEquals(driver.findElements(destroyableBoxes).size(), 3);

	}

}
