import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        driver.rotate(ScreenOrientation.PORTRAIT);
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

        driver.rotate(ScreenOrientation.LANDSCAPE);
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


    @Test
    public void testSaveAndDeleteArticleToReadindList(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'",
                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5,
                "Java");

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Island of Indonesia')]"),
                "Cannot find Java- island article",
                5);

        waitForElementAndClick(By.xpath("//android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab/android.widget.ImageView[@content-desc='Add this article to a reading list']"),
              "Cannot find 'Add this article to favorites' button",
                15);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'GOT IT')]"),
                "Cannot find button with text 'GOT IT'",
                5);

        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Add this article to favorites' button",
                15);

        String folderName = "My folder";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Add this article to favorites' button",
                15,
                folderName);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot find button with text 'OK'",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Close article' button",
                15);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'",
                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5, "Java");

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find Java- Object-oriented programming language",
                15);

        waitForElementAndClick(By.xpath("//android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab/android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find 'Add this article to favorites' button",
                15);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'My folder')]"),
                "Cannot find 'My folder'",
                15);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Close article' button",
                15);

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'Close article' button",
                15);

        waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                "Cannot find folder 'My folder'",
                15);

        waitForElementPresent(By.xpath("//*[contains(@text, 'object-oriented programming language')]"),
                "Cannot find article 'object-oriented programming language'",
                15);

        swipeElementToLeft(By.xpath("//*[contains(@text, 'object-oriented programming language')]"),
                "Cannot find the saved article");

        waitForElementPresent(By.xpath("//*[contains(@text, 'island of Indonesia')]"),
                "Cannot find the saved article",
                10);

        waitForElementNotPresent(By.xpath("//*[contains(@text, 'object-oriented programming language" +
                        "" +
                        "')]"),
                "Cannot delete the saved article",
                10);

    }

    @Test
    public void testTitleUsersSearch() throws Exception {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'",
                5);


        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5,
                "Java");

        String searchTitle = "//*[contains(@text, 'Island of Indonesia')]";
        waitForElementPresent(By.xpath(searchTitle),
                "Cannot find Java- island article",
                5);

        assertElementPresent(By.xpath(searchTitle),
                "Element with title 'Island of Indonesia'  not presented");

        waitForElementAndClick(By.xpath(searchTitle),
                "Cannot click Java- island article",
                3);
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

    private void assertElementPresent(By by, String errorTextMessage) throws Exception {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements <= 0) {
            String defaultMessage = "'An element'" + by.toString() + "'supposed to be present";
            throw new AssertionError(defaultMessage + " " + errorTextMessage);
        }
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
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

    protected void swipeElementToLeft(By by,String errorTextMessage){
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
    }
}
