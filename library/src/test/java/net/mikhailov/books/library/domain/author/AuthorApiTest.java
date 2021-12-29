package net.mikhailov.books.library.domain.author;

/**
 * @author Mikhailov Evgenii
 */

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.mikhailov.books.library.AbstractTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Тест API Авторы")
class AuthorApiTest extends AbstractTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Создание автора")
    void testCreateAuthor() {
        String authorBody = "{\"name\" : \"JoshuaPost\",\"surname\" : \"Bloch\"}";
        given().contentType(ContentType.JSON).body(authorBody).when().post("/authors").then()
                .statusCode(200)
                .body("name", is("JoshuaPost"))
                .body("surname", is("Bloch"))
                .body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Изменение автора")
    void testPutAuthor() {
        String authorBody = "{\"name\" : \"JoshuaPut\",\"surname\" : \"Bloch\"}";
        JsonPath jsonPath = given().contentType(ContentType.JSON).body(authorBody).when().post("/authors").jsonPath();

        Object id = jsonPath.get("id");
        authorBody = "{\"name\" : \"Tom\",\"surname\" : \"Cruz\"}";
        given().contentType(ContentType.JSON).body(authorBody).when().put("/authors/" + id).then()
                .statusCode(200)
                .body("name", is("Tom"))
                .body("surname", is("Cruz"))
                .body("id", is(id));
    }

    @Test
    @DisplayName("Список авторов")
    void testGetAuthor() {
        //TODO
    }


}
