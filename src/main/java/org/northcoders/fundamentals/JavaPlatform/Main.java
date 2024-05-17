package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import vehicle.AvailableVehicles;

public class Main {
    public static void main(String[] args) {

        runBasicProgramme();

    }

    public static void runBasicProgramme(){
        InstructionOperator instructionOperator = new InstructionOperator();
        Plateau plateau = new Plateau(instructionOperator.getPlateauSizeFromUser());
        plateau.addVehicle(instructionOperator.getStartingVehicleSpotFromUser(), instructionOperator.getVehicleTypeFromUser());
        instructionOperator.setPlateau(plateau);
        instructionOperator.getInstructionsFromUser();
    }
}