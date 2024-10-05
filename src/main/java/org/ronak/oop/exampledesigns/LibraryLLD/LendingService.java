package org.ronak.oop.exampledesigns.LibraryLLD;

public interface LendingService {

    void checkout(Member member, BookItem bookItem);

    void returnBook(Member member, BookItem bookItem);



}
