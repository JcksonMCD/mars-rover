package org.northcoders.fundamentals.JavaPlatform;

import input.layer.InputParser;
import input.layer.Instructor;
import input.layer.PlateauSize;
import input.layer.Position;
import plateau.models.Plateau;
import vehicle.AvailableVehicles;
import vehicle.Vehicle;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InstructionOperator {
    private static Plateau plateau;
    static Vehicle currentVehicle;
    private static Scanner scanner;
    public static boolean fordPicked = false;

    public InstructionOperator(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void runBasicProgrammeStart() {
        InstructionOperator instructionOperator = new InstructionOperator(new Scanner(System.in));
        plateau = new Plateau(getPlateauSizeFromUser());
        setPlateau(plateau);

        while (true) {
            Position startPos = getStartingVehicleSpotFromUser();
            AvailableVehicles vehicleType = getVehicleTypeFromUser();

            if (vehicleType == AvailableVehicles.FORD_FIESTA) {
                System.out.println("Ford Fiestas can't land on Mars! You crashed... oops");
                System.out.println("Would you like to try landing again? (Y/N)");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("Y")) {
                    System.exit(0); // Exit the program if the user chooses not to retry
                }
                fordPicked = true;
            } else {
                fordPicked = false;
            }

            if (!fordPicked) {
                plateau.addVehicle(startPos, vehicleType);
                break;
            }
        }

        setCurrentVehicle(0);
        getInstructionsFromUser();
    }

    public static boolean movementInBoundsOfPlateau() {
        Position originalPosition = new Position(currentVehicle.position.getX(), currentVehicle.position.getY(), currentVehicle.position.getFacing());
        currentVehicle.move();

        if (currentVehicle.position.getX() < 0 || currentVehicle.position.getX() > plateau.plateauSize.getMAX_X() ||
                currentVehicle.position.getY() < 0 || currentVehicle.position.getY() > plateau.plateauSize.getMAX_Y()) {

            currentVehicle.position.setX(originalPosition.getX());
            currentVehicle.position.setY(originalPosition.getY());

            throw new IllegalArgumentException(currentVehicle.getClass().getSimpleName() + " will move out of bounds! Do you want a crash!!");
        } else {
            currentVehicle.position.setX(originalPosition.getX());
            currentVehicle.position.setY(originalPosition.getY());
            return true;
        }
    }

    public static void executeInstructions(List<Instructor> instructions) {
        if (currentVehicle == null) {
            throw new IllegalStateException("Current vehicle not set.");
        }

        for (Instructor instruction : instructions) {
            switch (instruction) {
                case M:
                    if (movementInBoundsOfPlateau()) {
                        currentVehicle.move();
                    } else throw new IllegalArgumentException("Movement out of bounds! You can only move within the size of the plateau");
                    break;
                case R:
                    currentVehicle.rotateRight();
                    break;
                case L:
                    currentVehicle.rotateLeft();
                    break;
            }
        }
        plateau.printPositionOfVehicles();
    }

    public static void setPlateau(Plateau plateau) {
        InstructionOperator.plateau = plateau;
    }

    public static void setCurrentVehicle(int vehicleNumber) {
        if (!plateau.getVehicles().isEmpty()) {
            currentVehicle = plateau.getVehicles().get(vehicleNumber);
        }
    }

    public static Plateau getPlateau() {
        return plateau;
    }

    public static PlateauSize getPlateauSizeFromUser() {
        InputParser inputParser = new InputParser();
        boolean validPlateauSizeInputted = false;
        PlateauSize plateauSize = new PlateauSize(0, 0);

        do {
            try {
                System.out.println("Please input the size of your plateau space in the format (maxX maxY). Where X and Y are positive whole numbers.");
                plateauSize = inputParser.plateauSizeParser(scanner.nextLine());
                validPlateauSizeInputted = true;
                System.out.println("Plateau size set :)");
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid size. Read the instructions again.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Both pieces of information need to be provided. Please provide X and Y.");
            }
        } while (!validPlateauSizeInputted);

        return plateauSize;
    }

    public static AvailableVehicles getVehicleTypeFromUser() {
        boolean validVehicleInputted = false;
        AvailableVehicles vehicleType = null;

        while (!validVehicleInputted) {
            try {
                System.out.println("Which vehicle do you want to use? Choose from the following options: " + Arrays.toString(AvailableVehicles.values()));
                vehicleType = AvailableVehicles.valueOf(scanner.nextLine().toUpperCase());
                validVehicleInputted = true;
            } catch (IllegalArgumentException e) {
                System.out.println("You can only use available formats. Please pick a valid option.");
            }
        }

        return vehicleType;
    }

    public static Position getStartingVehicleSpotFromUser() {
        InputParser inputParser = new InputParser();
        boolean validstarterSpotInputted = false;
        Position startPosition = null;

        do {
            try {
                System.out.println("Please input where you want your vehicle to start (X Y Facing(N/ E/ S/ W))");
                startPosition = inputParser.positionParser(scanner.nextLine());
                if (((0 <= startPosition.getX()) && (startPosition.getX() <= plateau.plateauSize.getMAX_X())) &&
                        (0 <= startPosition.getY()) && (startPosition.getY() <= plateau.plateauSize.getMAX_Y())) {
                    validstarterSpotInputted = true;
                } else {
                    throw new IllegalArgumentException("Start position off grid. Pick new coordinates within the plateau size.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("All three pieces of information need to be provided. Please provide X, Y, and direction information.");
            }
        } while (!validstarterSpotInputted);

        return startPosition;
    }

    public static void getInstructionsFromUser() {
        InputParser inputParser = new InputParser();
        boolean validInstructionsInputted = false;

        do {
            try {
                System.out.println("Please input your vehicle move instructions (M: Move vehicle 1 block in the direction it is facing, " +
                        "L: Move 90 degrees to the left" +
                        ", R: Move 90 degrees to the right). Options can be chained e.g (MMRMMLLM).");
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    executeInstructions(inputParser.instructorParser(input));
                    validInstructionsInputted = true;
                } else {
                    System.out.println("Empty input. Please provide valid instructions.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInstructionsInputted);
    }
}

