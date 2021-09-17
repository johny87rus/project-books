package net.mikhailov.books.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "isbn")
@SequenceGenerator(name = ISBN.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class ISBN {
    public static final String SEQUENCE_NAME = "isbn_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "isbn", nullable = false)
    private Long isbnNumber;

    @ManyToOne
    @JoinColumn(name = "bookid", nullable = false)
    private Book book;


}
