package net.mikhailov.books.library.service.providers;

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
class BookFinderComTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/findBookTest.csv", delimiter = ':')
    void test(String input, String expectedAuthor, String expectedLang, String expectedTitle, String expectedDescription) throws IOException {
        Document document = Jsoup
                .connect(BookFinderCom.URL +input)
                .method(Connection.Method.GET).get();
        Assertions.assertEquals(expectedTitle, document.select("#describe-isbn-title").text(), () -> "Название книги не соответствует ISBN");
        if (Objects.nonNull(expectedDescription)) {
            Assertions.assertEquals(expectedDescription, document.select("#bookSummary").text(), () -> "Description не соответствует ISBN");
        }
        Assertions.assertEquals(expectedAuthor, document.select("span[itemprop=\"author\"]").text());
        Assertions.assertEquals(expectedLang, document.select("span[itemprop=\"inLanguage\"]").text());
    }
}
