package net.mikhailov.books.library.config.security;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.security.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Дефолтный юзер - admin
 * @author Mikhailov Evgenii
 */
@Component
@RequiredArgsConstructor
public class SecurityData implements CommandLineRunner {
    private final SecurityService securityService;

    @Override
    public void run(String... args) throws Exception {
        securityService.createDefaultUser();
    }
}
