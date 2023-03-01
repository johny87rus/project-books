package net.mikhailov.books.library;

import jakarta.validation.ConstraintViolation;
import net.mikhailov.books.library.validators.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TestValidators extends AbstractTest {
    @Autowired
    protected SpringValidatorAdapter validator;

    protected <T> void valid(T object, Class<?> group, String propertyName, String message) {
        Set<ConstraintViolation<T>> errors = validator.validate(object, group);
        assertEquals(1, errors.size());
        ConstraintViolation<T> error = errors.iterator().next();
        assertEquals(propertyName, error.getPropertyPath().toString());
        assertEquals(message, error.getMessage());
    }

    protected Class<?> getGroup(String strGroup) {
        switch (strGroup) {
            case "create":
                return ValidGroup.Create.class;
            case "update":
                return ValidGroup.Update.class;
            case "delete":
                return ValidGroup.Delete.class;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected String makeString(String name) {
        if (name == null) return null;
        int p = name.indexOf('*');
        if (p < 0) {
            return name;
        }
        return name.substring(0, p).repeat(Integer.parseInt(name.substring(p + 1)));
    }
}
