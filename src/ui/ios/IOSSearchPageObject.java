package ui.ios;

import io.appium.java_client.AppiumDriver;
import ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_CLEAR_STRING_BUTTON = "xpath://XCUIElementTypeCell";
        FIND_TITLE_OF_ARTICLE_TPL = "xpath:///XCUIElementTypeStaticText[contains(@name,'{ARTICLE_TITLE}')]";
        FIND_DESCRIPTION_OF_ARTICLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{ARTICLE_DESCRIPTION}')]";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
