package net.mikhailov.books.library.domain.isbnqueue.providers.itbook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItBook {

    private String title;
    private String isbn13;
    private String price;
    private String image;
    private String authors;
    private String publisher;
    private String language;
    private String desc;

}
