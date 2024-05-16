import environment.Plateau;
import input.layer.CompassDirection;
import input.layer.Instructor;
import input.layer.PlateauSize;
import input.layer.Position;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;
import vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class InstructionOperatorTest {

    @Test
    void executeInstructions() {
        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.vehicleNumber = 0;
        instructionOperator.plateau = new Plateau(new PlateauSize(5 , 5));
        instructionOperator.plateau.addVehicle(new Position(0, 0, CompassDirection.N), AvailableVehicles.ROVER);
        Instructor[] instructors = {Instructor.M};
        Instructor[] instructors2 = {Instructor.L};

        instructionOperator.executeInstructions(instructors);
        instructionOperator.executeInstructions(instructors2);

        assertEquals(1, instructionOperator.plateau.getVehicles().getFirst().position.getY());
        assertEquals(CompassDirection.W, instructionOperator.plateau.getVehicles().getFirst().position.getFacing());
    }
}