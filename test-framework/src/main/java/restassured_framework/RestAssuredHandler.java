package restassured_framework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredHandler {
    @Setter
    @Getter
    private String baseUrl;

    @Getter
    @Setter
    protected String apiKey;


    public Response callGetRequest(String apiUrl, Map<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .get(apiUrl);
    }

}
