package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]";
    private static final String SEARCH_INPUT = "//*[contains(@text, 'Search…')]";
    private static final String SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='{SUBSTRING}']";
    private static final String SEARCH_CANCEL_BUTTON = "//*[@resource-id ='org.wikipedia:id/search_close_btn']";
    private static final String SEARCH_CLEAR_STRING_BUTTON = "//*[@resource-id ='org.wikipedia:id/search_src_text']";
    private static final String FIND_TITLE_OF_ARTICLE_TPL = "//*[contains(@text, '{ARTICLE_TITLE}')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getArticleTitle(String articleTitle) {
        return FIND_TITLE_OF_ARTICLE_TPL.replace("{ARTICLE_TITLE}", articleTitle);
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

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON ),
                "Cannot find search field",
                5);
    }

    public void clearStringSearch(){
        this.waitForElementAndClear(By.xpath(SEARCH_CLEAR_STRING_BUTTON),
                "Cannot find search field",
                5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Search cancel button is stiil present",
                5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClear(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot find search and click cancel button",
                5);
    }
    public void goToTitle(String articleTitle) {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.waitForElementAndClick(By.xpath(getArticleTitle),
                "Cannot find title with name " + articleTitle,
                10);
    }
}
