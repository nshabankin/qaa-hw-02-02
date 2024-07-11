package ru.netlogy.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @Test
    public void ShouldPost() {
        // Given - When - Then
        // Предусловия
        java.io.File data = new File("src/test/resources/data.json");
        given()
                .baseUri("https://postman-echo.com")
                .body(data) // отправляемые данные в формате json
                .contentType("application/json; charset=UTF-8") // кодировка для латиницы

                // Выполняемые действия
                .when()
                .post("/post")

                // Проверки
                .then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body("json.house.room[0].name", equalTo("Bedroom"))
                .body("json.house.room[2].appliance", equalTo("microwave"))
                .body("json.house.garden.area", equalTo(150))
        ;
    }
}