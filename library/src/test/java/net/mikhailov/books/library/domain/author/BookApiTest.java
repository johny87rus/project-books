package net.mikhailov.books.library.domain.author;

/**
 * @author Mikhailov Evgenii
 */

import io.restassured.RestAssured;
import net.mikhailov.books.library.repository.PostgreContainerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тест API Книги")
class BookApiTest extends PostgreContainerTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Создание книги")
    void postBook() {
        //TODO
    }


}
