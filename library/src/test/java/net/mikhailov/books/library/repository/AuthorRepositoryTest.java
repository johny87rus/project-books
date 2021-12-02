package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Evgenii Mikhailov
 */
@DataJpaTest
@DisplayName("Тест репозитория Автора")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest extends PostgreContainerTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DisplayName("Тест сохранения автора")
    void shouldSaveAuthorAndReturnId() {
        Author author = new Author();
        author.setFirstname("TestName");
        author.setLastname("TestLastName");
        authorRepository.save(author);
        assertNotNull(author.getId());
        assertTrue(author.getId() >= 1000, () -> "ID должен быть не менее 1000 из-за настроек sequence");
    }
}
