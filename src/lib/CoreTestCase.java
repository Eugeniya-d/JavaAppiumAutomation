package lib;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
public class CoreTestCase extends IOSTestCase {


    protected AppiumDriver driver;
    protected Platform Platform;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.Platform = new Platform();
        driver = this.Platform.getDriver();
        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
