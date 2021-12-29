package net.mikhailov.books.library.domain.isbnqueue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mikhailov Evgenii
 */
public interface IsbnConverter {
    static Long convertFromString(String isbn) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(isbn);
        StringBuilder sb = new StringBuilder();
        while(m.find()) {
            sb.append(m.group());
        }
        return Long.valueOf(sb.toString());
    }
}
