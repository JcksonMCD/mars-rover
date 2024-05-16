package vehicle;

import environment.Plateau;
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

    public void rotate(){

    }

}
