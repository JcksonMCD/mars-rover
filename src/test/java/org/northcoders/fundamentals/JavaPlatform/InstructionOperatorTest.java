package org.northcoders.fundamentals.JavaPlatform;

import input.layer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plateau.models.Plateau;
import vehicle.AvailableVehicles;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstructionOperatorTest {
    private InstructionOperator instructionOperator;
    private Plateau plateau;

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        instructionOperator = new InstructionOperator(mockScanner);
        plateau = new Plateau(new PlateauSize(5, 5));
        instructionOperator.setPlateau(plateau);
        plateau.addVehicle(new Position(0, 0, CompassDirection.N), AvailableVehicles.ROVER);
        instructionOperator.setCurrentVehicle(0);
    }

    @Test
    public void testRunBasicProgrammeStartWithValidInputs() {
        when(mockScanner.nextLine())
                .thenReturn("5 5")  // for getPlateauSizeFromUser
                .thenReturn("0 0 N")  // for getStartingVehicleSpotFromUser
                .thenReturn("ROVER")  // for getVehicleTypeFromUser
                .thenReturn("MMRMMLLM");  // for getInstructionsFromUser

        instructionOperator.runBasicProgrammeStart();

        assertNotNull(instructionOperator.getPlateau());
        assertEquals(1, instructionOperator.getPlateau().getVehicles().size());
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    public void testRunBasicProgrammeStartWithFordFiesta() {
        when(mockScanner.nextLine())
                .thenReturn("5 5")  // for getPlateauSizeFromUser
                .thenReturn("0 0 N")  // for getStartingVehicleSpotFromUser
                .thenReturn("FORD_FIESTA")  // for getVehicleTypeFromUser
                .thenReturn("Y")  // for retry after Ford Fiesta crash
                .thenReturn("0 0 N")  // for getStartingVehicleSpotFromUser
                .thenReturn("ROVER")  // for getVehicleTypeFromUser
                .thenReturn("MMRMMLLM");  // for getInstructionsFromUser

        instructionOperator.runBasicProgrammeStart();

        assertNotNull(instructionOperator.getPlateau());
        assertEquals(1, instructionOperator.getPlateau().getVehicles().size());
        verify(mockScanner, times(7)).nextLine();
    }

    @Test
    public void testMovementInBoundsOfPlateau() {
        assertTrue(instructionOperator.movementInBoundsOfPlateau());
    }

    @Test
    public void testExecuteInstructions() {
        List<Instructor> instructions = List.of(Instructor.M, Instructor.R, Instructor.M);
        instructionOperator.executeInstructions(instructions);

        Position currentPosition = instructionOperator.getPlateau().getVehicles().get(0).position;
        assertEquals(1, currentPosition.getX());
        assertEquals(1, currentPosition.getY());
        assertEquals(CompassDirection.E, currentPosition.getFacing());
    }
}
