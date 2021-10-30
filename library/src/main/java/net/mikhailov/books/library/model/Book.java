package net.mikhailov.books.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "book")
@SequenceGenerator(name = Book.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class Book {
    public static final String SEQUENCE_NAME = "book_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "bookid"), inverseJoinColumns = @JoinColumn(name = "authorid"))
    List<Author> authors;

    @Column(name = "isbn", nullable = false)
    private Long isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "imageurl")
    private String imageurl;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                ", description='" + description + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
