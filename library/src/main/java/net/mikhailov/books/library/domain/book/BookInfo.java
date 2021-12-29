package net.mikhailov.books.library.domain.book;

import net.mikhailov.books.library.validators.ValidGroup;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Информация о книге
 */
@Validated
public interface BookInfo {

    /**
     * Индентификатор книги
     * @return идентификатор книги
     */
    @Null(groups = {ValidGroup.Create.class}, message = "{validation.BookInfo.create.id.null}")
    @NotNull(groups = {ValidGroup.Update.class, ValidGroup.Delete.class}, message = "{validation.BookInfo.create.id.notNull}")
    Long getId();

    /**
     * Название книги
     * @return название книги
     */
    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class}, message = "{validation.BookInfo.create.title.notNull}")
    String getTitle();

    /**
     * ISBN книги
     * @return цифровой isbn книги
     */
    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class}, message = "{validation.BookInfo.create.isbn.notNull}")
    Long getIsbn();

    /**
     * Описание книги
     * @return описание книги
     */
    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class}, message = "{validation.BookInfo.create.desc.notNull}")
    String getDescription();


    /**
     * URL обложки
     * @return URL обложки
     */
    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class}, message = "{validation.BookInfo.create.url.notNull}")
    String getImageurl();



}
