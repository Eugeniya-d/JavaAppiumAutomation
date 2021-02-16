import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "/Users/a18275751/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testAssertElementHasText() throws Exception {
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Search Wikipedia",
                "Cannot find element with text 'Search Wikipedia'");
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input", 5, "Led Zeppelin");

        waitForElementPresent(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Led Zeppelin']"),
                "Cannot find search 'Led Zeppelin' in the search results", 15);

        waitForElementAndClear(By.xpath("//*[@resource-id ='org.wikipedia:id/search_src_text']"),
                "Cannot find search field", 5);

        waitForElementAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/search_close_btn']"),
                "Cannot find X button", 10);


        waitForElementNotPresent(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Led Zeppelin']"),
                "Cannot find search 'Led Zeppelin' in the search results", 15);
    }

    @Test
    public void testEveryResultHasSearchingWord() throws Exception {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input", 5, "Java");

        Assert.assertTrue(assertElementContainsText(By.xpath("//*[contains(@text, 'Java')]"),
                "Java", "The first search result does not contains 'Java' word") &&
                assertElementContainsText(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                        "Java", "The third search result does not contains 'Java' word") &&
                assertElementContainsText(By.xpath("//*[contains(@text, 'JavaScript')]"),
                        "Java", "The third search result does not contains 'Java' word"));
    }

    private WebElement waitForElementPresent(By by, String errorTextMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorTextMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private Boolean waitForElementNotPresent(By by, String errorTextMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorTextMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private String assertElementHasText(By by, String expectedText, String errorTextMessage) throws Exception {
        WebElement element = waitForElementPresent(by, errorTextMessage, 5);
        String actualText = element.getAttribute("text");
        if (actualText.equals(expectedText)) {
            return actualText;
        } else
            throw new Exception(errorTextMessage);
    }

    private boolean assertElementContainsText(By by, String expectedText, String errorTextMessage) throws Exception {
        WebElement element = waitForElementPresent(by, errorTextMessage, 5);
        String actualText = element.getAttribute("text");
        if (actualText.contains(expectedText)) {
            return true;
        } else
            throw new Exception(errorTextMessage);
    }

    private WebElement waitForElementAndClick(By by, String errorTextMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorTextMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String errorTextMessage, long timeoutInSeconds, String value) {
        WebElement element = waitForElementPresent(by, errorTextMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String errorTextMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorTextMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
}
