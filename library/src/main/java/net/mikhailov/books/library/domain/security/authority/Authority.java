package net.mikhailov.books.library.domain.security.authority;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.mikhailov.books.library.domain.security.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Права пользователя
 * @author Mikhailov Evgenii
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "authorities")
@SequenceGenerator(name = Authority.SEQUENCE_NAME, allocationSize = 1, initialValue = 1000)
public class Authority implements Serializable {
    public static final String SEQUENCE_NAME = "authorities_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority_type")
    private AuthorityType authorityType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "authority_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

}
