package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.ArticlePageObject;
import ui.factories.ArticlePageObjectFactory;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TO_FAVORITE_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='save']";
        CONFIRM_ARTICLE_SELECTION_BUTTON = "xpath://XCUIElementTypeButton[@name='places auth close']";
        STRING_NAME_OF_FOLDER = "id:org.wikipedia:id/text_input";
        CONFIRM_ARTICLE_ADDICTION = "xpath://*[contains(@text, 'OK')]";
        ADD_ARTICLE_TO_EXISTING_FOLDER_TPL = "xpath://*[contains(@text, '{NAME_OF_FOLDER}')]";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}

