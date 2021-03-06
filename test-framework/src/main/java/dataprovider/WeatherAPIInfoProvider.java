package dataprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import enums.ExecutionFlag;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import pojo.WeatherAPIPojo;
import pojo.WeatherPojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static selenium_framework.Constants.WEATHER_API_PATH;

@Slf4j
public class WeatherAPIInfoProvider {
    public static WeatherAPIPojo weatherPojo;

    @DataProvider(name = "dataProviderForWeatherAPIs")
    public static Iterator<Object[]> weatherDataProvider() {
        File f = new File(WEATHER_API_PATH);
        List<Object[]> weatherData = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(f);
            for (JsonNode jsonNode : arrayNode) {
                weatherPojo = objectMapper.readValue(jsonNode.toString(), WeatherAPIPojo.class);
                try {
                    if (ExecutionFlag.valueOf(weatherPojo.getExecution()).equals(ExecutionFlag.ON)) {
                        Object[] ob = {weatherPojo.getScenarioName(), weatherPojo.getScenarioDescription(), weatherPojo.getCity(),
                                weatherPojo.getPassAccessToken(), weatherPojo.getUnitMeasurement(), weatherPojo.getExpectedResponse()};
                        weatherData.add(ob);
                    }
                } catch (IllegalArgumentException illegalArgumentException) {
                    log.warn("Input passed for Execution flag is incorrect, permissible values are 'ON' or 'OFF', input provided {}. Skipping iteration", weatherPojo.getExecution());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherData.iterator();
    }
}
