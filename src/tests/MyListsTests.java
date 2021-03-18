package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.NavigationUIPageObject;
import ui.SearchPageObject;
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
        ArticlePageObject.clearDefaultNameOfFolder();
        ArticlePageObject.giveArticleNewName(folderName);
        ArticlePageObject.confirmArticleAddiction();
        NavigationUIPageObject.exitFromArticlePage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String secondArticleTitleOnSearch = "Object-oriented programming language";
        SearchPageObject.goToTitle(secondArticleTitleOnSearch);
        ArticlePageObject.addArticleToFavoriteList();
        ArticlePageObject.addArticleToExistingFolder(folderName);
        NavigationUIPageObject.exitFromArticlePage();
        NavigationUIPageObject.goToSavedFolders();
        MyListsPageObject.goToSelectedFolder();
        String firstArticleTitle = "object-oriented programming language";
        String secondArticleTitle = "island of Indonesia";
        MyListsPageObject.waitForTitle(firstArticleTitle);
        MyListsPageObject.waitForTitle(secondArticleTitle);
        MyListsPageObject.waitNotForTitle(firstArticleTitle);
    }
}
