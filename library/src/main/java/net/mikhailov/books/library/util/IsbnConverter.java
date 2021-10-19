package net.mikhailov.books.library.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mikhailov Evgenii
 */
@Component
public class IsbnConverter {
    public Long convertFromString(String isbn) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(isbn);
        StringBuilder sb = new StringBuilder();
        while(m.find()) {
            sb.append(m.group());
        }
        return Long.valueOf(sb.toString());
    }
}
