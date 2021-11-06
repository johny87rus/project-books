package net.mikhailov.books.library.service.providers.google;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Mikhailov Evgenii
 */
@Getter
@Setter
public class GoogleBookList {
    List<GoogleBook> items;


    @Getter
    @Setter
    public static class GoogleBook {
        private VolumeInfo volumeInfo;
    }

    @Getter
    @Setter
    public static class VolumeInfo {
        private String title;
        private String description;
        private List<IndustryIdentifiers> industryIdentifiers;
        private List<String> authors;
        private ImageLinks imageLinks;
    }

    @Getter
    @Setter
    public static class IndustryIdentifiers {
        private String type;
        private String identifier;
    }


    @Getter
    @Setter
    public static class ImageLinks {
        private String thumbnail;
    }

}
