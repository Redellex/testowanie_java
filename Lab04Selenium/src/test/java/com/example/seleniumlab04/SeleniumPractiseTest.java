package com.example.seleniumlab04;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumPractiseTest
{
	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
		System.setProperty("webdriver.chrome.driver", "/home/studinf/psobocinski/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	@Test
	public void homePage(){
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		element = driver.findElement(By.linkText("This is a link"));
		assertNotNull(element);
	}
	
	public void checkLink()
	{
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		driver.findElement(By.linkText("This is a link")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		assertEquals(driver.getCurrentUrl(), "http://www.seleniumframework.com/");
	}
	
	@Test
	public void checkBoxTest()
	{
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		element = driver.findElement(By.cssSelector("#vfb-6-0"));
		element.click();
		assertTrue(element.isSelected());
	}
	
	@Test
	public void checkFailureVerification()
	{
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		driver.findElement(By.id("vfb-3")).sendKeys("13");
		driver.findElement(By.id("vfb-4")).click();
		element = driver.findElement(By.xpath("//*[@id=\"form_success\"]"));
		assertNotNull(element);
	}
	
	@Test
	public void checkSelect()
	{
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"vfb-12\"]")));
		dropdown.selectByVisibleText("Option 3");
		List<WebElement> options = dropdown.getOptions();
		assertEquals(dropdown.getFirstSelectedOption(), options.get(2));
	}
	@Test
	public void checkDragAndDrop()
	{
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		// Configure the action
		Actions builder = new Actions(driver);
		WebElement dragFrom = driver.findElement(By.xpath("//*[@id=\"draga\"]")); 
		WebElement dragTo = driver.findElement(By.xpath("//*[@id=\"dragb\"]")); 
		
		builder.keyDown(Keys.CONTROL)
		   .click(dragFrom)
		   .click(dragTo)
		   .keyUp(Keys.CONTROL);

		// Then get the action:
		Action selectMultiple = builder.build();

		// And execute it:
		selectMultiple.perform();   
	}
	
	@Test
	public void checkAlert()
	{
		driver.get("http://www.seleniumframework.com/Practiceform/");
		
		driver.findElement(By.xpath("//*[@id=\"alert\"]")).click();
		Alert alert = driver.switchTo().alert();
		assertNotNull(alert);
		alert.dismiss();
	}
	
	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}