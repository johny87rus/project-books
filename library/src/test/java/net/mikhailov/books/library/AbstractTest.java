package net.mikhailov.books.library;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.mikhailov.books.library.domain.security.SecuritServiceDefault;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(SecuritServiceDefault.ADMIN_NAME)
public abstract class AbstractTest {
    @Value("#{environment.ADMIN_PASS}admin")
    private String password;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    protected RequestSpecification getRestAssuredAuthentificated() {
        return given().auth().preemptive().basic("admin", password).and();
    }
}
