package com.mastering.selenium.test;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mastering.selenium.DriverFactory;

public class cssMenuTestWD extends DriverFactory{
	
	@Test
	public void automateCSSMenu() throws MalformedURLException
	{
		WebDriver driver = getDriver();
		driver.get("C:\\Users\\Administrator\\Desktop\\Mastering Selenium WebDriver\\1784394351\\Mastering Selenium WebDriver\\Chapter 6\\src\\main\\webapp\\cssMenu.html");
		Actions advanceActions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 5, 100);
		
		WebElement serviceMenuOption = driver.findElement(By.id("services"));
		WebElement webDevelopmentSubMenuOption = driver.findElement(By.xpath("//li[@id='services']/ul/li[2]"));
		
		advanceActions.moveToElement(serviceMenuOption).perform();
		wait.until(ExpectedConditions.visibilityOf(webDevelopmentSubMenuOption));
		advanceActions.moveToElement(webDevelopmentSubMenuOption).click().perform();
		
	}

}
