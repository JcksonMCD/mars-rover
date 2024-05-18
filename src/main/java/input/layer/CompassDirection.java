package input.layer;

public enum CompassDirection {
    N (0, -1),
    E (1, 0),
    S (0, 1),
    W (-1, 0);

    final int xVector;
    final int yVector;

    CompassDirection(int xVector, int yVector) {
        this.xVector = xVector;
        this.yVector = yVector;
    }

    public int getxVector() {
        return xVector;
    }

    public int getyVector() {
        return yVector;
    }
}
