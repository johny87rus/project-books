package net.mikhailov.books.library.domain.book;

/**
 * @author Mikhailov Evgenii
 */

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import net.mikhailov.books.library.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithUserDetails;

import static io.restassured.RestAssured.given;

@DisplayName("Тест API Книги")
class BookApiTest extends AbstractTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Создание книги")
    @WithUserDetails("admin")
    void postBook() {
        var authorBody = "{\"name\" : \"JoshuaForBook\",\"surname\" : \"Bloch\"}";
        given().header(new Header("Authorization", "Basic YWRtaW46WmlkYW5l")).contentType(ContentType.JSON).body(authorBody).when().post("/api/v1/authors").then().statusCode(200);
        var jsonPath = given().header(new Header("Authorization", "Basic YWRtaW46WmlkYW5l")).contentType(ContentType.JSON).body(authorBody).when().post("/api/v1/authors").jsonPath();
        var authorId = jsonPath.get("id");

        var bookBody = "{\"title\" : \"JPA AND HIBERNATE\",\"description\" : \"Java Persistence API\", \"isbn\":11231111111, \"imageurl\":\"https://youtube.com/\"" +
                ", \"authors\":[{\"id\":"+ authorId + "}]}";
        var post = given().contentType(ContentType.JSON).body(bookBody).when().post("/api/v1/books").then().statusCode(200);
    }


}
