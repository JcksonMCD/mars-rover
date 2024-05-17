package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import input.layer.Instructor;
import input.layer.PlateauSize;
import input.layer.Position;
import vehicle.AvailableVehicles;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InstructionOperator {
    static Plateau plateau;
    static int vehicleNumber = 0;


    public static void executeInstructions(List<Instructor> instructions){
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
        plateau.printPositionOfVehicles();
    }

    public void setPlateau(Plateau plateau) {
        InstructionOperator.plateau = plateau;
        vehicleNumber = plateau.vehicles.size();
    }

    public void setVehicleNumber(int vehicleNumber) {
        InstructionOperator.vehicleNumber = vehicleNumber;
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
            }  catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Both pieces of information need to be provided. Please provide X and Y");
            }
        } while (!validPlateauSizeInputted);

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

        return vehicleType;
    }

    public Position getStartingVehicleSpotFromUser(){
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        boolean validstarterSpotInputted = false;
        Position startPosition = null;

            do {
            try{
                System.out.println("Please input where you want your vehicle to start (X Y Facing(N/ E/ S/ W)");
                startPosition = inputParser.positionParser(scanner.nextLine());
                if (((0 <= startPosition.getX()) && (startPosition.getX() <= plateau.plateauSize.getMAX_X())) &&
                        (0 <= startPosition.getY()) && (startPosition.getY() <= plateau.plateauSize.getMAX_Y())){
                    validstarterSpotInputted = true;
                } else {
                    throw new IllegalArgumentException("Start position off grid. Pick new coordinates with in the plateau size");
                }
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("All three pieces of information need to be provided. Please provide X, Y and direction information.");
            }
        } while (!validstarterSpotInputted);
        
        return startPosition;
    }

    public void getInstructionsFromUser() {
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        boolean validInstructionsInputted = false;
        List<Instructor> instructorList = null;

        do {
            try {
                System.out.println("Please input your vehicle move instructions (M: Move vehicle 1 block in the direction it is facing, " +
                        "L: Move 90 degrees to the left" +
                        ", R: Move 90 degrees to the right). Options can be chained e.g (MMRMMLLM).");
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    instructorList = inputParser.instructorParser(input);
                    executeInstructions(instructorList);
                    validInstructionsInputted = true;
                } else {
                    System.out.println("Empty input. Please provide valid instructions.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input please follow instructions");
            }
        } while (!validInstructionsInputted);

    }
}
