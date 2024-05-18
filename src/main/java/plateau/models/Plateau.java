package plateau.models;

import input.layer.PlateauSize;
import input.layer.Position;
import org.northcoders.fundamentals.JavaPlatform.InstructionOperator;
import vehicle.AvailableVehicles;
import vehicle.MoonBuggy;
import vehicle.Rover;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class Plateau {
    public PlateauSize plateauSize;
    List<Vehicle> vehicles = new ArrayList<>();
    List<CrashSite> crashSites = new ArrayList<>();

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public void addCrashSite(CrashSite crashSite) {
        this.crashSites.add(crashSite);
    }

    public void printPositionOfVehicles(){
        for (Vehicle vehicle : vehicles) {
            System.out.println("Position of " + vehicle.getClass().getSimpleName() + " is " +
                    vehicle.position.getX() + " " + vehicle.position.getY() + " " + vehicle.position.getFacing() + ".");
        }
    }

    public void addVehicle(Position startPosition, AvailableVehicles vehicleType){
//        for (CrashSite crashSite : crashSites){
//            if (crashSite.x == startPosition.getX() && crashSite.y == startPosition.getY()){
//                System.out.println("Look who crashed again... You've landed on a crash site :(");
//            }
//        }
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
                    System.out.println("Ford Fiestas can't land on Mars! You crashed... oops :(");
                    InstructionOperator.fordPicked = true;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
            }
            System.out.println(vehicleType + " has successfully landed to position (" + startPosition.getX() + ", " + startPosition.getY() + ") on the plateau.");
        } else {
            throw new IllegalArgumentException("Vehicle out of bounds for plateau size.");
        }
    }

}
