package net.mikhailov.books.library.domain.security.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.mikhailov.books.library.domain.security.authority.Authority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Пользователь сервиса
 * @author Mikhailov Evgenii
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@Accessors(chain = true)
@SequenceGenerator(name = User.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class User implements Serializable {
    public static final String SEQUENCE_NAME = "users_seq";


    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
