package net.mikhailov.books.library.domain.author;

/**
 * @author Mikhailov Evgenii
 */

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.mikhailov.books.library.AbstractTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

@DisplayName("Тест API Авторы")
class AuthorApiTest extends AbstractTest {
    @Test
    @DisplayName("Создание автора")
    void testCreateAuthor() {
        String authorBody = "{\"name\" : \"JoshuaPost\",\"surname\" : \"Bloch\"}";
        getRestAssuredAuthentificated().contentType(ContentType.JSON).body(authorBody).when().post("/api/v1/authors").then()
                .statusCode(200)
                .body("name", is("JoshuaPost"))
                .body("surname", is("Bloch"))
                .body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Изменение автора")
    void testPutAuthor() {
        String authorBody = "{\"name\" : \"JoshuaPut\",\"surname\" : \"Bloch\"}";
        JsonPath jsonPath = getRestAssuredAuthentificated().contentType(ContentType.JSON).body(authorBody).when().post("/api/v1/authors").jsonPath();

        Object id = jsonPath.get("id");
        authorBody = "{\"name\" : \"Tom\",\"surname\" : \"Cruz\"}";
        getRestAssuredAuthentificated().contentType(ContentType.JSON).body(authorBody).when().put("/api/v1/authors/" + id).then()
                .statusCode(200)
                .body("name", is("Tom"))
                .body("surname", is("Cruz"))
                .body("id", is(id));
    }
}
