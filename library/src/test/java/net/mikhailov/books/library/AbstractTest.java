package net.mikhailov.books.library;

import net.mikhailov.books.library.domain.security.SecuritServiceDefault;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(SecuritServiceDefault.ADMIN_NAME)
public abstract class AbstractTest {
}
