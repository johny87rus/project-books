package net.mikhailov.books.library.controller.mapper;

import net.mikhailov.books.library.domain.author.Author;
import net.mikhailov.books.library.domain.book.BookInfo;
import net.mikhailov.books.model.BookDTOPost;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Адаптер для информации о книге
 */
public class BookInfoAdapter implements BookInfo {
    private Long id;
    private String title;
    private Long isbn;
    private String description;
    private String imageurl;
    private Set<Author> authors;

    public BookInfoAdapter(BookDTOPost bookDTOPost) {
        this.id = bookDTOPost.getId();
        this.title = bookDTOPost.getTitle();
        this.isbn = bookDTOPost.getIsbn().longValue();
        this.description = bookDTOPost.getDescription();
        this.imageurl = bookDTOPost.getImageurl();
        var authorDTOs = bookDTOPost.getAuthors();
        if (Objects.nonNull(authorDTOs)) {
            this.authors = new LinkedHashSet<>();
            authorDTOs.forEach(it -> this.authors.add(new Author().setId(it.getId())));
        }
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Long getIsbn() {
        return isbn;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImageurl() {
        return imageurl;
    }

    @Override
    public Set<Author> getAuthors() {
        return authors;
    }
}
