package org.northcoders.fundamentals.JavaPlatform;


public class InputParser {
    // Takes input String and parses to PlateauSize
    public PlateauSize plateauSizeParser(String input){
        String[] separatedInput = input.split(" ");
        return new PlateauSize(Integer.parseInt(separatedInput[0]), Integer.parseInt(separatedInput[1]));
    }



}
