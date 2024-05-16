package org.northcoders.fundamentals.JavaPlatform;

import input.layer.PlateauSize;
import input.layer.Position;
import vehicle.AvailableVehicles;
import vehicle.Rover;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    PlateauSize plateauSize;
    List<Vehicle> vehicles = new ArrayList<>();

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }


    public void addVehicle(Position startPosition, AvailableVehicles vehicleType){
        if (((0 <= startPosition.getX()) && (startPosition.getX() <= plateauSize.getMAX_X())) &&
                (0 <= startPosition.getY()) && (startPosition.getY() <= plateauSize.getMAX_Y())){

            Vehicle vehicle;
            switch(vehicleType) {
                case ROVER:
                    vehicle = new Rover(startPosition);
                    this.vehicles.add(vehicle);
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

    public void moveVehicle(int vehicleNumber){
        Vehicle currentVehicle = vehicles.get(vehicleNumber);
        switch (currentVehicle.position.getFacing()){
            case N :    if ((currentVehicle.position.getY() + 1) < plateauSize.getMAX_Y()){
                        currentVehicle.position.setY(currentVehicle.position.getY() + 1);
                        } else {
                            throw new IllegalArgumentException(currentVehicle.getClass() + " will not move out of bounds! Do you want a crash!!");
                        }
                break;
            case E:     if ((currentVehicle.position.getX() + 1) < plateauSize.getMAX_X()){
                            currentVehicle.position.setX(currentVehicle.position.getX() + 1);
                        } else {
                            throw new IllegalArgumentException(currentVehicle.getClass() + " will not move out of bounds! Do you want a crash!!");
                        }
                break;
            case S:     if ((currentVehicle.position.getY() - 1) >= 0){
                        currentVehicle.position.setY(currentVehicle.position.getY() - 1);
                        } else {
                        throw new IllegalArgumentException(currentVehicle.getClass() + " will not move out of bounds! Do you want a crash!!");
                        }
                break;
            case W:     if ((currentVehicle.position.getX() - 1) >= 0){
                        currentVehicle.position.setX(currentVehicle.position.getX() - 1);
                        } else {
                        throw new IllegalArgumentException(currentVehicle.getClass() + " will not move out of bounds! Do you want a crash!!");
                        }
                break;
        }
    }
}
