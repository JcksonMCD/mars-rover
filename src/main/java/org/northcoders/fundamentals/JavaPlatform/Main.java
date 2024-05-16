package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import vehicle.AvailableVehicles;

public class Main {
    public static void main(String[] args) {
        // 5 5
        //1 2 N
        //LMLMLMLMM
        //3 3 E
        //MMRMMRMRRM

        InputParser inputParser = new InputParser();

        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.plateau = new Plateau(inputParser.plateauSizeParser("5 5"));
        instructionOperator.plateau.addVehicle(inputParser.positionParser("1 2 N"), AvailableVehicles.ROVER);
//        instructionOperator.vehicleNumber = 0;
//        instructionOperator.executeInstructions(inputParser.instructorParser("LMLMLMLMM"));
    }
}