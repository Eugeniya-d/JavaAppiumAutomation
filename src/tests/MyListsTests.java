package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.NavigationUIPageObjectFactory;
import ui.factories.SearchPageObjectFactory;
import ui.factories.MyListPageObjectFactory;


public class MyListsTests extends CoreTestCase {

private static final String folderName = "My folder";

    @Test
    public void testSaveAndDeleteArticleToReadindList() throws IllegalAccessException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUIPageObject NavigationUIPageObject = NavigationUIPageObjectFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String firstArticleTitleOnSearch = "Island of Indonesia";
        SearchPageObject.goToTitle(firstArticleTitleOnSearch);
        ArticlePageObject.addArticleToFavoriteList();
        ArticlePageObject.confirmArticleSelection();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.clearDefaultNameOfFolder();
            ArticlePageObject.giveArticleNewName(folderName);
            ArticlePageObject.confirmArticleAddiction();
        }
        NavigationUIPageObject.exitFromArticlePage();
        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine("Java");
        }
        String secondArticleTitleOnSearch = "Object-oriented programming language";
        SearchPageObject.goToTitle(secondArticleTitleOnSearch);
        ArticlePageObject.addArticleToFavoriteList();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistingFolder(folderName);
        }
        NavigationUIPageObject.exitFromArticlePage();
        NavigationUIPageObject.goToSavedFolders();
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.goToSelectedFolder();
            String firstArticleTitle = "object-oriented programming language";
            String secondArticleTitle = "island of Indonesia";
            MyListsPageObject.deleteArticle(firstArticleTitle);
            MyListsPageObject.waitForTitle(secondArticleTitle);
            MyListsPageObject.waitNotForTitle(firstArticleTitle);
        }
        MyListsPageObject.swipeToDeleteArticleForIOS();
        MyListsPageObject.waitNotForTypeImage();
        }
    }

