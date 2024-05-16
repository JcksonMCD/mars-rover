package environment;

import input.layer.PlateauSize;
import input.layer.Position;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;
import vehicle.Rover;
import vehicle.Vehicle;

import static input.layer.CompassDirection.N;
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
}