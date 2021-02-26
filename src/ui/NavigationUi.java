package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject{
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    private static final String EXIT_FROM_ARTICLE_NAVIGATE_BUTTON =  "//android.widget.ImageButton[@content-desc='Navigate up']";
    private static final String GO_TO_SAVED_FOLDERS_NAVIGATE_BUTTON = "//android.widget.FrameLayout[@content-desc='My lists']";


    public void exitFromArticlePage(){
        this.waitForElementAndClick(By.xpath(EXIT_FROM_ARTICLE_NAVIGATE_BUTTON),
                "Cannot close the article",
                15);
    }

    public void goToSavedFolders(){
        this.waitForElementAndClick(By.xpath(GO_TO_SAVED_FOLDERS_NAVIGATE_BUTTON),
                "Cannot go to favorites folders",
                15);
    }
}
