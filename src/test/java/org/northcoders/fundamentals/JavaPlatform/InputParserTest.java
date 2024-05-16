package org.northcoders.fundamentals.JavaPlatform;

import input.layer.CompassDirection;
import input.layer.InputParser;
import input.layer.Instructor;
import input.layer.PlateauSize;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void plateauSizeParserTest() {
        InputParser inputParser = new InputParser();
        PlateauSize plateauSize = new PlateauSize(5 , 5);

        assertEquals(5 , inputParser.plateauSizeParser("5 5").getMAX_X());
        assertEquals(5 , inputParser.plateauSizeParser("5 5").getMAX_Y());
        assertThrows(RuntimeException.class, () -> inputParser.plateauSizeParser("0 0"));
        assertThrows(RuntimeException.class, () -> inputParser.plateauSizeParser("0 1"));
        assertThrows(RuntimeException.class, () -> inputParser.plateauSizeParser("1 0"));
        assertThrows(RuntimeException.class, () -> inputParser.plateauSizeParser("-1 -1"));
        assertThrows(RuntimeException.class, () -> inputParser.plateauSizeParser("1 -1"));
        assertThrows(RuntimeException.class, () -> inputParser.plateauSizeParser("-1 1"));
    }


    @Test
    void positionParser() {
        InputParser inputParser = new InputParser();

        assertEquals(CompassDirection.N, inputParser.positionParser("5 5 N").getFacing());
        assertEquals(5, inputParser.positionParser("5 5 N").getY());
        assertEquals(5, inputParser.positionParser("5 5 N").getX());
        assertEquals(CompassDirection.N, inputParser.positionParser("5 5 n").getFacing());
        assertThrows(IllegalArgumentException.class, () -> inputParser.positionParser("5 5 Z"));

    }

    @Test
    void instructorParser() {
        InputParser inputParser = new InputParser();

        assertEquals(List.of(Instructor.R, Instructor.M, Instructor.L), inputParser.instructorParser("RML"));
        assertEquals(List.of(Instructor.R, Instructor.M, Instructor.L), inputParser.instructorParser("rML"));
        assertEquals(List.of(Instructor.R, Instructor.R, Instructor.R), inputParser.instructorParser("rrr"));
        assertEquals(List.of(Instructor.M, Instructor.M, Instructor.M), inputParser.instructorParser("MMM"));
        assertThrows(IllegalArgumentException.class, () -> inputParser.instructorParser("ZZZ"));
        assertThrows(IllegalArgumentException.class, () -> inputParser.instructorParser("ZML"));
        assertThrows(IllegalArgumentException.class, () -> inputParser.instructorParser("ML0"));

    }
}