package org.northcoders.fundamentals.JavaPlatform;

public class PlateauSize {
    private final int MAX_X;
    private final int MAX_Y;

    public PlateauSize(int maxY, int maxX) {
        this.MAX_Y = maxY;
        this.MAX_X = maxX;
    }

    public int getMAX_X() {
        return MAX_X;
    }

    public int getMAX_Y() {
        return MAX_Y;
    }
}
