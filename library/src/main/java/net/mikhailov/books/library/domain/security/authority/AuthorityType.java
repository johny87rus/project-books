package net.mikhailov.books.library.domain.security.authority;

import java.io.Serializable;

/**
 * Тип права или роли
 * @author Mikhailov Evgenii
 */
public enum AuthorityType implements Serializable {
    WRITE
    , ROLE_ADMIN
    ;

    public String getRole() {
        var array = this.name().split("^ROLE_*");
        if (array.length > 1) {
            return array[1];
        } else return name();
    }
}
