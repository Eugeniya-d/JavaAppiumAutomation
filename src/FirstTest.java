import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import ui.*;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testAssertElementHasText() throws Exception {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Led Zeppelin");
        SearchPageObject.clearStringSearch();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUi NavigationUi = new NavigationUi(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String firstArticleTitleOnSearch = "Island of Indonesia";
        SearchPageObject.goToTitle(firstArticleTitleOnSearch);
        ArticlePageObject.addArticleToFavoriteList();
        ArticlePageObject.confirmArticleSelection();
        ArticlePageObject.clearDefaultNameOfFolder();
        String folderName = "My folder";
        ArticlePageObject.giveArticleNewName(folderName);
        ArticlePageObject.confirmArticleAddiction();
        NavigationUi.exitFromArticlePage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String secondArticleTitleOnSearch = "Object-oriented programming language";
        SearchPageObject.goToTitle(secondArticleTitleOnSearch);
        ArticlePageObject.addArticleToFavoriteList();
        ArticlePageObject.addArticleToExistingFolder(folderName);
        NavigationUi.exitFromArticlePage();
        NavigationUi.goToSavedFolders();
        MyListsPageObject.goToSelectedFolder();
        String firstArticleTitle = "object-oriented programming language";
        String secondArticleTitle = "island of Indonesia";
        MyListsPageObject.waitForTitle(firstArticleTitle);
        MyListsPageObject.deleteArticle(firstArticleTitle);
        MyListsPageObject.waitForTitle(secondArticleTitle);
        MyListsPageObject.waitNotForTitle(firstArticleTitle);
    }

    @Test
    public void testTitleUsersSearch() throws Exception {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String articleTitleOnSearch = "Island of Indonesia";
        SearchPageObject.waitForTitlePresent(articleTitleOnSearch);
        SearchPageObject.goToTitle(articleTitleOnSearch);
    }
}
