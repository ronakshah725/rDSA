package org.ronak.oop.exampledesigns.LibraryLLD;

import java.time.LocalDate;
import java.util.List;

public interface Catalog {
    List<Book> search(String title,String author, LocalDate date);
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByDate(LocalDate date);

    void addBook(Book book);
    void removeBook(String bookId);
}
