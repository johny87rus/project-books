package net.mikhailov.books.library.domain.author;

import net.mikhailov.books.library.validators.ValidGroup;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Информация о авторе
 */
@Validated
public interface AuthorInfo {
    /**
     * Идентификатор автора
     * @return идентификатор
     */
    @Null(groups = {ValidGroup.Create.class}, message = "{validation.AuthorInfo.create.id.null}")
    @NotNull(groups = {ValidGroup.Update.class, ValidGroup.Delete.class}, message = "{validation.AuthorInfo.create.id.notNull}")
    Long getId();


    /**
     * Имя автора
     * @return имя автора
     */
    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class}, message = "{validation.AuthorInfo.create.firstName.notNull}")
    String getFirstname();

    /**
     * Фамилия автора
     * @return фамилия автора
     */
    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class}, message = "{validation.AuthorInfo.create.lastName.notNull}")
    String getLastname();
}
