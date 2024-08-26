package dev.maria.automacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class ApiTest {

    @org.junit.Test
    public void testeValidandoNome( ){
        Response response = RestAssured.request(Method.GET,"https://gsi.fly.dev/characters/10");
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());


        assertEquals(200, response.getStatusCode());
        assertThat(response.path("result.name"), equalTo("Noelle"));
    }

    @org.junit.Test
    public void testeValidandoNomePost( ){

        String bodyGI = "{\r\n"
                + "  "nome": "Noelle",\r\n"
                + "  "email": "Noelle@genshin.hoyoverse.com",\r\n"
                + "  "password": "KnightsOfFavonius<3",\r\n"
                + "  "administrador": "false"\r\n"
                + "}";


        Response response =
        given()
        .log().all()
        .contentType(ContentType.JSON)
        .body(bodyGI)
        .when()
            .post("https://serverest.dev/usuarios")
        .then()
        .log().all()
            .extract().response();

        assertEquals(201, response.getStatusCode());
        assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
        assertThat(response.path("nome"), equalTo("Noelle"));
        assertThat(response.path("email"), equalTo("Noelle@genshin.hoyoverse.com"));

    }
@org.junit.Test
    public void testeGetNoelle( ){



        Response response =
        given()
        .log().all()
        .when()
            .get("https://serverest.dev/usuarios?nome=Noelle")
        .then()
        .log().all()
            .extract().response();

        assertEquals(200, response.getStatusCode());
        assertThat(response.path("usuarios[0].nome"), equalTo("Noelle"));
        assertThat(response.path("usuarios[0].email"), equalTo("Noelle@genshin.hoyoverse.com"));

    }
}