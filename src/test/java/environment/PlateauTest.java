package environment;

import input.layer.CompassDirection;
import input.layer.PlateauSize;
import input.layer.Position;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;
import vehicle.Rover;
import vehicle.Vehicle;

import static input.layer.CompassDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void addVehicle() {
        Plateau plateau = new Plateau(new PlateauSize(5 , 5));
        Position startPosition = new Position(5, 3, N);
        AvailableVehicles vehicleType = AvailableVehicles.ROVER;

        plateau.addVehicle(startPosition, vehicleType);
        assertEquals(1, plateau.getVehicles().size());
        assertInstanceOf(Rover.class, plateau.getVehicles().getFirst());

        plateau.addVehicle(new Position(5 , 5, N), vehicleType);
        assertEquals(2, plateau.getVehicles().size());
        assertInstanceOf(Rover.class, plateau.getVehicles().get(1));

        plateau.addVehicle(new Position(0 , 0, N), vehicleType);
        assertEquals(3, plateau.getVehicles().size());
        assertInstanceOf(Rover.class, plateau.getVehicles().get(2));

        assertThrows(IllegalArgumentException.class, () -> plateau.addVehicle(new Position(-1 , 0, N), vehicleType));
        assertThrows(IllegalArgumentException.class, () -> plateau.addVehicle(new Position(7 , 7, N), vehicleType));

    }

    @Test
    void moveVehicle() {
        Plateau plateau = new Plateau(new PlateauSize(5, 5));
        plateau.addVehicle(new Position(0 ,0, N), AvailableVehicles.ROVER);
        plateau.addVehicle(new Position(1 ,0, W), AvailableVehicles.ROVER);
        plateau.addVehicle(new Position(0 ,0, S), AvailableVehicles.ROVER);
        plateau.addVehicle(new Position(5 ,0, E), AvailableVehicles.ROVER);
        plateau.moveVehicle(0);
        plateau.moveVehicle(1);

        assertEquals(plateau.getVehicles().getFirst().position.getY(), 1);
        assertEquals(plateau.getVehicles().getFirst().position.getX(), 0);
        assertThrows(IllegalArgumentException.class, () -> plateau.moveVehicle(2));
        assertThrows(IllegalArgumentException.class, () -> plateau.moveVehicle(3));
    }
}