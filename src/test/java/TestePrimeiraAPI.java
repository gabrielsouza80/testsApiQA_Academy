// Importa a classe principal do RestAssured, usada para fazer requisições HTTP
import io.restassured.RestAssured;

// Importa o construtor de especificações de requisição (RequestSpecBuilder)
import io.restassured.builder.RequestSpecBuilder;

// Importa a interface RequestSpecification, que guarda configurações padrão da requisição
import io.restassured.specification.RequestSpecification;

// Importa Matchers do Hamcrest, usados para validar respostas
import org.hamcrest.Matchers;

// Importa a anotação @Test do JUnit para marcar métodos de teste
import org.junit.Test;

// Importa o método "given" do RestAssured para iniciar a requisição
import static io.restassured.RestAssured.given;

public class TestePrimeiraAPI {

    // URL base onde sua API está rodando
    String url = "http://localhost:8080";

    // Cria uma especificação de requisição com a URL base
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void testComSucessoPrimeiraAPI(){

        // Caminho do endpoint que será testado
        String path = "/api/primeiraApi";

        // Inicia a requisição
        given()
                .spec(requestSpecification) // Usa a especificação com a URL base
                .log().all()                // Loga tudo da requisição
                .when()
                .get(path)                  // Executa o método GET no endpoint
                .then()
                .log().all()                // Loga tudo da resposta
                .assertThat()               // Inicia as validações
                .statusCode(200)            // Valida que o status HTTP é 200
                .body(Matchers.containsString("api com sucesso!!!")); // Valida que o corpo contém o texto esperado
    }

    @Test
    public void testComQueryParamPrimeiraAPIv1(){

        // Caminho do endpoint
        String path = "/api/primeiraApiV1";

        // Valor que será enviado no query param
        String txtqueryParam = "teste";

        given()
                .spec(requestSpecification)         // Usa a URL base
                .queryParam("palavra", txtqueryParam) // Envia o query param ?palavra=teste
                .log().all()
                .when()
                .get(path)                          // Faz o GET
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(txtqueryParam)); // Valida que a resposta contém o valor enviado
    }

    @Test
    public void testComPathParamPrimeiraAPIv2(){

        // Valor que será enviado no path param
        String txtpathParam = "testes";

        // Monta o endpoint com o path param
        String path = "/api/primeiraApiV2/" + txtpathParam;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)                          // GET com path param
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(txtpathParam)); // Valida que o retorno contém o path param
    }

    @Test
    public void testComQueryParamImpar(){

        // Endpoint
        String path = "/exercicios/parOuImpar";

        // Número enviado no query param
        Number numeroEscolhido = 3;

        // Resultado esperado
        String Resultado = "impar";

        given()
                .spec(requestSpecification)
                .queryParam("numero", numeroEscolhido) // Envia ?numero=3
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(Resultado)); // Valida que a API retornou "impar"
    }

    @Test
    public void testComQueryParamPar(){

        String path = "/exercicios/parOuImpar";
        Number numeroEscolhido = 200;
        String Resultado = "par";

        given()
                .spec(requestSpecification)
                .queryParam("numero", numeroEscolhido) // Envia ?numero=200
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(Resultado)); // Valida que retornou "par"
    }

    @Test
    public void testComPathParamSalario(){

        // Valor enviado no path param
        Number salarioEscolhido = 6000;

        // Monta o endpoint
        String path = "/exercicios/calculaSalario/" + salarioEscolhido;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path) // GET com path param
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("Salario liquido = 4379.36")); // Valida o cálculo
    }

    @Test
    public void testComQueryParamCPF(){

        String path = "/exercicios/validarCpf";
        String CpfEscolhido = "15097745728";
        String Resultado = "Valido";

        given()
                .spec(requestSpecification)
                .queryParam("cpf", CpfEscolhido) // Envia ?cpf=15097745728
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(Resultado)); // Valida que o CPF é válido
    }
}