package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        GO_TO_SELECTED_FOLDER  =  "xpath://ui.android.widget.ImageButton[@content-desc='Navigate up']";
        FIND_TITLE_OF_ARTICLE_NAME_TPL = "xpath://ui.android.widget.FrameLayout[@content-desc='My lists']";
    }
    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
