package org.ronak.oop.exampledesigns.LibraryLLD;

import java.time.LocalDateTime;
import java.util.List;

public class Book {
    private BookId id;
    private List<Author> authors;
    private LocalDateTime publishedDate;
    private List<String> subjects;
}
