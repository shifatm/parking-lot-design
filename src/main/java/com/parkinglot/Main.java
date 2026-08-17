package com.parkinglot;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.ParkingFloor;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Payment;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.strategy.HourlyFeeCalculation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. Get the singleton parking lot and configure the fee strategy
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.setFeeCalculationStrategy(new HourlyFeeCalculation());

        // 2. Build a floor with a few spots of each type
        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new ParkingSpot(SpotType.SMALL, "S1"));
        spots.add(new ParkingSpot(SpotType.MEDIUM, "M1"));
        spots.add(new ParkingSpot(SpotType.MEDIUM, "M2"));
        spots.add(new ParkingSpot(SpotType.LARGE, "L1"));

        ParkingFloor floor = new ParkingFloor(spots, 1);
        parkingLot.addFloor(floor);

        // 3. Create a vehicle
        Vehicle car = new Vehicle();
        car.setVehicleId("KA-01-1234");
        car.setVehicleType(VehicleType.CAR);

        // 4. Park the vehicle
        System.out.println("Is lot full for CAR? " + parkingLot.isFull(VehicleType.CAR));
        Ticket ticket = parkingLot.parkVehicle(car);

        if (ticket == null) {
            System.out.println("No spot available for the vehicle!");
            return;
        }

        System.out.println("Vehicle parked successfully.");
        System.out.println("Ticket ID   : " + ticket.getTicketID());
        System.out.println("Spot ID     : " + ticket.getParkingSpot().getSpotID());
        System.out.println("Entry Time  : " + ticket.getEntryTime());

        // 5. Unpark the vehicle and get the payment
        Payment payment = parkingLot.unparkVehicle(ticket);

        System.out.println("\nVehicle unparked successfully.");
        System.out.println("Payment ID  : " + payment.getId());
        System.out.println("Exit Time   : " + payment.getExitTime());
        System.out.println("Amount Due  : " + payment.getAmount());
    }
}