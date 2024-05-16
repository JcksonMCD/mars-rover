package org.northcoders.fundamentals.JavaPlatform;


public class InputParser {
    // Takes input String and parses to PlateauSize
    public PlateauSize plateauSizeParser(String input){
        String[] separatedInput = input.split(" ");

        if ((Integer.parseInt(separatedInput[0]) > 0) && (Integer.parseInt(separatedInput[1]) > 0)) {
            return new PlateauSize(Integer.parseInt(separatedInput[0]), Integer.parseInt(separatedInput[1]));
        } else {
            throw new RuntimeException("Plateau size inputs need to be more than zero.");
        }
    }

    //     Takes an input string of starting location and converts to Position class fields
    public Position positionParser(String input){
        String[] separatedInput = input.split(" ");

        return new Position(Integer.parseInt(separatedInput[0]), Integer.parseInt(separatedInput[1]),
                CompassDirection.valueOf(separatedInput[2]));
    }
}
