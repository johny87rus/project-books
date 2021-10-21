package net.mikhailov.books.library.service.providers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Evgenii Mikhailov
 */
class AmazonComTest {
    @Test
    void test() throws IOException {
        long isbn = 9785699927326L; //Джельсомино
        Document document = Jsoup
                .connect(AmazonComProvider.URL + isbn)
                .method(Connection.Method.GET).get();
        if (document.select(AmazonComProvider.ROBOTSELECTOR).text().contains("Sorry")) {
            return;
        }
        Assertions.assertEquals("Dzhelsomino v strane Lzhetcov", document.select(AmazonComProvider.TITLESELECTOR).text(), () -> "Название книги не соответствует");
        Assertions.assertEquals("Rodari D.", document.select(AmazonComProvider.AUTHORSELECTOR).text(), () -> "Автор книги не соответствует");
        Assertions.assertEquals("Russian Edition", document.select(AmazonComProvider.LANGSELECTOR).text(), () -> "Язык книги не соответствует");
        Assertions.assertEquals("https://m.media-amazon.com/images/I/515MNoex7wL._AC_UY218_.jpg", document.select(AmazonComProvider.IMAGESELECTOR).attr("src"), () -> "Ссылка на картинку не соответствует");
    }
}
