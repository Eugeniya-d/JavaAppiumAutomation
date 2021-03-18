package ui.factories;


import io.appium.java_client.AppiumDriver;
import lib.Platform;
import ui.MyListsPageObject;
import ui.NavigationUIPageObject;
import ui.android.AndroidMyListsPageObject;
import ui.android.AndroidNavigationUIPageObject;
import ui.ios.IOSMyListsPageObject;
import ui.ios.IOSNavigationUIPageObject;

public class MyListPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
