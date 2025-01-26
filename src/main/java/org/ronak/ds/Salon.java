package org.ronak.ds;

import java.util.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Stylist {
    private String name;
    private Customer currentCustomer;
    private LocalTime busyUntil;
    private boolean hasGoneHome;

    public Stylist(String name) {
        this.name = name;
        this.hasGoneHome = false;
    }

    public String getName() {
        return name;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public LocalTime getBusyUntil() {
        return busyUntil;
    }

    public void setBusyUntil(LocalTime busyUntil) {
        this.busyUntil = busyUntil;
    }

    public boolean hasGoneHome() {
        return hasGoneHome;
    }

    public void setHasGoneHome(boolean hasGoneHome) {
        this.hasGoneHome = hasGoneHome;
    }
}

class Customer {
    private int id;
    private String name;

    public Customer(int id) {
        this.id = id;
        this.name = "Customer-" + id;
    }

    public String getName() {
        return name;
    }
}

class Salon {
    private Queue<Stylist> availableStylists;
    private Queue<Stylist> busyStylists;
    private Queue<Customer> waitingCustomers;
    private int nextCustomerId;
    private LocalTime currentTime;
    private LocalTime closingTime;
    private DateTimeFormatter formatter;

    public Salon() {
        availableStylists = new LinkedList<>();
        busyStylists = new LinkedList<>();
        availableStylists.add(new Stylist("Anne"));
        availableStylists.add(new Stylist("Ben"));
        availableStylists.add(new Stylist("Carol"));
        availableStylists.add(new Stylist("Derek"));

        waitingCustomers = new LinkedList<>();
        nextCustomerId = 1;
        currentTime = LocalTime.of(9, 0);
        closingTime = LocalTime.of(17, 0);
        formatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    private void log(String message) {
        System.out.println(currentTime.format(formatter) + " " + message);
    }

    private void updateStylistQueues() {
        Queue<Stylist> tempQueue = new LinkedList<>();
        while (!busyStylists.isEmpty()) {
            Stylist stylist = busyStylists.poll();
            if (stylist.getBusyUntil() != null && !stylist.getBusyUntil().isAfter(currentTime)) {
                if (stylist.getCurrentCustomer() != null) {
                    handleStylistFinished(stylist);
                }
                if (!stylist.hasGoneHome()) {
                    availableStylists.offer(stylist);
                }
            } else {
                tempQueue.offer(stylist);
            }
        }
        busyStylists = tempQueue;
    }

    private void handleNewCustomer() {
        Customer customer = new Customer(nextCustomerId++);
        log(customer.getName() + " entered");
        
        updateStylistQueues();
        
        if (!availableStylists.isEmpty()) {
            Stylist stylist = availableStylists.poll();
            startHaircut(stylist, customer);
            busyStylists.offer(stylist);
        } else {
            waitingCustomers.offer(customer);
        }
    }

    private void startHaircut(Stylist stylist, Customer customer) {
        stylist.setCurrentCustomer(customer);
        stylist.setBusyUntil(currentTime.plusMinutes(30));
        log(stylist.getName() + " started cutting " + customer.getName() + "'s hair");
    }

    private void handleStylistFinished(Stylist stylist) {
        log(stylist.getName() + " ended cutting " + stylist.getCurrentCustomer().getName() + "'s hair");
        stylist.setCurrentCustomer(null);

        if (!currentTime.isBefore(closingTime)) {
            log(stylist.getName() + " went home");
            stylist.setHasGoneHome(true);
        } else if (!waitingCustomers.isEmpty()) {
            Customer nextCustomer = waitingCustomers.poll();
            startHaircut(stylist, nextCustomer);
            busyStylists.offer(stylist);
        } else {
            availableStylists.offer(stylist);
        }
    }

    public void simulate() {
        log("Hair salon opened");

        while (true) {
            // Find next event time
            LocalTime nextEventTime = null;
            
            // Time for next customer arrival (every 7 minutes)
            if (currentTime.isBefore(closingTime)) {
                LocalTime nextCustomerTime = currentTime.plusMinutes(7);
                nextEventTime = nextCustomerTime;
            }

            // Check stylist completion times
            for (Stylist stylist : busyStylists) {
                if (stylist.getBusyUntil() != null && 
                    (nextEventTime == null || stylist.getBusyUntil().isBefore(nextEventTime))) {
                    nextEventTime = stylist.getBusyUntil();
                }
            }

            // If no more events, end simulation
            if (nextEventTime == null && busyStylists.isEmpty()) {
                while (!waitingCustomers.isEmpty()) {
                    log(waitingCustomers.poll().getName() + " leaves furious");
                }
                log("Hair salon closed");
                break;
            }

            // Advance time and handle events
            currentTime = nextEventTime;
            
            updateStylistQueues();

            // Handle new customer arrival
            if (currentTime.isBefore(closingTime) && 
                currentTime.getMinute() % 7 == 0 && 
                currentTime.getHour() * 60 + currentTime.getMinute() >= 9 * 60) {
                handleNewCustomer();
            }
        }
    }

    public static void main(String[] args) {
        Salon salon = new Salon();
        salon.simulate();
    }
}