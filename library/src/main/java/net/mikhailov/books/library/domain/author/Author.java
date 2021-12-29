package net.mikhailov.books.library.domain.author;

import lombok.Getter;
import lombok.Setter;
import net.mikhailov.books.library.domain.book.Book;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "author")
@SequenceGenerator(name = Author.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class Author implements AuthorInfo{
    public static final String SEQUENCE_NAME = "author_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}
