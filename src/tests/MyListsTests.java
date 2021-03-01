package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.NavigationUi;
import ui.SearchPageObject;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveAndDeleteArticleToReadindList() {
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
}
