package inputlayer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


public class InputParserTest {

    private InputParser parser;

    @BeforeEach
    public void setup() {
        parser = new InputParser();
    }

    @Test
    public void testPlateauSizeParser() {
        PlateauSize size = parser.plateauSizeParser("5 5");
        assertEquals(5, size.getMAX_X());
        assertEquals(5, size.getMAX_Y());
    }

    @Test
    public void testPositionParser() {
        Position position = parser.positionParser("1 2 N");
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(CompassDirection.N, position.getFacing());
    }

    @Test
    public void testInstructorParser() {
        List<Instructor> instructions = parser.instructorParser("MLR");
        assertEquals(List.of(Instructor.M, Instructor.L, Instructor.R), instructions);
    }

    @Test
    public void testInvalidPlateauSizeParser() {
        assertThrows(IllegalArgumentException.class, () -> parser.plateauSizeParser("-1 5"));
    }

    @Test
    public void testInvalidPositionParser() {
        assertThrows(IllegalArgumentException.class, () -> parser.positionParser("1 2 X"));
    }

    @Test
    public void testInvalidInstructorParser() {
        assertThrows(IllegalArgumentException.class, () -> parser.instructorParser("MXL"));
    }
}
