package plateaumodels;

import static org.junit.jupiter.api.Assertions.*;

import inputlayer.CompassDirection;
import inputlayer.PlateauSize;
import inputlayer.Position;
import logiclayer.Plateau;
import logiclayer.vehicle.AvailableVehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlateauTest {

    private Plateau plateau;

    @BeforeEach
    public void setup() {
        plateau = new Plateau(new PlateauSize(5, 5));
    }

    @Test
    public void testAddVehicle() {
        Position position = new Position(1, 2, CompassDirection.N);
        plateau.addVehicle(position, AvailableVehicles.ROVER);
        assertEquals(1, plateau.getVehicles().size());
    }

    @Test
    public void testAddVehicleOutOfBounds() {
        Position position = new Position(6, 6, CompassDirection.N);
        assertThrows(IllegalArgumentException.class, () -> plateau.addVehicle(position, AvailableVehicles.ROVER));
    }

    @Test
    public void testPrintPositionOfVehicles() {
        Position position = new Position(1, 2, CompassDirection.N);
        plateau.addVehicle(position, AvailableVehicles.ROVER);
        plateau.printPositionOfVehicles();
    }
}
