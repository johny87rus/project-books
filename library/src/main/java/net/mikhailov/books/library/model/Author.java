package net.mikhailov.books.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "author")
@SequenceGenerator(name = Author.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class Author {
    public static final String SEQUENCE_NAME = "author_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}
