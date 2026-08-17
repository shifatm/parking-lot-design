package com.parkinglot.model;


import com.parkinglot.enums.VehicleType;
import com.parkinglot.strategy.FeeCalculationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
Iterates through floors and asks each floor to find a spot
 */
public class ParkingLot {

    private static ParkingLot instance; // holds the singleton instance

    private String name;
    private List<ParkingFloor> floors;
    //private ParkingStrategy parkingStrategy;

    private FeeCalculationStrategy feeCalculationStrategy;

    private ParkingLot() {
        this.name = "TootanKhamun";
        this.floors = new ArrayList<>();
    } // prevent from calling "new ParkingLot()". Will give compilation error


    ///  Method is static since we do not want an object of the class to be created in order to call this method.
    /// Object of the class can not even be created since the constructor we have is private.
    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
    }

    public void setFeeCalculationStrategy(FeeCalculationStrategy feeCalculationStrategy) {
        this.feeCalculationStrategy = feeCalculationStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {

        for(ParkingFloor floor: floors) {
            ParkingSpot spot = floor.findavailableSpot(vehicle.getVehicleType());
            if(spot != null) {
                spot.parkVehicle(vehicle);
                return  new Ticket(vehicle, spot);
            }
        }
        return null;
    }

    public Payment unparkVehicle(Ticket ticket) {

        if(ticket == null) {
            return null;
        }

        ParkingSpot spot = ticket.getParkingSpot();
        LocalDateTime exitTime = LocalDateTime.now();
        spot.unparkVehicle();

        for(ParkingFloor floor: floors) {
            floor.freeSpot(spot);
        }

        double amount = feeCalculationStrategy.calculateFee(ticket, exitTime);
        return new Payment(ticket, exitTime, amount);

    }

    public boolean isFull(VehicleType vehicleType) {

        for(ParkingFloor floor: floors) {
            ParkingSpot spot = floor.findavailableSpot(vehicleType);
            if(spot != null) {
                return false;
            }
        }
        return true;
    }



}