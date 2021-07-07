package planets;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlanetsEndpoints {
    private static final String BASE_URL = "https://swapi.dev/api";

    public static Response getPlanets() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        return request.header("Content-Type", "application/json").get("/planets");
    }

    public static Response getPlanet(int planetNumber) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        return request.header("Content-Type", "application/json").get("/planets/" +planetNumber);
    }
}
