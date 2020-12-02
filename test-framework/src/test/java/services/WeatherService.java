package services;

import io.restassured.response.Response;
import restassured_framework.RestAssuredHandler;

import java.util.HashMap;
import java.util.Map;

public class WeatherService extends BaseService {
    String apiUrl = "";
    Map<String, String> queryParams;
    public WeatherService(RestAssuredHandler restAssuredHandler) {
        super(restAssuredHandler);
    }

    public Map<String, String> getWeatherDetailsFromApi(String cityName) {
        queryParams = new HashMap() {{
            put("q", cityName);
            put("appid", restAssuredHandler.getApiKey());
//            put("appid", restAssuredHandler.getApiKey());
        }};
        Response response = restAssuredHandler.callGetRequest(apiUrl, queryParams);
        System.out.println(response.getBody().asString());
        return new HashMap<>();
    }
}
