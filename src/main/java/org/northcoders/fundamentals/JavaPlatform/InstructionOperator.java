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

    public void getVehicleTypeFromUser(){
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser();
        boolean validVehicleInputted = false;

        do {
            try{
                System.out.println("Which vehicle is landing (" + Arrays.toString(AvailableVehicles.values()) + ")");
                AvailableVehicles.valueOf(scanner.nextLine());
                validVehicleInputted = true;
            } catch (IllegalArgumentException e){
                System.out.println("You can only use available formats. Please pick a valid option.");
            }
        } while (!validVehicleInputted);

        scanner.close();
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
}
