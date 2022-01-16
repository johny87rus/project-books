package net.mikhailov.books.library.domain.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервис по пользователям и правам
 * @author Mikhailov Evgeni
 */
public interface SecurityService extends UserDetailsService {

    /**
     * Создает дефолтного юзера если нужно
     */
    void createDefaultUser();
}
