import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import ui.MainPageObject;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testAssertElementHasText() throws Exception {
        MainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Search Wikipedia",
                "Cannot find element with text 'Search Wikipedia'");
    }

    @Test
    public void testCancelSearch() {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'", 5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input", 5, "Led Zeppelin");

        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Led Zeppelin']"),
                "Cannot find search 'Led Zeppelin' in the search results", 15);

        MainPageObject.waitForElementAndClear(By.xpath("//*[@resource-id ='org.wikipedia:id/search_src_text']"),
                "Cannot find search field", 5);

        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id ='org.wikipedia:id/search_close_btn']"),
                "Cannot find X button", 10);

        MainPageObject.waitForElementNotPresent(By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Led Zeppelin']"),
                "Cannot find search 'Led Zeppelin' in the search results", 15);

    }

    @Test
    public void testEveryResultHasSearchingWord() throws Exception {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'", 5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input", 5, "Java");

        Assert.assertTrue(MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text, 'Java')]"),
                "Java", "The first search result does not contains 'Java' word") &&
                MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                        "Java", "The third search result does not contains 'Java' word") &&
                MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text, 'JavaScript')]"),
                        "Java", "The third search result does not contains 'Java' word"));
    }


    @Test
    public void testSaveAndDeleteArticleToReadindList(){
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'",
                5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5,
                "Java");

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Island of Indonesia')]"),
                "Cannot find Java- island article",
                5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab/android.widget.ImageView[@content-desc='Add this article to a reading list']"),
              "Cannot find 'Add this article to favorites' button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'GOT IT')]"),
                "Cannot find button with text 'GOT IT'",
                5);

        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Add this article to favorites' button",
                15);

        String folderName = "My folder";

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Add this article to favorites' button",
                15,
                folderName);

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot find button with text 'OK'",
                5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Close article' button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'",
                5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5, "Java");

        MainPageObject. waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find Java- Object-oriented programming language",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab/android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find 'Add this article to favorites' button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'My folder')]"),
                "Cannot find 'My folder'",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Close article' button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'Close article' button",
                15);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                "Cannot find folder 'My folder'",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'object-oriented programming language')]"),
                "Cannot find article 'object-oriented programming language'",
                15);

        MainPageObject.swipeElementToLeft(By.xpath("//*[contains(@text, 'object-oriented programming language')]"),
                "Cannot find the saved article");

        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'island of Indonesia')]"),
                "Cannot find the saved article",
                10);

        MainPageObject.waitForElementNotPresent(By.xpath("//*[contains(@text, 'object-oriented programming language" +
                        "" +
                        "')]"),
                "Cannot delete the saved article",
                10);

    }

    @Test
    public void testTitleUsersSearch() throws Exception {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'",
                5);


        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input",
                5,
                "Java");

        String searchTitle = "//*[contains(@text, 'Island of Indonesia')]";
        MainPageObject.waitForElementPresent(By.xpath(searchTitle),
                "Cannot find Java- island article",
                5);

        MainPageObject.assertElementPresent(By.xpath(searchTitle),
                "Element with title 'Island of Indonesia'  not presented");

        MainPageObject.waitForElementAndClick(By.xpath(searchTitle),
                "Cannot click Java- island article",
                3);
    }

}
