package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.Author;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Evgenii Mikhailov
 */
public class AuthorRepositoryTest extends PostgreContainerTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void test() {
        Author author = new Author();
        author.setFirstname("TestName");
        author.setLastname("TestLastName");
        authorRepository.save(author);
        Assert.assertNotNull(author.getId());
    }
}
