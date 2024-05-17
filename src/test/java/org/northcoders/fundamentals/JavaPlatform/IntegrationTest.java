package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;

public class IntegrationTest {

    @Test
    void integrationTest(){
        InputParser inputParser = new InputParser();
        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.plateau = new Plateau(inputParser.plateauSizeParser("5 5"));
        instructionOperator.plateau.addVehicle(inputParser.positionParser("1 2 N"), AvailableVehicles.ROVER);
        instructionOperator.vehicleNumber = 0;
        instructionOperator.executeInstructions(inputParser.instructorParser("LMLMLMLMM"));

        Assertions.assertEquals("1 3 N", instructionOperator.plateau.positionOfVehicleString(instructionOperator.vehicleNumber));

        instructionOperator.plateau.addVehicle(inputParser.positionParser("3 3 E"), AvailableVehicles.ROVER);
        instructionOperator.vehicleNumber = 1;
        instructionOperator.executeInstructions(inputParser.instructorParser("MMRMMRMRRM"));

        Assertions.assertEquals("5 1 E", instructionOperator.plateau.positionOfVehicleString(instructionOperator.vehicleNumber));
    }
}
