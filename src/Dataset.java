import java.util.Iterator;
import java.util.Set;

public class Dataset {

    private int dimensionality;
    private Set<Point> positivePoints;
    private Set<Point> negativePoints;
    /**
     *   R^2 , R is the max Euclidean distance from a point to the origin
     **/
    private double R2 = -1;

    /**
     * Scan the whole dataset to find R^2.
     */
    public void computeR2() {
        Iterator<Point> iterator = this.positivePoints.iterator();
        while (iterator.hasNext()) {
            double sn = squareNorm(iterator.next());
            if (sn > this.R2) {
                this.R2 = sn;
            }
        }
        iterator = this.negativePoints.iterator();
        while (iterator.hasNext()) {
            double sn = squareNorm(iterator.next());
            if (sn > this.R2) {
                this.R2 = sn;
            }
        }
    }


    /**
     *    Compute the square of l2 norm of a point.
     **/
    private double squareNorm(Point point) {
        double ns = 0;
        double[] coordinates = point.getCoordinates();
        for (double coordinate : coordinates) {
            ns = ns + coordinate * coordinate;
        }
        // the additional dimension with coordinate 1
        ns = ns + 1;
        return ns;
    }

    public double getR2() {
        return R2;
    }

    public int getDimensionality() {
        return dimensionality;
    }

    public void setDimensionality(int dimensionality) {
        this.dimensionality = dimensionality;
    }

    public Set<Point> getPositivePoints() {
        return positivePoints;
    }

    public void setPositivePoints(Set<Point> positivePoints) {
        this.positivePoints = positivePoints;
    }

    public Set<Point> getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(Set<Point> negativePoints) {
        this.negativePoints = negativePoints;
    }
}
