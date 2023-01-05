package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	//Chua cac ham dung trong tat ca cac page
	
	public void openPageUrl (WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getUrl (WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageTitle (WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageSourceCode (WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void back (WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forward (WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refresh (WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	public Alert waitForAlertPresence (WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait (driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert (WebDriver driver) {
		waitForAlertPresence(driver).accept();
		
	}
	
	public void cancelAlert (WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getTextAlert (WebDriver driver) {
		return waitForAlertPresence(driver).getText();
		
	}
	
	public void senkeyToAlert (WebDriver driver, String textValue){
		waitForAlertPresence(driver).sendKeys(textValue);
		
	}	
	
	public void switchWindowByID (WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);                                                                                                                                                                                                                                           
		
	}
	
	public void switchWindowOrTabByID (WebDriver driver, String idTab) {
		Set <String> allIdTabs = driver.getWindowHandles();    
		for (String id : allIdTabs) {
			if (!id.equals(idTab)) {
				driver.switchTo().window(id);
			}
			
		}
	}
	
	public void switchWindowOrTabByTitle (WebDriver driver, String expectedTitlePage) {
		Set <String> allIdTabs = driver.getWindowHandles();    
		for (String id : allIdTabs) {
			driver.switchTo().window(id);
			String currentTitlePage = driver.getTitle();
			if (currentTitlePage.equals(expectedTitlePage)) {
				break;
			}
			
		}
	}
	
	public void closeAllWindowsExceptParent (WebDriver driver, String parentTitlePage) {
		Set <String> allIdTabs = driver.getWindowHandles();    
		for (String id : allIdTabs) {
			driver.switchTo().window(id);
			String currentTitlePage = driver.getTitle();
			if (!currentTitlePage.equals(parentTitlePage)) {
				driver.close();
				break;
			}
			
		}
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathLocator ) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	public List<WebElement> getListWebElement (WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	public void clickToElement (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.click();
	}
	
	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		getWebElement(driver, xpathLocator).sendKeys(textValue);
	}
	
	public String getElementText (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}
	
	public void selectItemInDefaultDropdown (WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textItem);
	}
	
	public String getSelectedItemDefaultDropdown (WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple (WebDriver driver, String xpathLocator ) {
		Select select = new Select (getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropDown (WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		WebDriverWait explicitWait = new WebDriverWait (driver, 30);
		
		clickToElement ( driver, parentLocator);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
		List <WebElement> allItem = driver.findElements(getByXpath(childLocator));
		for (WebElement item : allItem) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			}
				item.click();
				break;
			}
			
	}
	
	public String getElementAttribute (WebDriver driver, String xpathLocator, String attributeName ) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	
	public String getHexaColorFromRgba (String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	
	public int getElementSize (WebDriver driver, String xpathLocator, String attributeName) {
		return getListWebElement(driver, xpathLocator).size();
	}
	
	
	public void checkToDefaultCheckboxRadio (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if(!element.isSelected()) {
			clickToElement(driver, xpathLocator);
		}
	}
	
	public void uncheckToDefaultCheckboxRadio (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if(element.isSelected()) {
			clickToElement(driver, xpathLocator);
		}
	}
	
	
	public boolean isElementDisplayed (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	
	
	public boolean isElementEnabled (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	
	public boolean isElementSelected (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public void switchToFrameIframe (WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	
	public void switchToDefaultContent (WebDriver driver, String xpathLocator) {
		driver.switchTo().defaultContent();
	}
	
	
	public void hoverMouseToElement (WebDriver driver, String xpathLocator) {
		Actions action = new Actions (driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}
	
	public void openURL (WebDriver driver, String URL ) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '"+ URL +"'" );
		
	}
	
	public String getDomainPage (WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.domain");
	}
	
	public String getTitlePage (WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.title");
	}
	
	public String getURLPage (WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.URL");
	}
	
	public void clickToElementByJS (WebDriver driver, String xpathLocator ) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}
	
	
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}
	
	public void scrollToElementByJSAbove (WebDriver driver, String xpathLocator ) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}
	
	public void scrollToElementByJSBelow (WebDriver driver, String xpathLocator ) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, xpathLocator));
	}
	
	public void sendkeyToElementByJS (WebDriver driver, String xpathLocator, String value ) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, xpathLocator));
	}
	
	public void waitForElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait (driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));

	}
	
	public void waitForAllElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait (driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));

	}
	
	public void waitForElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait (driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));

	}
	
	public void waitForAllElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait (driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));

	}
	
	public void waitForAllElemenClickable (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait (driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));

	}
	
	private long longTimeout = 30;
	
}
	
