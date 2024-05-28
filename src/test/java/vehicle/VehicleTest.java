package vehicle;

import static org.junit.jupiter.api.Assertions.*;

import inputlayer.CompassDirection;
import inputlayer.Position;
import logiclayer.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VehicleTest {

    private Vehicle vehicle;

    @BeforeEach
    public void setup() {
        vehicle = new Vehicle(new Position(0, 0, CompassDirection.N));
    }

    @Test
    public void testRotateLeft() {
        vehicle.rotateLeft();
        assertEquals(CompassDirection.W, vehicle.position.getFacing());
    }

    @Test
    public void testRotateRight() {
        vehicle.rotateRight();
        assertEquals(CompassDirection.E, vehicle.position.getFacing());
    }

    @Test
    public void testMove() {
        vehicle.move();
        assertEquals(1, vehicle.position.getY());
    }
}
