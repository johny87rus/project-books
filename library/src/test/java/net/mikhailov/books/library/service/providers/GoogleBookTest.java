package net.mikhailov.books.library.service.providers;

import net.mikhailov.books.library.service.providers.google.GoogleBookList;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static net.mikhailov.books.library.service.providers.GoogleApiProvider.KEY;
import static net.mikhailov.books.library.service.providers.GoogleApiProvider.URL;

/**
 * @author Evgenii Mikhailov
 */
class GoogleBookTest {
    @Test
    void test() throws IOException {
        long isbn = 9781484256251L;
        String googleApiKey = "";
        RestTemplate restTemplate = new RestTemplate();
        GoogleBookList googleBookList = restTemplate.getForObject(URL + isbn + KEY + googleApiKey, GoogleBookList.class);
    }
}
