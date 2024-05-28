import inputlayer.CompassDirection;
import inputlayer.Instructor;
import inputlayer.PlateauSize;
import inputlayer.Position;
import logiclayer.MissionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logiclayer.Plateau;
import logiclayer.vehicle.AvailableVehicles;

import java.util.List;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MissionControlTest {
    private MissionControl missionControl;
    private Plateau plateau;

    private Queue<String> inputQueue;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        inputQueue = new LinkedList<>();
        mockScanner = new Scanner(new InputQueueStream(inputQueue));
        missionControl = new MissionControl(mockScanner);
        plateau = new Plateau(new PlateauSize(5, 5));
        missionControl.setPlateau(plateau);
        plateau.addVehicle(new Position(0, 0, CompassDirection.N), AvailableVehicles.ROVER);
        missionControl.setCurrentVehicle(0);
    }

    @Test
    public void testRunBasicProgrammeStartWithValidInputs() {
        inputQueue.add("5 5");
        inputQueue.add("0 0 N");
        inputQueue.add("ROVER");
        inputQueue.add("MMRMMLLM");

        missionControl.runBasicProgrammeStart();

        assertNotNull(missionControl.getPlateau());
        assertEquals(1, missionControl.getPlateau().getVehicles().size());
    }

    @Test
    public void testRunBasicProgrammeStartWithFordFiesta() {
        inputQueue.add("5 5");
        inputQueue.add("0 0 N");
        inputQueue.add("FORD_FIESTA");
        inputQueue.add("Y");
        inputQueue.add("0 0 N");
        inputQueue.add("ROVER");
        inputQueue.add("MMRMMLLM");

        missionControl.runBasicProgrammeStart();

        assertNotNull(missionControl.getPlateau());
        assertEquals(1, missionControl.getPlateau().getVehicles().size());
    }

    @Test
    public void testMovementInBoundsOfPlateau() {
        assertTrue(missionControl.movementInBoundsOfPlateau());
    }

    @Test
    public void testExecuteInstructions() {
        List<Instructor> instructions = List.of(Instructor.M, Instructor.R, Instructor.M);
        missionControl.executeInstructions(instructions);

        Position currentPosition = missionControl.getPlateau().getVehicles().get(0).position;
        assertEquals(1, currentPosition.getX());
        assertEquals(1, currentPosition.getY());
        assertEquals(CompassDirection.E, currentPosition.getFacing());
    }

    private static class InputQueueStream extends java.io.InputStream {
        private final Queue<String> queue;
        private String current;
        private int pos;

        public InputQueueStream(Queue<String> queue) {
            this.queue = queue;
            this.current = queue.poll() + "\n";
            this.pos = 0;
        }

        @Override
        public int read() {
            if (current == null) return -1;
            if (pos == current.length()) {
                current = queue.poll();
                if (current != null) {
                    current += "\n";
                    pos = 0;
                } else {
                    return -1;
                }
            }
            return current.charAt(pos++);
        }
    }
}
