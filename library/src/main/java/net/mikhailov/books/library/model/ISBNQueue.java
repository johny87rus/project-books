package net.mikhailov.books.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Mikhailov Evgenii
 */
@Entity
@Getter
@Setter
@Table(name = "isbnqueue")
@SequenceGenerator(name = ISBNQueue.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class ISBNQueue {
    public static final String SEQUENCE_NAME = "isbnqueue_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "isbn", nullable = false)
    private String isbn;
}
