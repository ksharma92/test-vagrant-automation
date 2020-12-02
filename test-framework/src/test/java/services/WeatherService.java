package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import restassured_framework.RestAssuredHandler;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class WeatherService extends BaseService {
    String apiUrl = restAssuredHandler.getBaseUrl();
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
        return JsonUtils.getJsonObjectAsMap(response.getBody().asString(), "/main");
    }
}
