import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestApi_Futebol {

    String url = "https://free-api-live-football-data.p.rapidapi.com";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void testAPIfutebolBrazil(){

        String path = "/football-get-all-countries";

        // findAll retorna uma lista → então o tipo deve ser List<String>
        String nomes =
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

        System.out.println(nomes);
    }

    @Test
    public void testAPIresponcefutebolBrazil(){

        // Caminho do endpoint
        String path = "/football-get-all-countries";

        // Response é o tipo correto para usar .path()
        io.restassured.response.Response response =
                given()
                        // Headers obrigatórios do RapidAPI
                        .header("X-RapidAPI-Key","a246d7caabmsha5906de41ecca26p1afd2cjsn352845771666")
                        .header("X-RapidAPI-Host","free-api-live-football-data.p.rapidapi.com")

                        // Usa a URL base configurada no RequestSpecification
                        .spec(requestSpecification)

                        // Loga toda a requisição
                        .log().all()

                        .when()
                        // Executa o GET
                        .get(path)

                        .then()
                        // Loga toda a resposta
                        .log().all()

                        // Valida status HTTP
                        .statusCode(200)

                        // Extrai a resposta como objeto Response
                        .extract()
                        .response();

        // Variáveis para armazenar os primeiros países da lista
        String primeiroPais, segundoPais, terceiroPais, quartoPais;

        // Caminho JSON correto baseado no JSON que você enviou
        primeiroPais = response.path("response.countries[0].name");
        segundoPais  = response.path("response.countries[1].name");
        terceiroPais = response.path("response.countries[2].name");
        quartoPais   = response.path("response.countries[3].name");

        // Imprime os resultados
        System.out.println("Primeiro país: " + primeiroPais);
        System.out.println("Segundo país: " + segundoPais);
        System.out.println("Terceiro país: " + terceiroPais);
        System.out.println("Quarto país: " + quartoPais);
    }
}
