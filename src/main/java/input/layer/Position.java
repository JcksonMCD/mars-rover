package input.layer;

public class Position {
        private int x;
        private int y;
        private CompassDirection facing; // this type can be whatever your direction enum is called

        public Position(int x, int y, CompassDirection facing) {
                this.x = x;
                this.y = y;
                this.facing = facing;
        }

        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public CompassDirection getFacing() {
                return facing;
        }
}