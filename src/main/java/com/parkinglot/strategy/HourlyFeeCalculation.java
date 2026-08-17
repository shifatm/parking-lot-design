package com.parkinglot.strategy;

import com.parkinglot.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyFeeCalculation implements FeeCalculationStrategy {

    private static final double RATE_PER_HOUR = 20.0;

    @Override
    public double calculateFee(Ticket ticket, LocalDateTime exitTime) {
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);

        // Round up partial hours (e.g., 1h 10m => 2 hours billed)
        long hours = (long) Math.ceil(duration.toMinutes() / 60.0);
        if (hours == 0) {
            hours = 1; // minimum 1 hour charge
        }

        return hours * RATE_PER_HOUR;
    }
}