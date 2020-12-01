package tests;

import actions.HomeActions;
import actions.WeatherActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import selenium_framework.SeleniumBaseFramework;

public class BaseTest extends SeleniumBaseFramework {
    public String baseURL;
    public HomeActions homeActions;
    public WeatherActions weatherActions;
    @BeforeClass(alwaysRun = true)
    public void test() {
        //todo the url will eventually be picked from a configuration file.
        baseURL = "https://ndtv.com";
        initActions(driver);
    }

    private void initActions(WebDriver driver) {
        homeActions = new HomeActions(driver);
        weatherActions = new WeatherActions(driver);
    }
}
