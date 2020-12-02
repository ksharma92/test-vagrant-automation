package restassured_framework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestAssuredHandler {
    @Setter
    @Getter
    private String baseUrl;

    @Getter
    @Setter
    protected String apiKey;


    public Response callGetRequest(String apiUrl, Map<String, String> queryParams) {
        log.info("Test");
        return given()
                .queryParams(queryParams)
                .when()
                .get(apiUrl);
    }

}
