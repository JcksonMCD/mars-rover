package vehicle;

import input.layer.CompassDirection;
import input.layer.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void rotateLeft() {
        Position position = new Position(0, 0, CompassDirection.N);
        Vehicle vehicle = new Vehicle(position);

        position.setFacing(CompassDirection.N);
        vehicle.rotateLeft();
        assertEquals(CompassDirection.W, position.getFacing());
        position.setFacing(CompassDirection.E);
        vehicle.rotateLeft();
        assertEquals(CompassDirection.N, position.getFacing());
    }

    @Test
    void rotateRight() {
        Position position = new Position(0, 0, CompassDirection.N);
        Vehicle vehicle = new Vehicle(position);

        position.setFacing(CompassDirection.N);
        vehicle.rotateRight();
        assertEquals(CompassDirection.E, position.getFacing());
        position.setFacing(CompassDirection.E);
        vehicle.rotateRight();
        assertEquals(CompassDirection.S, position.getFacing());
    }


}