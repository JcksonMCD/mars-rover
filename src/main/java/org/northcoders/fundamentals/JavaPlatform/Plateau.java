package org.northcoders.fundamentals.JavaPlatform;

import input.layer.PlateauSize;
import input.layer.Position;
import vehicle.AvailableVehicles;
import vehicle.MoonBuggy;
import vehicle.Rover;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plateau {
    public PlateauSize plateauSize;
    List<Vehicle> vehicles = new ArrayList<>();
    List<CrashSite> crashSites = new ArrayList<>();

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }


    public void addVehicle(Position startPosition, AvailableVehicles vehicleType){
            if (((0 <= startPosition.getX()) && (startPosition.getX() <= plateauSize.getMAX_X())) &&
                    (0 <= startPosition.getY()) && (startPosition.getY() <= plateauSize.getMAX_Y())) {
                Vehicle vehicle;
                switch (vehicleType) {
                    case ROVER:
                        vehicle = new Rover(startPosition);
                        this.vehicles.add(vehicle);
                        break;
                    case MOON_BUGGY:
                        vehicle = new MoonBuggy(startPosition);
                        this.vehicles.add(vehicle);
                        break;
                    case FORD_FIESTA:
                        System.out.println("Ford Fiestas cant land on Mars! You crashed... oops :(");
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
                }
                System.out.println(vehicleType + " has successfully landed to position (" + startPosition.getX() + ", " + startPosition.getY() + ") on the plateau.");
            } else {
                throw new IllegalArgumentException("Rover out of bounds for plateau size.");
            }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void moveVehicle(int vehicleNumber) {
        Vehicle currentVehicle = vehicles.get(vehicleNumber);
        switch (currentVehicle.position.getFacing()) {
            case N:
                currentVehicle.position.setY(currentVehicle.position.getY() + 1);
                break;
            case E:
                currentVehicle.position.setX(currentVehicle.position.getX() + 1);
                break;
            case S:
                currentVehicle.position.setY(currentVehicle.position.getY() - 1);
                break;
            case W:
                currentVehicle.position.setX(currentVehicle.position.getX() - 1);
                break;
        }

        // Check if the new position is out of bounds
        if (currentVehicle.position.getX() < 0 || currentVehicle.position.getX() > plateauSize.getMAX_X() ||
                currentVehicle.position.getY() < 0 || currentVehicle.position.getY() > plateauSize.getMAX_Y()) {
            throw new IllegalArgumentException(currentVehicle.getClass().getSimpleName() + " will move out of bounds! Do you want a crash!!");
        }
    }

    public void printPositionOfVehicles(){
        for (int i = 0; i < vehicles.size(); i++){
            System.out.println("Position of " + getVehicles().get(i).getClass().getSimpleName() + " is " + getVehicles().get(i).position.getX() + " " + getVehicles().get(i).position.getY() + " "
                    + getVehicles().get(i).position.getFacing() + ".");
        }
    }

    public String positionOfVehicleString(int vehicleListNumber){
        return getVehicles().get(vehicleListNumber).position.getX() + " " + getVehicles().get(vehicleListNumber).position.getY() + " "
                + getVehicles().get(vehicleListNumber).position.getFacing();
    }

}
