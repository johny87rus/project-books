package net.mikhailov.books.library.domain.security.authority;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий прав
 * @author Mikhailov Evgenii
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    /**
     * Поиск права по типу
     */
    Optional<Authority> findAuthorityByAuthorityType(AuthorityType authorityType);
}
