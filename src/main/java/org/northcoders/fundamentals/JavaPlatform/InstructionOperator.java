package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import input.layer.Instructor;
import input.layer.PlateauSize;
import vehicle.AvailableVehicles;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InstructionOperator {
    Plateau plateau;
    int vehicleNumber;

    public static void main(String[] args) {
        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.getPlateauSizeFromUser();
    }

    public void executeInstructions(List<Instructor> instructions){
        for(Instructor instruction : instructions){
            switch (instruction){
                case Instructor.M: plateau.moveVehicle(vehicleNumber);
                    break;
                case R: plateau.getVehicles().get(vehicleNumber).rotateRight();
                    break;
                case L: plateau.getVehicles().get(vehicleNumber).rotateLeft();
                    break;
            }
        }
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
        vehicleNumber = plateau.vehicles.size() - 1;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public PlateauSize getPlateauSizeFromUser(){
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        boolean validPlateauSizeInputted = false;
        PlateauSize plateauSize = new PlateauSize(0 , 0);

        do {
            try{
                System.out.println("Please input the size of your plateau space int format (maxX maxY)");
                plateauSize = inputParser.plateauSizeParser(scanner.nextLine());
                validPlateauSizeInputted = true;
                System.out.println("Plateau size set :)");
            } catch (IllegalArgumentException e){
                System.out.println("Not a valid size, use the given format silly!");
            }
        } while (!validPlateauSizeInputted);

        scanner.close();
        return plateauSize;
    }

    public AvailableVehicles getVehicleTypeFromUser(){
        Scanner scanner = new Scanner(System.in);
        boolean validVehicleInputted = false;
        AvailableVehicles vehicleType = null;

        do {
            try{
                System.out.println("Which vehicle is landing (" + Arrays.toString(AvailableVehicles.values()) + ")");
                vehicleType = AvailableVehicles.valueOf(scanner.nextLine());
                validVehicleInputted = true;
            } catch (IllegalArgumentException e){
                System.out.println("You can only use available formats. Please pick a valid option.");
            }
        } while (!validVehicleInputted);
        
        scanner.close();
        return vehicleType;
    }

    public void getStartingVehicleSpotFromUser(){
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        boolean validstarterSpotInputted = false;
        PlateauSize plateauSize = new PlateauSize(0 , 0);

        do {
            try{
                System.out.println("Please input where you want your vehicle to start (X Y Facing(N/ E/ S/ W)");
                plateau.vehicles.getFirst().position = inputParser.positionParser(scanner.nextLine());
                validstarterSpotInputted = true;
            } catch (IllegalArgumentException e){
                System.out.println("I said format X Y Facing(N/E/S/W)! Use it!");
            }
        } while (!validstarterSpotInputted);

        scanner.close();
    }

    public void getInstructionsFromUser() {
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        boolean validInstructionsInputted = false;

        do {
            try {
                System.out.println("Please input your vehicle move instructions (M: Move vehicle 1 block in the direction it is facing, " +
                        "L: Move 90 degrees to the left" +
                        ", R: Move 90 degrees to the right). Options can be chained e.g (MMRMMLLM).");
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    inputParser.instructorParser(input);
                    validInstructionsInputted = true;
                } else {
                    System.out.println("Empty input. Please provide valid instructions.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input please follow instructions");
            }
        } while (!validInstructionsInputted);

        scanner.close();
    }
}
