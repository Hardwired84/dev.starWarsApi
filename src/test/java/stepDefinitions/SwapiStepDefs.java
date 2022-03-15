package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import people.PeopleEndpoints;
import people.PeopleExample;
import people.Result;
import planets.PlanetExample;
import planets.PlanetsEndpoints;

public class SwapiStepDefs {
    private static Response response;
    private static String jsonString;

    @When("I send request to get information about the {int} planet")
    public void iSendRequestToGetInformationAboutThePlanet(int number) {
        PlanetsEndpoints.getPlanet(number);
    }

    @Then("I check {string} of {int} planet equals to {string}")
    public void iCheckOfPlanetEqualsTo(String parameter, int number, String name) {
        jsonString = PlanetsEndpoints.getPlanet(number).asString();
        String actualNamePlanet = JsonPath.from(jsonString).get(parameter);
        Assert.assertEquals(actualNamePlanet, name);

    }

    @When("I get planets information")
    public void iGetPlanetsInformation() {
        Response planets = PlanetsEndpoints.getPlanets();
        planets.getBody().as(PlanetExample.class);
    }

    @Then("I check at list one of them has diameter more than {int} kilometers")
    public void iCheckAtListOneOfThemHasDiameterMoreThanKilometers(long km) {
        Response planets = PlanetsEndpoints.getPlanets();
        PlanetExample planetsExample = planets.getBody().as(PlanetExample.class);
        planetsExample.getResults().stream()
                .filter(planet -> Integer.parseInt(planet.getDiameter()) > km)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("There is no planet with diameter more than '%d'", km)));
    }

    @When("I get people information")
    public void iGetPeopleInformation() {
        PeopleEndpoints.getAllPeople();
    }

    @Then("I check at list one of them has name {string}")
    public void iCheckAtListOneOfThemHasName(String expectedName) {
        Response people = PeopleEndpoints.getAllPeople();
        PeopleExample peopleExample = people.getBody().as(PeopleExample.class);
        peopleExample.getResults().stream()
                .filter(person -> person.getName().equals(expectedName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("There is no person with expected name '%s'", expectedName)));
    }

    @Then("I check {int} person with name equals to {string} has {string} eyes")
    public void iCheckPersonWithNameEqualsToHasEyes(int personNumber, String expectedName, String expectedEyesColor) {
        Response person = PeopleEndpoints.getPerson(personNumber);
        Result result = person.getBody().as(Result.class);
        Assert.assertTrue(result.getName().equals(expectedName) && result.getEyeColor().equals(expectedEyesColor));
    }
}
