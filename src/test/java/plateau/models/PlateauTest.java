package plateau.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import input.layer.*;
import vehicle.*;

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
        // This test can be enhanced by capturing stdout and verifying the output
    }
}
