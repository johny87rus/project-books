package net.mikhailov.books.library.domain.author;

import org.springframework.stereotype.Component;

/**
 * Дефолтная фабрика Авторов
 */
@Component
public class AuthorFactoryDefault implements AuthorFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Author build(AuthorInfo authorInfo) {
        var author = new Author();
        setValues(author, authorInfo);
        return author;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValues(Author author, AuthorInfo authorInfo) {
        author.setId(authorInfo.getId());
        author.setFirstname(authorInfo.getFirstname());
        author.setLastname(authorInfo.getLastname());
    }
}
