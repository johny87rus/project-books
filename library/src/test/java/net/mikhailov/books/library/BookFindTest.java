package net.mikhailov.books.library;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Evgenii Mikhailov
 */
class BookFindTest {
    private static final String URL = "https://www.bookfinder.com/search/?author=&title=&lang=en&new_used=*&destination=ru&currency=USD&mode=basic&st=sr&ac=qr&isbn=";

    @ParameterizedTest
    @CsvFileSource(resources = "/findBookTest.csv", delimiter = ':')
    void test(String input, String expected, String expectedDescription) throws IOException {
        Document document = Jsoup
                .connect(URL+input)
                .method(Connection.Method.GET).get();
        Assertions.assertEquals(expected, document.select("#describe-isbn-title").text(), () -> "Название книги не соответствует ISBN");
        if (Objects.nonNull(expectedDescription)) {
            Assertions.assertEquals(expectedDescription, document.select("#bookSummary").text(), () -> "Description не соответствует ISBN");
        }
    }
}
