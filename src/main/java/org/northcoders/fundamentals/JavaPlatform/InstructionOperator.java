package org.northcoders.fundamentals.JavaPlatform;

import input.layer.Instructor;

import java.util.List;

public class InstructionOperator {
    Plateau plateau;
    int vehicleNumber;

    public Plateau getPlateau() {
        return plateau;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }


    public void executeInstructions(List<Instructor> instructions){
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
