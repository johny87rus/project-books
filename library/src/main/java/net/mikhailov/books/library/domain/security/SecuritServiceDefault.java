package net.mikhailov.books.library.domain.security;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.security.authority.Authority;
import net.mikhailov.books.library.domain.security.authority.AuthorityRepository;
import net.mikhailov.books.library.domain.security.authority.AuthorityType;
import net.mikhailov.books.library.domain.security.user.User;
import net.mikhailov.books.library.domain.security.user.UserDetailsDecorator;
import net.mikhailov.books.library.domain.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Дефолтная реализация
 *
 * @author Mikhailov Evgenii
 */
@Service
@RequiredArgsConstructor
public class SecuritServiceDefault implements SecurityService {
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    public static final String ADMIN_NAME = "admin";
    private final PasswordEncoder encoder;

    @Value("#{environment.ADMIN_PASS}:admin")
    private String password;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void createDefaultUser() {
        Optional<User> foundAdmin = userRepository.findUserByName(ADMIN_NAME);
        User admin;
        if (foundAdmin.isEmpty()) {
            var adminEntity = new User().setName(ADMIN_NAME).setPassword(encoder.encode(password));
            admin = userRepository.save(adminEntity);
        } else {
            admin = foundAdmin.get();
        }

        var authorities = new HashSet<Authority>();
        for (AuthorityType type : AuthorityType.values()) {
            var foundAuthority = authorityRepository.findAuthorityByAuthorityType(type);
            if (foundAuthority.isEmpty()) {
                authorities.add(authorityRepository.save(new Authority().setAuthorityType(type)));
            } else {
                authorities.add(foundAuthority.get());
            }
        }

        admin.setAuthorities(authorities);
        authorities.forEach(it -> it.setUsers(Set.of(admin)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsDecorator(user);
    }
}
