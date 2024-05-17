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
            throw new IllegalArgumentException("Plateau size inputs need to be more than zero.");
        }
    }

    //     Takes an input string of starting location and converts to Position class fields
    public Position positionParser(String input){
        String[] separatedInput = input.split(" ");
        int x;
        int y;

        try {
            if ((Integer.parseInt(separatedInput[0]) >= 0) && (Integer.parseInt(separatedInput[1]) >= 0)) {
                x = Integer.parseInt(separatedInput[0]);
                y = Integer.parseInt(separatedInput[1]);
            } else {
                throw new IllegalArgumentException("Negative numbers not allowed!");
            }

            return new Position(x, y, CompassDirection.valueOf(separatedInput[2].toUpperCase()));
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Please use the input format provided. Current input invalid");
        }
    }

    // Takes a string input of instructions and parses to a list of Instructor's
    public List<Instructor> instructorParser(String input) {
        List<Instructor> instructionsTranslated = new ArrayList<>();

        for (char instruction : input.toUpperCase().toCharArray()) {
            switch (instruction) {
                case 'M':
                    instructionsTranslated.add(Instructor.M);
                    break;
                case 'L':
                    instructionsTranslated.add(Instructor.L);
                    break;
                case 'R':
                    instructionsTranslated.add(Instructor.R);
                    break;
                default:
                    throw new IllegalArgumentException("M, L, R are the only available options");
            }
        }

        return instructionsTranslated;
    }
}
