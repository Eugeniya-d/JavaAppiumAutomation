package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {
    private static final String ARTICLE_TO_FAVORITE_LIST_BUTTON = "xpath://android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab/android.widget.ImageView[@content-desc='Add this article to a reading list']";
    private static final String CONFIRM_ARTICLE_SELECTION_BUTTON = "xpath://*[contains(@text, 'GOT IT')]";
    private static final String STRING_NAME_OF_FOLDER = "id:org.wikipedia:id/text_input";
    private static final String CONFIRM_ARTICLE_ADDICTION = "xpath://*[contains(@text, 'OK')]";
    private static final String ADD_ARTICLE_TO_EXISTING_FOLDER_TPL = "xpath://*[contains(@text, '{NAME_OF_FOLDER}')]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderName(String folderName) {
        return ADD_ARTICLE_TO_EXISTING_FOLDER_TPL.replace("{NAME_OF_FOLDER}", folderName);
    }
    /* TEMPLATES METHODS */

    public void addArticleToFavoriteList() throws IllegalAccessException {
        this.waitForElementAndClick(ARTICLE_TO_FAVORITE_LIST_BUTTON,
                "Cannot find 'Add this article to favorites' button",
                15);
    }

    public void confirmArticleSelection() throws IllegalAccessException {
        this.waitForElementAndClick(CONFIRM_ARTICLE_SELECTION_BUTTON,
                "Cannot find button with text 'GOT IT'",
                5);
    }

    public void clearDefaultNameOfFolder() throws IllegalAccessException {
        this.waitForElementAndClear(STRING_NAME_OF_FOLDER,
                "Cannot clear default folder name",
                15);
    }

    public void giveArticleNewName(String name) throws IllegalAccessException {
        this.waitForElementAndSendKeys(STRING_NAME_OF_FOLDER,
                "Cannot give new name for article",
                15,
                name);
    }

    public void confirmArticleAddiction() throws IllegalAccessException {
        this.waitForElementAndClick(CONFIRM_ARTICLE_ADDICTION,
                "Cannot add new folder",
                5);
    }

    public void addArticleToExistingFolder(String folderName) throws IllegalAccessException {
        String getFolderName = getFolderName(folderName);
        this.waitForElementAndClick(getFolderName,
                "Cannot find folder with name " + folderName,
                5);
    }
}
