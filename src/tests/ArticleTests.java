/*package tests;

import lib.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import ui.*;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testEveryResultHasSearchingWord() throws Exception {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element with text 'Search Wikipedia'", 5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find search input", 5, "Java");

        assertTrue(MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text, 'Java')]"),
                "Java", "The first search result does not contains 'Java' word") &&
                MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                        "Java", "The third search result does not contains 'Java' word") &&
                MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text, 'JavaScript')]"),
                        "Java", "The third search result does not contains 'Java' word"));
    }

}*/
