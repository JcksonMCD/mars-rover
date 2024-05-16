package org.northcoders.fundamentals.JavaPlatform;

public class Position {
        private int x;
        private int y;
        private CompassDirection facing; // this type can be whatever your direction enum is called

        public Position(int x, int y, CompassDirection facing) {
                this.x = x;
                this.y = y;
                this.facing = facing;
        }
}
