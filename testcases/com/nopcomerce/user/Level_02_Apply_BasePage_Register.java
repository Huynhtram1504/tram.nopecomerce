package com.nopcomerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Level_02_Apply_BasePage_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	BasePage basePage;
	
  @BeforeClass
	  public void beforeClass() {
	  	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	  	driver = new FirefoxDriver();
	  	basePage = new BasePage();
	  	emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
	  	
	  	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  	driver.get("https://demo.nopcommerce.com/");
	  
	  }
	
  @Test
  public void TC_01_Register_Empty_Data() {
	 
	  //1. Click to Register
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	 
	  //2. Click on Register button
	  basePage.clickToElement(driver, "//button[@id='register-button']");

	  //Verify message
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	 
	  
  }
  
  @Test
  public void TC_02_Register_Invalid_Email() {
	 
	  //1. Click to Register
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  //2. Input invalid info and email
	  basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendKeyToElement(driver, "//input[@id='Email']", "123@#456");
	  basePage.sendKeyToElement(driver, "//input[@id='Password']", "Duyanh030");
	  basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Duyanh030");
	 
	  //3. Click to Register
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //4.Verify message
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	  
  }
  
  @Test
  public void TC_03_Register_Success() {
	  
	  //1. Click to Register
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  //2. Input valid info
	  basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendKeyToElement(driver, "//input[@id='Password']", "Duyanh030");
	  basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Duyanh030");
	  
	  //3. Click to Register
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //4. Verify message
	  Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  
//	  //5. Logout
//	  basePage.clickToElement(driver, "//a[@class='ico-logout']");
	
  }
 
  @Test
  public void TC_04_Register_Existing_Email() {
	  
	  //1. Click to Register
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  //2. Input valid info
	  basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendKeyToElement(driver, "//input[@id='Password']", "Duyanh030");
	  basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Duyanh030");
	  
	  //3. Click to Register
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //4. Verify message
	  Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']/ul/li"), "The specified email already exists");
	  
	  
  }
  
  @Test
  public void TC_05_Register_Password_Less_Than_6_Chars() {
	  
	  //1. Chọn to Register
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  //2. Input password less than 6 chars
	  basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendKeyToElement(driver, "//input[@id='Password']", "Duy");
	  basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Duyanh030");
	 
	  //3. Click to Register
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //4. Verify message
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	 
  }
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  
	  //1. Chọn to Register
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  //2. Input invalid confirm password
	  basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
	  basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendKeyToElement(driver, "//input[@id='Password']", "Duyanh030");
	  basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "duyanh030");
	 
	  //3. Click to Register
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  //4. Verify message
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	  
  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int generateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
	  
	  
  }
  
  

}
