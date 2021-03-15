package ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUi extends MainPageObject{
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    private static final String EXIT_FROM_ARTICLE_NAVIGATE_BUTTON =  "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    private static final String GO_TO_SAVED_FOLDERS_NAVIGATE_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";


    public void exitFromArticlePage() throws IllegalAccessException {
        this.waitForElementAndClick(EXIT_FROM_ARTICLE_NAVIGATE_BUTTON,
                "Cannot close the article",
                15);
    }

    public void goToSavedFolders() throws IllegalAccessException {
        this.waitForElementAndClick(GO_TO_SAVED_FOLDERS_NAVIGATE_BUTTON,
                "Cannot go to favorites folders",
                15);
    }
}
