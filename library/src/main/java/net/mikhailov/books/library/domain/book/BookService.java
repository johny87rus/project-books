package net.mikhailov.books.library.domain.book;

import net.mikhailov.books.model.ISBNResultList;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BookService {
    /**
     * Получение списка книг
     * @return список книг
     */
    List<Book> getAllBooks();

    /**
     * Создание книги
     * @param bookInfo - информация о книге
     * @return книга
     */
    Book postBook(BookInfo bookInfo);

    /**
     * Импорт книг по isbn
     * @param isbnList - список isbn
     * @return - результат импорта
     */
    ISBNResultList initBooks(List<String> isbnList);

    /**
     * Удалить книгу по идентификатору
     */
    void deleteBook(@NotNull Long bookId);
}
