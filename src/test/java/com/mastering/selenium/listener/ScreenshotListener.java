package com.mastering.selenium.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.mastering.selenium.DriverFactory;

public class ScreenshotListener extends TestListenerAdapter{
	
	private Boolean createFile(File screenshot) throws IOException
	{
		Boolean fileCreate = Boolean.FALSE;
		if (screenshot.exists())
		{
			fileCreate = Boolean.TRUE;
		}
		else
		{
			File parentDirectory = new File(screenshot.getParent());
			if (parentDirectory.exists() || parentDirectory.mkdirs())
			{
				fileCreate = screenshot.createNewFile();
			}
			
		}
		return fileCreate;
	}
	
	private void writeScreenShotToFile(WebDriver driver, File screenshot) throws WebDriverException, IOException
	{
		FileOutputStream screenshotStream = new FileOutputStream(screenshot);
		screenshotStream.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
		screenshotStream.close();
	}
	
	@Override
	public void onTestFailure(ITestResult failingTest) {
		try {
			WebDriver driver = DriverFactory.getDriver();
			String screenshotDirectory = System.getProperty("screenshotDirectory");
			if (screenshotDirectory == null)
			{
				screenshotDirectory = "errorscreenshot";
			}
			String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_"
					+ failingTest.getName() + ".png";
			File screenshot = new File(screenshotAbsolutePath);
			if (createFile(screenshot)) {
				try {
					writeScreenShotToFile(driver, screenshot);
				} catch (ClassCastException weNeedToAugmentOurDriverObject) {
					writeScreenShotToFile(new Augmenter().augment(driver), screenshot);
				}
				System.out.println("Written screenshot to " + screenshotAbsolutePath);
			} else {
				System.err.println("Unable to create " + screenshotAbsolutePath);
			}
		} catch (Exception e) {
			System.err.println("Unable to capture screenshot...");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
