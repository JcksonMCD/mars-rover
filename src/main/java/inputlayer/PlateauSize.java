package inputlayer;

public class PlateauSize {
    private static PlateauSize instance;
    private final int MAX_X;
    private final int MAX_Y;

    public PlateauSize(int maxX, int maxY) {
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
