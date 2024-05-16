package input.layer;


import java.util.ArrayList;
import java.util.List;

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
        int x = Integer.parseInt(separatedInput[0]);
        int y = Integer.parseInt(separatedInput[1]);


        return new Position(x, y, CompassDirection.valueOf(separatedInput[2].toUpperCase()));
    }

    // Takes a string input of instructions and parses to a list of Instructor's
    public List<Instructor> instructorParser(String input){
        char[] separatedInput = input.toUpperCase().toCharArray();
        List<Instructor> instructionsTranslated = new ArrayList<>();

         for (char instruction : separatedInput){
             instructionsTranslated.add(Instructor.valueOf(String.valueOf(instruction)));
         }

         return instructionsTranslated;
    }
}
