package com.nopcomerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_DRY {
	//Là 1 class wrapper lại các function/method của Selenium WebDriver
	//Cách viết hàm:
	//1. Access modifier: public/protected/private/default
	//2. Kiểu dữ liệu của hàm (Data type): void/int/String/boolean/WebElement/List<WebElement>/...
	//3. Tên hàm: đặt có nghĩa theo tên hàm cần viết. Convention tuân thủ theo từng ngôn ngữ lập trình (Java - camel case)
	//4. Có tham số hay ko (tùy vào chức năng cần viết)
	//5. Kiểu dữ liệu trả về cho hàm: nếu như return thì nó là step cuối cùng
	
	//Tạm thời sẽ set public, qua các bài học qua tính kế thừa sẽ set lại
	//1 hàm chỉ xử lý 1 chức năng
	//Ko phải class nào cũng được khởi tạo Driver
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	
  @BeforeClass
	  public void beforeClass() {
	  	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	  	driver = new FirefoxDriver();
	  	emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
	  	
	  	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  	driver.get("https://demo.nopcommerce.com/");
	  
	  }
	
  @Test
  public void TC_01_Register_Empty_Data() {
	 
	  //1. Chọn Register
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  //2. Click vào nút Register
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  //Verify message
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
	  
  }
  
  @Test
  public void TC_02_Register_Invalid_Email() {
	 
	  //1. Chọn Register
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  //2. Nhập thông tin và email ko hợp lệ
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys("123@#456");
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("Duyanh030");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Duyanh030");
	  //3. Click vào nút Register
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  //Verify message
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
	  
  }
  
  @Test
  public void TC_03_Register_Success() {
	  
	  //1. Chọn Register
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  //2. Nhập thông tin đăng ký hợp lệ
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("Duyanh030");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Duyanh030");
	  //3. Click vào nút Register
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  //Verify message
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	  //Logout
	  driver.findElement(By.cssSelector("a.ico-logout")).click();
  }
 
  @Test
  public void TC_04_Register_Existing_Email() {
	  
	  //1. Chọn Register
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  //2. Nhập thông tin đăng ký hợp lệ
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("Duyanh030");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Duyanh030");
	  //3. Click vào nút Register
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  //Verify message
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']/ul/li")).getText(), "The specified email already exists");
	  
  }
  
  @Test
  public void TC_05_Register_Password_Less_Than_6_Chars() {
	  
	  //1. Chọn Register
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  //Nhập password dưới 6 chars
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("Duy");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Duyanh030");
	  //3. Click vào nút Register
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  //Verify message
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  
	  //1. Chọn Register
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  //Nhập password
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("Duyanh030");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("duyanh030");
	  //3. Click vào nút Register
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  //Verify message
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
  
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
