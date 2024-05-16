package vehicle;

import input.layer.CompassDirection;
import input.layer.Position;

public class Vehicle {

    public Vehicle(Position position) {
        this.position = position;
    }

    Position position;

    public void move(){
        switch (position.getFacing()){
            case N :position.setY(position.getY() + 1);
                break;
            case E: position.setX(position.getX() + 1);
                break;
            case S: position.setY(position.getY() - 1);
                break;
            case W: position.setX(position.getX() -1);
                break;
        }
    }

    public void rotateLeft(){
        switch (position.getFacing()){
            case N :position.setFacing(CompassDirection.W);
                break;
            case E: position.setFacing(CompassDirection.N);
                break;
            case S: position.setFacing(CompassDirection.E);
                break;
            case W: position.setFacing(CompassDirection.S);
                break;
        }
    }

    public void rotateRight(){
        switch (position.getFacing()){
            case N :position.setFacing(CompassDirection.E);
                break;
            case E: position.setFacing(CompassDirection.S);
                break;
            case S: position.setFacing(CompassDirection.W);
                break;
            case W: position.setFacing(CompassDirection.N);
                break;
        }
    }

}
