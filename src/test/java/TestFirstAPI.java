// Imports the main RestAssured class used to make HTTP requests

// Imports the RequestSpecBuilder used to build request specifications
import io.restassured.builder.RequestSpecBuilder;

// Imports the RequestSpecification interface, which stores default request configurations
import io.restassured.specification.RequestSpecification;

// Imports Hamcrest Matchers used to validate responses
import org.hamcrest.Matchers;

// Imports the @Test annotation from JUnit to mark test methods
import org.junit.Test;

// Imports the "given" method from RestAssured to start a request
import static io.restassured.RestAssured.given;

public class TestFirstAPI {

    // Base URL where your API is running
    String url = "http://localhost:8080";

    // Creates a request specification with the base URL
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void testSuccessFirstAPI(){

        // Endpoint path to be tested
        String path = "/api/primeiraApi";

        // Starts the request
        given()
                .spec(requestSpecification) // Uses the specification with the base URL
                .log().all()                // Logs the entire request
                .when()
                .get(path)                  // Executes the GET method on the endpoint
                .then()
                .log().all()                // Logs the entire response
                .assertThat()               // Starts validations
                .statusCode(200)            // Validates that the HTTP status is 200
                .body(Matchers.containsString("api com sucesso!!!")); // Validates that the body contains the expected text
    }

    @Test
    public void testQueryParamFirstAPIv1(){

        // Endpoint path
        String path = "/api/primeiraApiV1";

        // Value to be sent in the query param
        String queryParamText = "teste";

        given()
                .spec(requestSpecification)             // Uses the base URL
                .queryParam("palavra", queryParamText)  // Sends the query param ?palavra=teste
                .log().all()
                .when()
                .get(path)                              // Executes GET
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(queryParamText)); // Validates that the response contains the sent value
    }

    @Test
    public void testPathParamFirstAPIv2(){

        // Value to be sent in the path param
        String pathParamText = "testes";

        // Builds the endpoint with the path param
        String path = "/api/primeiraApiV2/" + pathParamText;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)                              // GET with path param
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(pathParamText)); // Validates that the response contains the path param
    }

    @Test
    public void testQueryParamOdd(){

        // Endpoint
        String path = "/exercicios/parOuImpar";

        // Number sent in the query param
        Number chosenNumber = 3;

        // Expected result
        String expectedResult = "impar";

        given()
                .spec(requestSpecification)
                .queryParam("numero", chosenNumber) // Sends ?numero=3
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(expectedResult)); // Validates that the API returned "impar"
    }

    @Test
    public void testQueryParamEven(){

        String path = "/exercicios/parOuImpar";
        Number chosenNumber = 200;
        String expectedResult = "par";

        given()
                .spec(requestSpecification)
                .queryParam("numero", chosenNumber) // Sends ?numero=200
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(expectedResult)); // Validates that the API returned "par"
    }

    @Test
    public void testPathParamSalary(){

        // Value sent in the path param
        Number chosenSalary = 6000;

        // Builds the endpoint
        String path = "/exercicios/calculaSalario/" + chosenSalary;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path) // GET with path param
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("Salario liquido = 4379.36")); // Validates the calculation
    }

    @Test
    public void testQueryParamCPF(){

        String path = "/exercicios/validarCpf";
        String chosenCpf = "15097745728";
        String expectedResult = "Valido";

        given()
                .spec(requestSpecification)
                .queryParam("cpf", chosenCpf) // Sends ?cpf=15097745728
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(expectedResult)); // Validates that the CPF is valid
    }
}