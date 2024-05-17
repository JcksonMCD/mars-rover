package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;

public class IntegrationTest {

    @Test
    void integrationTest(){
        InputParser inputParser = new InputParser();
        InstructionOperator.plateau = new Plateau(inputParser.plateauSizeParser("5 5"));
        InstructionOperator.plateau.addVehicle(inputParser.positionParser("1 2 N"), AvailableVehicles.ROVER);
        InstructionOperator.vehicleNumber = 0;
        InstructionOperator.executeInstructions(inputParser.instructorParser("LMLMLMLMM"));

        Assertions.assertEquals("1 3 N", InstructionOperator.plateau.positionOfVehicleString(InstructionOperator.vehicleNumber));

        InstructionOperator.plateau.addVehicle(inputParser.positionParser("3 3 E"), AvailableVehicles.ROVER);
        InstructionOperator.vehicleNumber = 1;
        InstructionOperator.executeInstructions(inputParser.instructorParser("MMRMMRMRRM"));

        Assertions.assertEquals("5 1 E", InstructionOperator.plateau.positionOfVehicleString(InstructionOperator.vehicleNumber));
    }
}
