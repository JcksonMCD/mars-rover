package org.northcoders.fundamentals.JavaPlatform;

import org.junit.jupiter.api.Test;

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


}