import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class TestePrimeiraAPI {

    @Test
    public void testComSucessoPrimeiraAPI(){

        String url = "http://localhost:8080/api/primeiraApi";

        RestAssured.given()
                            .log().all()
                    .when()
                            .get(url)
                    .then()
                            .log().all()
                            .assertThat()
                            .statusCode(200)
                            .body(Matchers.containsString("api com sucesso!!!"));
    }

    @Test
    public void testComQueryParamPrimeiraAPIv1(){

        String url = "http://localhost:8080/api/primeiraApiV1";
        String txtqueryParam = "teste";

        RestAssured.given()
                .queryParam("palavra", txtqueryParam)
                            .log().all()
                    .when()
                            .get(url)
                    .then()
                            .log().all()
                            .assertThat()
                            .statusCode(200)
                            .body(Matchers.containsString(txtqueryParam));
    }

    @Test
    public void testComPathParamPrimeiraAPIv2(){

        String txtpathParam = "testes";
        String url = "http://localhost:8080/api/primeiraApiV2/"+txtpathParam;

        RestAssured.given()
                            .log().all()
                    .when()
                            .get(url)
                    .then()
                            .log().all()
                            .assertThat()
                            .statusCode(200)
                            .body(Matchers.containsString(txtpathParam));
    }

    @Test
    public void testComQueryParamImpar(){

        String url = "http://localhost:8080/exercicios/parOuImpar";
        Number numeroEscolhido = 3;
        String Resultado = "impar";

        RestAssured.given()
                .queryParam("numero", numeroEscolhido)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(Resultado));
    }

    @Test
    public void testComQueryParamPar(){

        String url = "http://localhost:8080/exercicios/parOuImpar";
        Number numeroEscolhido = 200;
        String Resultado = "par";

        RestAssured.given()
                .queryParam("numero", numeroEscolhido)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(Resultado));
    }

    @Test
    public void testComPathParamSalario(){
        Number salarioEscolhido = 6000;
        String url = "http://localhost:8080/exercicios/calculaSalario/"+ salarioEscolhido;

        RestAssured.given()
                            .log().all()
                    .when()
                            .get(url)
                    .then()
                            .log().all()
                            .assertThat()
                            .statusCode(200)
                            .body(Matchers.containsString("Salario liquido = 4379.36"));
    }
}
