package net.mikhailov.books.library.domain.author;

import net.mikhailov.books.library.TestValidators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Проверка работы валидаторов информации о Авторе")
class AuthorValidatorTest extends TestValidators {
    @ParameterizedTest(name = "[{index}] {0}")
    @SuppressWarnings("java:S2699")
    //Отключаем проверку Sonar на наличие assert в тестовом методе, т.к. они прописаны в вызываемом методе
    @CsvFileSource(resources = "/domain/author/author-info-validators-params.csv", numLinesToSkip = 1, delimiterString = ";")
    void validated(String testName, Long id, String first, String last, String strGroup, String propertyName, String message) {
        var authorInfo = makeAuthor(id, first, last);
        valid(authorInfo, getGroup(strGroup), propertyName, message);
    }

    private AuthorInfo makeAuthor(Long id, String first, String last) {
        var author = new Author();
        author.setId(id);
        author.setFirstname(first);
        author.setLastname(last);
        return author;
    }
}
