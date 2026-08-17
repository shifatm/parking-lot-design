package com.parkinglot.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private String id;
    private Ticket ticket;
    private LocalDateTime exitTime;
    private double amount;


    public Payment(Ticket ticket, LocalDateTime exitTime, double amount) {
        this.id = UUID.randomUUID().toString();
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmount() {
        return amount;
    }
}