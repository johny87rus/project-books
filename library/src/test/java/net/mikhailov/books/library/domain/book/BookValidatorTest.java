package net.mikhailov.books.library.domain.book;

import net.mikhailov.books.library.TestValidators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Проверка работы валидаторов информации о Книге")
class BookValidatorTest extends TestValidators {
    @ParameterizedTest(name = "[{index}] {0}")
    @SuppressWarnings("java:S2699")
    //Отключаем проверку Sonar на наличие assert в тестовом методе, т.к. они прописаны в вызываемом методе
    @CsvFileSource(resources = "/domain/book/book-info-validators-params.csv", numLinesToSkip = 1, delimiterString = ";")
    void validated(String testName, Long id, String title, String desc, String url, Long isbn, String strGroup, String propertyName, String message) {
        var bookInfo = makeBook(id, title, desc, url, isbn);
        valid(bookInfo, getGroup(strGroup), propertyName, message);
    }

    private BookInfo makeBook(Long id, String title, String desc, String url, Long isbn) {
        var book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setDescription(desc);
        book.setImageurl(url);
        book.setIsbn(isbn);
        return book;
    }
}
