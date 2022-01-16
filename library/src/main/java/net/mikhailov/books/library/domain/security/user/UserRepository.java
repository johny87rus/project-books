package net.mikhailov.books.library.domain.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий пользователей
 * @author Mikhailov Evgenii
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск пользователя по имени
     * @param name - имя пользователя
     * @return - пользователь
     */
    Optional<User> findUserByName(String name);
}
