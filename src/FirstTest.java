import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
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
    public void firstTest() {
        System.out.println("First test run");
    }


    @Test
    public void testAssertElementHasText() throws Exception {
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Search Wikipedia",
                "Cannot find element with text 'Search Wikipedia'");
    }


    private WebElement waitForElementPresent(By by, String errorTextMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorTextMessage + "/n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private String assertElementHasText(By by, String expectedText, String errorTextMessage) throws Exception {
        WebElement element = waitForElementPresent(by, errorTextMessage, 5);
        String actualText = element.getAttribute("text");
        if (actualText.equals(expectedText)) {
            return actualText;
        } else
            throw new Exception(errorTextMessage);
    }
}
