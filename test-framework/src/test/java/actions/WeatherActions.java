package actions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.WeatherPage;

public class WeatherActions extends BaseAction {
    WeatherPage weatherPage;

    public WeatherActions(WebDriver driver) {
        super(driver);
        weatherPage = new WeatherPage(driver);
        PageFactory.initElements(driver, weatherPage);
    }

    public void selectCityAndOpenDetailsInMap(String cityName) {
        weatherPage.setSearchCity(cityName)
                .selectCityFromList(cityName)
                .clickOnCityInMap(cityName)
                .waitForWeatherPopup();
    }

    @Step
    public String getWeatherDetailForCity(String cityName, String weatherDetail) {
        selectCityAndOpenDetailsInMap(cityName);
        return weatherPage.getWeatherDetailsForCity(weatherDetail);
    }
}
