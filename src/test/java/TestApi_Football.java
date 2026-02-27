import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestApi_Football {

    String url = "https://free-api-live-football-data.p.rapidapi.com";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void testAPI_Football_Brazil(){

        String path = "/football-get-all-countries";

        // find returns a single object → extract only the name
        String countryName =
                given()
                        .header("X-RapidAPI-Key","a246d7caabmsha5906de41ecca26p1afd2cjsn352845771666")
                        .header("X-RapidAPI-Host","free-api-live-football-data.p.rapidapi.com")
                        .spec(requestSpecification)
                        .log().all()
                        .when()
                        .get(path)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .path("response.countries.find { it.ccode == 'BRA' }.name");

        System.out.println(countryName);
    }

    @Test
    public void testAPI_Response_Football(){

        // Endpoint path
        String path = "/football-get-all-countries";

        // Response object to use .path()
        io.restassured.response.Response response =
                given()
                        // Required RapidAPI headers
                        .header("X-RapidAPI-Key","a246d7caabmsha5906de41ecca26p1afd2cjsn352845771666")
                        .header("X-RapidAPI-Host","free-api-live-football-data.p.rapidapi.com")

                        // Base URL from RequestSpecification
                        .spec(requestSpecification)

                        // Log request
                        .log().all()

                        .when()
                        // Execute GET
                        .get(path)

                        .then()
                        // Log response
                        .log().all()

                        // Validate HTTP status
                        .statusCode(200)

                        // Extract response object
                        .extract()
                        .response();

        // Variables for the first countries in the list
        String firstCountry, secondCountry, thirdCountry, fourthCountry;

        // Correct JSONPath based on your JSON
        firstCountry  = response.path("response.countries[0].name");
        secondCountry = response.path("response.countries[1].name");
        thirdCountry  = response.path("response.countries[2].name");
        fourthCountry = response.path("response.countries[3].name");

        // Print results
        System.out.println("First country: " + firstCountry);
        System.out.println("Second country: " + secondCountry);
        System.out.println("Third country: " + thirdCountry);
        System.out.println("Fourth country: " + fourthCountry);
    }
}