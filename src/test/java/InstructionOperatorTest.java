import org.northcoders.fundamentals.JavaPlatform.Plateau;
import input.layer.CompassDirection;
import input.layer.Instructor;
import input.layer.PlateauSize;
import input.layer.Position;
import org.junit.jupiter.api.Test;
import org.northcoders.fundamentals.JavaPlatform.InstructionOperator;
import vehicle.AvailableVehicles;

import static org.junit.jupiter.api.Assertions.*;

class InstructionOperatorTest {

    @Test
    void executeInstructions() {
        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.setVehicleNumber(0);
        instructionOperator.setPlateau(new Plateau(new PlateauSize(5 , 5)));
        instructionOperator.getPlateau().addVehicle(new Position(0, 0, CompassDirection.N), AvailableVehicles.ROVER);
        Instructor[] instructors = {Instructor.M};
        Instructor[] instructors2 = {Instructor.L};

        instructionOperator.executeInstructions(instructors);
        instructionOperator.executeInstructions(instructors2);

        assertEquals(1, instructionOperator.getPlateau().getVehicles().getFirst().position.getY());
        assertEquals(CompassDirection.W, instructionOperator.getPlateau().getVehicles().getFirst().position.getFacing());
    }
}