package com.parkinglot.strategy;

import com.parkinglot.model.Ticket;

import java.time.LocalDateTime;

public interface FeeCalculationStrategy {
    double calculateFee(Ticket ticket, LocalDateTime exitTime);
}