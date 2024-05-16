package vehicle;

import input.layer.CompassDirection;
import input.layer.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void move() {
        Vehicle vehicle = new Vehicle(new Position(1, 1, CompassDirection.N));
        vehicle.move();
        Vehicle vehicle2 = new Vehicle(new Position(1, 1, CompassDirection.E));
        vehicle2.move();
        Vehicle vehicle3 = new Vehicle(new Position(1, 1, CompassDirection.S));
        vehicle3.move();
        Vehicle vehicle4 = new Vehicle(new Position(1, 1, CompassDirection.W));
        vehicle4.move();

        assertEquals(2 , vehicle.position.getY());
        assertEquals(2 , vehicle2.position.getX());
        assertEquals(0 , vehicle3.position.getY());
        assertEquals(0 , vehicle4.position.getX());
    }

    @Test
    void rotate() {
    }
}