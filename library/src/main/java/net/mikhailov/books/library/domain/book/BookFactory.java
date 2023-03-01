package net.mikhailov.books.library.domain.book;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Фабрика по созданию книг
 */
public interface BookFactory {
    /**
     * Создает книгу из информации
     * @param bookInfo - информация о книге
     * @return книга
     */
    Book build(@Valid @NotNull BookInfo bookInfo);

    /**
     * Обновляет значения полей в книге
     * @param book - обновляемая книга
     * @param bookInfo - источник информации
     */
    void setValues(@Valid @NotNull Book book, @Valid @NotNull BookInfo bookInfo);

    /**
     * Копирует книгу
     * @param origin - оригинал
     * @return - копия
     */
    default Book copy(@Valid @NotNull Book origin) {
        return build(origin);
    }
}
