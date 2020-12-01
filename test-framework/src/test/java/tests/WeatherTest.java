package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WeatherTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        homeActions.launchApplication(baseURL);
    }

    @Test(description = "Comparing whether the temperature displayed on screen matches with the temperate received from the api")
    public void verifyTemperatureForCity() {
        SoftAssert softAssert = new SoftAssert();
        homeActions.navigateToWeatherSection();
        weatherActions.selectCityAndOpenDetailsInMap("Pune");
    }
}
