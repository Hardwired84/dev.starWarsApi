package people;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PeopleEndpoints {
    private static final String BASE_URL = "https://swapi.dev/api";

    public static Response getAllPeople() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        return request.header("Content-Type", "application/json").get("/people");
    }

    public static Response getPerson(int personNumber) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        return request.header("Content-Type", "application/json").get("/people/" + personNumber);
    }
}
