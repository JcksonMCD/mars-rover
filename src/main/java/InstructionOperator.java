import environment.Plateau;
import input.layer.Instructor;

public class InstructionOperator {
    Plateau plateau;
    int vehicleNumber;

    public void executeInstructions(Instructor[] instructions){
        for(Instructor instruction : instructions){
            switch (instruction){
                case Instructor.M: plateau.moveVehicle(vehicleNumber);
                    break;
                case R: plateau.getVehicles().get(vehicleNumber).rotateRight();
                    break;
                case L: plateau.getVehicles().get(vehicleNumber).rotateLeft();
                    break;
            }
        }
    }
}
