package com.parkinglot.model;

import com.parkinglot.enums.SpotType;
import com.parkinglot.enums.VehicleType;

public class ParkingSpot {

    private String spotID;
    private SpotType spotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(SpotType spotType, String spotID) {
        this.spotType = spotType;
        this.spotID = spotID;
    }

    ///  To park a vehicle
    public boolean parkVehicle(Vehicle vehicle) {
        if(isOccupied) {
            return false;
        }
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    ///  To unpark a vehicle
    public Vehicle unparkVehicle() {
        if(!isOccupied) {
            return null;
        }
        Vehicle vehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        this.isOccupied = false;
        return vehicle;
    }

    public String getSpotID() {
        return spotID;
    }

    public void setSpotID(String spotID) {
        this.spotID = spotID;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

}