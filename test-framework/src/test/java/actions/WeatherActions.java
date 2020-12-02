package actions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.WeatherPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherActions extends BaseAction {
    WeatherPage weatherPage;

    public WeatherActions(WebDriver driver) {
        super(driver);
        weatherPage = new WeatherPage(driver);
        PageFactory.initElements(driver, weatherPage);
    }

    @Step
    public void selectCityAndOpenDetailsInMap(String cityName) {
        weatherPage.setSearchCity(cityName)
                .selectCityFromList(cityName)
                .clickOnCityInMap(cityName)
                .waitForWeatherPopup();
    }

    @Step
    public Map<String, String> getWeatherDetailForCity(String cityName) {
        selectCityAndOpenDetailsInMap(cityName);
        Map<String, String> weatherDetailsMap = new HashMap<>();
        List<String> weatherDetails = weatherPage.getWeatherDetailsForCity();
        System.out.println(weatherDetails);
        //storing content of the weather information in a map.
        for (String weatherInfo : weatherDetails) {
            String[] split = weatherInfo.split(":");
            weatherDetailsMap.put(split[0].trim(), split[1].trim());
        }
        return weatherDetailsMap;
    }
}
