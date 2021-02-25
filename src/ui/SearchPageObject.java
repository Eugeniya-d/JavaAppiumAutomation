package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]";
    private static final String SEARCH_INPUT = "//*[contains(@text, 'Search…')]";
    private static final String SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='{SUBSTRING}']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find element with text 'Search Wikipedia'",
                5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot search the element input after click",
                5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),
                "Cannot find search input",
                5,
                searchLine);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath),
                "Cannot find search result with substring" + substring,
                5);
    }
}
