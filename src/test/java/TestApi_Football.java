import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestApi_Football {

    // Base URL of the API
    String url = "https://free-api-live-football-data.p.rapidapi.com";

    // Request specification with base URL + required headers
    RequestSpecification requestSpecification =
            new RequestSpecBuilder()
                    .setBaseUri(url)
                    .addHeader("X-RapidAPI-Key", "a246d7caabmsha5906de41ecca26p1afd2cjsn352845771666")
                    .addHeader("X-RapidAPI-Host", "free-api-live-football-data.p.rapidapi.com")
                    .build();

    @Test
    public void testAPI_Football_Brazil() {

        String path = "/football-get-all-countries";

        // Extracts only the name of Brazil
        String countryName =
                given()
                        .spec(requestSpecification)
                        .log().all()
                        .when()
                        .get(path)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .path("response.countries.find { it.ccode == 'BRA' }.name");

        System.out.println("Brazil found as: " + countryName);
    }

    @Test
    public void testAPI_Response_Football() {

        String path = "/football-get-all-countries";

        // Executes request and extracts response
        Response response =
                given()
                        .spec(requestSpecification)
                        .log().all()
                        .when()
                        .get(path)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        // Extracts the first 4 countries
        String firstCountry  = response.path("response.countries[0].name");
        String secondCountry = response.path("response.countries[1].name");
        String thirdCountry  = response.path("response.countries[2].name");
        String fourthCountry = response.path("response.countries[3].name");

        System.out.println("First country: " + firstCountry);
        System.out.println("Second country: " + secondCountry);
        System.out.println("Third country: " + thirdCountry);
        System.out.println("Fourth country: " + fourthCountry);
    }

    @Test
    public void testCountriesList() {

        String path = "/football-get-all-countries";

        // Executes request and extracts response
        Response response =
                given()
                        .spec(requestSpecification)
                        .log().all()
                        .when()
                        .get(path)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        // Total number of countries
        int totalCountries = response.path("response.countries.size()");
        System.out.println("Total countries: " + totalCountries);

        // Print the first 20 countries
        int limit = Math.min(totalCountries, 20);

        for (int i = 0; i < limit; i++) {
            String name = response.path("response.countries[" + i + "].name");
            System.out.println((i + 1) + " - " + name);
        }

        // Extract Brazil and Argentina
        String brazil = response.path("response.countries.find { it.ccode == 'BRA' }.name");
        String argentina = response.path("response.countries.find { it.ccode == 'ARG' }.name");

        // Validate that Brazil exists
        Assert.assertNotNull("Brazil was not found in the list", brazil);

        // Alphabetical comparison example
        Assert.assertTrue(
                "Brazil should come after Argentina alphabetically",
                brazil.compareTo(argentina) > 0
        );
    }
}