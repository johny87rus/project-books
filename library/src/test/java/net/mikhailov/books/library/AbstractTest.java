package net.mikhailov.books.library;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.mikhailov.books.library.domain.security.SecuritServiceDefault;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(SecuritServiceDefault.ADMIN_NAME)
public abstract class AbstractTest {
    @Value("#{environment.ADMIN_PASS}:admin")
    private String password;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    protected RequestSpecification getRestAssuredAuthentificated() {

        /**
         * TEST PIPELINE
         */
        var sessionId = given().auth().preemptive().basic("admin", password).contentType(ContentType.JSON).
                when().get("/").
                then().log().all().extract().response().getSessionId();
        var csrfToken = given().sessionId(sessionId).contentType(ContentType.JSON).
                when().get("/").
                then().log().all().extract().response().cookie("XSRF-TOKEN");
        return given().sessionId(sessionId)
                .cookie("XSRF-TOKEN", csrfToken)
                .header("X-XSRF-TOKEN", csrfToken);
    }
}
