package net.mikhailov.books.library.api;

/**
 * @author Mikhailov Evgenii
 */

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.mikhailov.books.library.repository.PostgreContainerTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тест API Авторы")
class AuthorApiTest extends PostgreContainerTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Создание автора")
    void testCreateAuthor() {
        String authorBody = "{\"name\" : \"nameCreateTest\",\"surname\" : \"surNameCreateTest\"}";
        given().contentType(ContentType.JSON).body(authorBody).when().post("/authors").then()
                .statusCode(200)
                .body("name", is("nameCreateTest"))
                .body("surname", is("surNameCreateTest"))
                .body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Изменение автора")
    void testPutAuthor() {
        String authorBody = "{\"name\" : \"namePutTest\",\"surname\" : \"surNamePutTest\"}";
        JsonPath jsonPath = given().contentType(ContentType.JSON).body(authorBody).when().post("/authors").jsonPath();

        Integer id = jsonPath.get("id");
        authorBody = "{\"name\" : \"namePutTest2\",\"surname\" : \"surNamePutTest2\"}";
        given().contentType(ContentType.JSON).body(authorBody).when().put("/authors/" + id).then()
                .statusCode(200)
                .body("name", is("namePutTest2"))
                .body("surname", is("surNamePutTest2"))
                .body("id", is(id));
    }

    @Test
    @DisplayName("Список авторов")
    void testGetAuthor() {
        //TODO
    }


}
