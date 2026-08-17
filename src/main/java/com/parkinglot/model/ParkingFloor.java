package com.parkinglot.model;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;

import java.util.List;

/*
Iterates through its own spots and find if any is available
 */
public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpotList;

    public ParkingFloor(List<ParkingSpot> parkingSpotList, int floorNumber) {
        this.parkingSpotList = parkingSpotList;
        this.floorNumber = floorNumber;
    }

    public ParkingSpot findavailableSpot(VehicleType vehicleType){
        SpotType requiredType = getSpotTypeForVehicle(vehicleType);
        for (ParkingSpot spot : parkingSpotList) {
            if (!spot.isOccupied() && spot.getSpotType() == requiredType) {
                return spot;
            }
        }
        return null;
    }

    private SpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return SpotType.SMALL;
            case CAR:
                return SpotType.MEDIUM;
            case TRUCK:
                return SpotType.LARGE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }

    public void freeSpot(ParkingSpot parkingSpot) {
        for (ParkingSpot spot : parkingSpotList) {
            if (spot.getSpotID().equals(parkingSpot.getSpotID())) {
                spot.unparkVehicle();
                return;
            }
        }
    }

    public int getAvailableSpotCount(SpotType spotType) {
        int count = 0;
        for(ParkingSpot spot: parkingSpotList) {
            if(!spot.isOccupied() && spot.getSpotType() == spotType){
                count++;
            }
        }
        return count;
    }
}