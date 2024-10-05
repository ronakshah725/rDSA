package org.ronak.oop.exampledesigns.LibraryLLD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Loan {
    private String id;
    private Member member;
    private BookItem bookItem;
    private LocalDateTime checkoutDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private LoanStatus status;

    List<LoanObserver> observers = new ArrayList<>();

    public void addObserver(LoanObserver observer){
        observers.add(observer);
    }

    public void removeObserver(LoanObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers(){
        observers.forEach(o -> o.update(this));
    }

    public void setStatus(LoanStatus status){
        this.status = status;
        notifyObservers();
    }
}
