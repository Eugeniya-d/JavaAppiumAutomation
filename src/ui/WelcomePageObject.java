package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{
    private static final String LEARN_MORE_LINK = "//*[@name='Learn more about Wikipedia']";
    private static final String NEXT_BUTTON = "Next";
    private static final String NEW_WAVES_EXPLORE_LINK = "New ways to explore";
    private static final String SEARCH_IN_NEARLY_300_LANG_LINK = "Search in nearly 300 languages";
    private static final String LEARN_MORE_ABOUT_DATA_COLLECT_LINK = "Learn more about data collected";
    private static final String GET_STARTED_BUTTON = "//XCUIElementTypeStaticText[@name='Get started']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(By.xpath(LEARN_MORE_LINK),
                "Cannot find 'Learn more about Wikipedia' link",
                10    );
    }

    public void waitAndClickNextButton(){
        this.waitForElementAndClick(By.id(NEXT_BUTTON),
                "Cannot find 'Next' button",
                10    );
    }

    public void waitForNewWaysToExplore(){
        this.waitForElementPresent(By.id(NEW_WAVES_EXPLORE_LINK),
                "Cannot find 'New ways to explore' link",
                10    );
    }

    public void waitForSearchInNearly300Languagese(){
        this.waitForElementPresent(By.id(SEARCH_IN_NEARLY_300_LANG_LINK),
                "Cannot find 'Search in nearly 300 languages' link",
                10    );
    }

    public void waitForLearnMoreAboutDataCollected(){
        this.waitForElementPresent(By.id(LEARN_MORE_ABOUT_DATA_COLLECT_LINK),
                "Cannot find 'Learn more about data collected' link",
                10    );
    }

    public void waitAndClickForGetStarted(){
        this.waitForElementPresent(By.xpath(GET_STARTED_BUTTON),
                "Cannot find 'Get started' button",
                10    );
    }
}
