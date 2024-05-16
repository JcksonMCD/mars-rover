package environment;

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
        if ((0 <= startPosition.getX() && startPosition.getX() <= plateauSize.getMAX_X()) && 0
                <= startPosition.getY() && startPosition.getY()<= startPosition.getX()){

            switch(vehicleType){

                case ROVER: Vehicle vehicle = new Rover(startPosition);
                            this.vehicles.add(vehicle);
                        break;
            }
            System.out.println(vehicleType + " has successfully landed to position (" + startPosition.getX() + ", " + startPosition.getY() + ") on the plateau.");
        } else {
            throw new IllegalArgumentException("Rover out of bounds for plateau size.");
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }
}
