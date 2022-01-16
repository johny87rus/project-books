package net.mikhailov.books.library.domain.book;

/**
 * @author Mikhailov Evgenii
 */

import io.restassured.http.ContentType;
import net.mikhailov.books.library.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тест API Книги")
class BookApiTest extends AbstractTest {
    @Test
    @DisplayName("Создание книги")
    void postBook() {
        var authorBody = "{\"name\" : \"JoshuaForBook\",\"surname\" : \"Bloch\"}";
        var jsonPath = getRestAssuredAuthentificated()
                .contentType(ContentType.JSON).body(authorBody).log().all().when().post("/api/v1/authors").jsonPath();
        var authorId = jsonPath.get("id");

        var bookBody = "{\"title\" : \"JPA AND HIBERNATE\",\"description\" : \"Java Persistence API\", \"isbn\":11231111111, \"imageurl\":\"https://youtube.com/\"" +
                ", \"authors\":[{\"id\":"+ authorId + "}]}";
        getRestAssuredAuthentificated()
                .contentType(ContentType.JSON).body(bookBody).when().post("/api/v1/books").then().statusCode(200);
    }


}
