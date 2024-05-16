package org.northcoders.fundamentals.JavaPlatform;

import input.layer.CompassDirection;
import input.layer.Instructor;
import input.layer.PlateauSize;
import input.layer.Position;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionOperatorTest {

    @Test
    void executeInstructions() {
        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.setVehicleNumber(0);
        instructionOperator.setPlateau(new Plateau(new PlateauSize(5 , 5)));
        instructionOperator.getPlateau().addVehicle(new Position(0, 0, CompassDirection.N), AvailableVehicles.ROVER);


        instructionOperator.executeInstructions(List.of(Instructor.M));
        instructionOperator.executeInstructions(List.of(Instructor.L));

        assertEquals(1, instructionOperator.getPlateau().getVehicles().getFirst().position.getY());
        assertEquals(CompassDirection.W, instructionOperator.getPlateau().getVehicles().getFirst().position.getFacing());

        instructionOperator.executeInstructions(List.of(Instructor.R, Instructor.M));

        assertEquals(2, instructionOperator.getPlateau().getVehicles().getFirst().position.getY());

    }
}