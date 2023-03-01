package net.mikhailov.books.library.domain.author;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import net.mikhailov.books.library.validators.ValidGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * @author Mikhailov Evgenii
 */
public interface AuthorService {
    /**
     * Добавление автора
     * @param authorInfo - информация об авторе
     * @return автор
     */
    @Validated(ValidGroup.Create.class)
    Author postAuthor(@Valid @NotNull AuthorInfo authorInfo);

    /**
     * Получение списка авторов
     * @return список авторов
     */
    List<Author> getAllAuthors();

    /**
     * Изменение автора
     * @param authorInfo - информация об авторе
     * @return - измененный автор
     */
    @Validated(ValidGroup.Update.class)
    Author putAuthor(@Valid @NotNull AuthorInfo authorInfo);

    /**
     * Получение автора по идентификатору
     * @param id идентификатор автора
     * @return автор
     */
    Author getByID(@Positive @NotNull Long id);

    /**
     * Поиск автора по имени и фамилии
     * @param name имя автора
     * @param surname фамилия автора
     * @return автор
     */
    Optional<Author> findAuthorByNameAndSurname(String name, String surname);
}
