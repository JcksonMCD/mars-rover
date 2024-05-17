package org.northcoders.fundamentals.JavaPlatform;

import input.layer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.AvailableVehicles;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static input.layer.CompassDirection.N;
import static org.junit.jupiter.api.Assertions.*;

class InstructionOperatorTest {

    @Test
    void executeInstructions() {
        InstructionOperator instructionOperator = new InstructionOperator();
        instructionOperator.setVehicleNumber(0);
        instructionOperator.setPlateau(new Plateau(new PlateauSize(5 , 5)));
        instructionOperator.getPlateau().addVehicle(new Position(0, 0, N), AvailableVehicles.ROVER);


        InstructionOperator.executeInstructions(List.of(Instructor.M));
        InstructionOperator.executeInstructions(List.of(Instructor.L));

        assertEquals(1, instructionOperator.getPlateau().getVehicles().getFirst().position.getY());
        assertEquals(CompassDirection.W, instructionOperator.getPlateau().getVehicles().getFirst().position.getFacing());

        InstructionOperator.executeInstructions(List.of(Instructor.R, Instructor.M));

        assertEquals(2, instructionOperator.getPlateau().getVehicles().getFirst().position.getY());

    }

    private InstructionOperator instructionOperator;
    private Plateau mockPlateau;

    @BeforeEach
    public void setUp() {
        instructionOperator = new InstructionOperator();
        mockPlateau= new Plateau(new PlateauSize(10, 10));
        instructionOperator.setPlateau(mockPlateau);

        mockPlateau.addVehicle(new Position(0, 0, N), AvailableVehicles.ROVER);
    }

    @Test
    public void testGetPlateauSizeFromUser_ValidInput() {
        String input = "5 10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PlateauSize plateauSize = instructionOperator.getPlateauSizeFromUser();

        assertNotNull(plateauSize);
        assertEquals(5, plateauSize.getMAX_X());
        assertEquals(10, plateauSize.getMAX_Y());
    }

    @Test
    public void testGetPlateauSizeFromUser_InvalidThenValidInput() {
        String input = "invalid\n5 10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PlateauSize plateauSize = instructionOperator.getPlateauSizeFromUser();

        assertNotNull(plateauSize);
        assertEquals(5, plateauSize.getMAX_X());
        assertEquals(10, plateauSize.getMAX_Y());
    }

    @Test
    public void testGetPlateauSizeFromUser_NegativeCoordinatesThenValidInput() {
        String input = "-1 10\n5 10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PlateauSize plateauSize = instructionOperator.getPlateauSizeFromUser();

        assertNotNull(plateauSize);
        assertEquals(5, plateauSize.getMAX_X());
        assertEquals(10, plateauSize.getMAX_Y());
    }

    @Test
    public void testGetStartingVehicleSpotFromUser_ValidInput() {
        String input = "2 3 N\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InstructionOperator instructionOperator = new InstructionOperator();
        Position startPosition = instructionOperator.getStartingVehicleSpotFromUser();

        assertNotNull(startPosition);
        assertEquals(2, startPosition.getX());
        assertEquals(3, startPosition.getY());
        assertEquals(CompassDirection.N, startPosition.getFacing());
    }

    @Test
    public void testGetVehicleTypeFromUser_ValidInput() {
        String input = "ROVER\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AvailableVehicles result = instructionOperator.getVehicleTypeFromUser();

        assertEquals(AvailableVehicles.ROVER, result);
    }

    @Test
    public void testGetVehicleTypeFromUser_InvalidThenValidInput() {
        String input = "INVALID\nROVER\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AvailableVehicles result = instructionOperator.getVehicleTypeFromUser();

        assertEquals(AvailableVehicles.ROVER, result);
    }

    @Test
    public void testGetInstructionsFromUser_ValidInput() {
        String input = "MMRMMLLM\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InstructionOperator instructionOperator = new InstructionOperator();

        assertDoesNotThrow(instructionOperator::getInstructionsFromUser);
    }
}

