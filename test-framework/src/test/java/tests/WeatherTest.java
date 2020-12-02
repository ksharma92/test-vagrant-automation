package tests;

import dataprovider.WeatherInfoProvider;
import enums.ExecutionFlag;
import enums.UnitsOfMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.WindowsUtils;

import java.util.Map;


@Slf4j
public class WeatherTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        homeActions.launchApplication(baseURL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        WindowsUtils.closeAllWindows(driver);
    }

    @Test(description = "Comparing whether the temperature displayed on screen matches with the temperate received from the api", dataProvider = "dataProviderForWeatherScenario", dataProviderClass =  WeatherInfoProvider.class)
    public void verifyTemperatureForCity(String scenarioName, String scenarioDescription, ExecutionFlag executionFlag, String city, String condition, String variationAllowed) {
        SoftAssert softAssert = new SoftAssert();
        homeActions.navigateToWeatherSection();
        Map<String, String> weatherMap = weatherActions.getWeatherDetailForCity("Pune");
        double tempInDegrees = Double.parseDouble(weatherMap.get("Temp in Degrees"));
        Map weatherDetailsApi = weatherService.getWeatherDetailsFromApi("Pune", UnitsOfMeasurement.METRIC);
        double tempResponseAPI = (double) weatherDetailsApi.get("temp");
        softAssert.assertTrue(tempInDegrees >= tempResponseAPI - 2 && tempInDegrees <= tempResponseAPI + 2, "The current temperature in the UI and the current temperature from the API do not match. " +
                "Current Temperature (UI): "+tempInDegrees+" \n Current temperature (API): "+tempResponseAPI +" with an accepted degree of difference of 2{");
        softAssert.assertAll();
    }

    @Test(description = "test", dataProvider = "dataProviderForWeatherScenario", dataProviderClass = WeatherInfoProvider.class)
    public void testDataProvider(String scenarioName, String scenarioDescription, ExecutionFlag executionFlag, String city, String condition, String variationAllowed) {
        log.info("{}, {}, {}, {}, {}, {}", scenarioName, scenarioDescription, executionFlag, city, condition, variationAllowed);
        System.out.println(scenarioName);

    }
}
