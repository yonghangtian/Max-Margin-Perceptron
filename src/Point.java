public class Point {

    private double[] coordinates;
    private boolean label;

    public Point(double[] coordinates, boolean label) {
        this.label = label;
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public boolean getLabel() {
        return label;
    }
}
