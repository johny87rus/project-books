package net.mikhailov.books.library.domain.author;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Фабрика по созданию авторов
 */
@Validated
public interface AuthorFactory {
    /**
     * Создает автора из информации
     * @param authorInfo - информация об авторе
     * @return автор
     */
    Author build(@Valid @NotNull AuthorInfo authorInfo);

    /**
     * Обновляет значения полей в авторе
     * @param author - обновляемый автор
     * @param authorInfo - источник информации
     */
    void setValues(@Valid @NotNull Author author, @Valid @NotNull AuthorInfo authorInfo);

    /**
     * Копирует автора
     * @param origin - оригинал
     * @return - копия
     */
    default Author copy(@Valid @NotNull Author origin) {
        return build(origin);
    }
}
