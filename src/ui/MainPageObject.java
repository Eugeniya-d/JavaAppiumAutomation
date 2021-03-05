package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String errorTextMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorTextMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public Boolean waitForElementNotPresent(By by, String errorTextMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorTextMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public String assertElementHasText(By by, String expectedText, String errorTextMessage) throws Exception {
        WebElement element = waitForElementPresent(by, errorTextMessage, 5);
        String actualText = element.getAttribute("text");
        if (actualText.equals(expectedText)) {
            return actualText;
        } else
            throw new Exception(errorTextMessage);
    }

    public boolean assertElementContainsText(By by, String expectedText, String errorTextMessage) throws Exception {
        WebElement element = waitForElementPresent(by, errorTextMessage, 5);
        String actualText = element.getAttribute("text");
        if (actualText.contains(expectedText)) {
            return true;
        } else
            throw new Exception(errorTextMessage);
    }

    public void assertElementPresent(By by, String errorTextMessage) throws Exception {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements <= 0) {
            String defaultMessage = "'An element'" + by.toString() + "'supposed to be present";
            throw new AssertionError(defaultMessage + " " + errorTextMessage);
        }
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }


    public WebElement waitForElementAndClick(By by, String errorTextMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorTextMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String errorTextMessage, long timeoutInSeconds, String value) {
        WebElement element = waitForElementPresent(by, errorTextMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(By by, String errorTextMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorTextMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

  /*  public void swipeElementToLeft(By by,String errorTextMessage){
        WebElement element =  waitForElementPresent(
                by,
                errorTextMessage,
                10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/ 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX,middleY)
                .waitAction(300)
                .moveTo(leftX,middleY)
                .release().perform();
    }*/
}
