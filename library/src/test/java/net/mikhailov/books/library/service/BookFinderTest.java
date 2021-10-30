package net.mikhailov.books.library.service;

import net.mikhailov.books.library.model.Book;
import net.mikhailov.books.library.model.ISBNQueue;
import net.mikhailov.books.library.repository.AuthorRepository;
import net.mikhailov.books.library.repository.BookRepository;
import net.mikhailov.books.library.repository.ISBNQueueRepository;
import net.mikhailov.books.library.repository.PostgreContainerTest;
import net.mikhailov.books.library.service.providers.BookProvider;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mikhailov Evgenii
 */
@SpringBootTest
@Disabled("Не работает после OpenAPI")
class BookFinderTest extends PostgreContainerTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ISBNQueueRepository isbnQueueRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookProvider bookProvider;


    @Test
    @Transactional
    void shouldEnrichBook() {
        //Prepare
        BookFinder bookFinder = new BookFinderImpl(bookRepository, isbnQueueRepository, authorRepository, bookProvider);
        prepare();


        //Run
        bookFinder.enrichBooks();

        //Check
        Assertions.assertEquals(3, bookRepository.count());
        Iterator<Book> bookIterator = bookRepository.findAll().iterator();
        Book book1 = bookIterator.next();
        assertThat("Vlad", IsEqualIgnoringCase.equalToIgnoringCase(book1.getAuthors().get(0).getFirstname()));
        assertThat("Mihalcea", IsEqualIgnoringCase.equalToIgnoringCase(book1.getAuthors().get(0).getLastname()));
        Book book2 = bookIterator.next();
        assertThat("Gianni", IsEqualIgnoringCase.equalToIgnoringCase(book2.getAuthors().get(0).getFirstname()));
        assertThat("Rodari", IsEqualIgnoringCase.equalToIgnoringCase(book2.getAuthors().get(0).getLastname()));
        Book book3 = bookIterator.next();
        assertThat("Doug", IsEqualIgnoringCase.equalToIgnoringCase(book3.getAuthors().get(5).getFirstname()));
        assertThat("Lea", IsEqualIgnoringCase.equalToIgnoringCase(book3.getAuthors().get(5).getLastname()));
        Assertions.assertEquals(8, authorRepository.count());
    }

    private void prepare() {
        ISBNQueue isbnQueue1 = new ISBNQueue();
        isbnQueue1.setIsbn("978-9730-228236");
        isbnQueueRepository.save(isbnQueue1);

        ISBNQueue isbnQueue2 = new ISBNQueue();
        isbnQueue2.setIsbn("9785699927326");
        isbnQueueRepository.save(isbnQueue2);

        ISBNQueue isbnQueue3 = new ISBNQueue();
        isbnQueue3.setIsbn("9789730228236");
        isbnQueueRepository.save(isbnQueue3);

        ISBNQueue isbnQueue4 = new ISBNQueue();
        isbnQueue4.setIsbn("9780321349606");
        isbnQueueRepository.save(isbnQueue4);


    }
}
