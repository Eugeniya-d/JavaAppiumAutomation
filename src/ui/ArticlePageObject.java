package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {
    private static final String ARTICLE_TO_FAVORITE_LIST_BUTTON = "//android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab/android.widget.ImageView[@content-desc='Add this article to a reading list']";
    private static final String CONFIRM_ARTICLE_SELECTION_BUTTON = "//*[contains(@text, 'GOT IT')]";
    private static final String STRING_NAME_OF_FOLDER = "org.wikipedia:id/text_input";
    private static final String CONFIRM_ARTICLE_ADDICTION = "//*[contains(@text, 'OK')]";
    private static final String ADD_ARTICLE_TO_EXISTING_FOLDER_TPL = "//*[contains(@text, '{NAME_OF_FOLDER}')]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderName(String folderName) {
        return ADD_ARTICLE_TO_EXISTING_FOLDER_TPL.replace("{NAME_OF_FOLDER}", folderName);
    }
    /* TEMPLATES METHODS */

    public void addArticleToFavoriteList() {
        this.waitForElementAndClick(By.xpath(ARTICLE_TO_FAVORITE_LIST_BUTTON),
                "Cannot find 'Add this article to favorites' button",
                15);
    }

    public void confirmArticleSelection() {
        this.waitForElementAndClick(By.xpath(CONFIRM_ARTICLE_SELECTION_BUTTON),
                "Cannot find button with text 'GOT IT'",
                5);
    }

    public void clearDefaultNameOfFolder() {
        this.waitForElementAndClear(By.id(STRING_NAME_OF_FOLDER),
                "Cannot clear default folder name",
                15);
    }

    public void giveArticleNewName(String name) {
        this.waitForElementAndSendKeys(By.id(STRING_NAME_OF_FOLDER),
                "Cannot give new name for article",
                15,
                name);
    }

    public void confirmArticleAddiction() {
        this.waitForElementAndClick(By.xpath(CONFIRM_ARTICLE_ADDICTION),
                "Cannot add new folder",
                5);
    }

    public void addArticleToExistingFolder(String folderName) {
        String getFolderName = getFolderName(folderName);
        this.waitForElementAndClick(By.xpath(getFolderName),
                "Cannot find folder with name " + folderName,
                5);
    }
}
