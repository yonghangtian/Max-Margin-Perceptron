import java.util.Set;

/**
 * The margin perceptron in lecture 5.
 * We don't explicitly convert the dataset (d-dim) to a new one (d+1-dim),
 * instead, when it comes to related computation, we add an extra dimension
 * with coordinate 1.
 */
public class MarginPerceptron {

    /*
        the guessed gamma from the incremental algorithm
     */
    private double gamma;

    /*
        half of the guessed gamma, it is used to determine violations
     */
    private double gamma_2;

    /*
        R^2 , R is the max Euclidean distance from a point to the origin
     */
    private double R2;

    /*
        separation plane if exists
     */
    private double[] hyperplane;

    private double normalization;

    private Dataset dataset;

    public MarginPerceptron(Dataset dataset, double guessedGamma, double R2) {
        this.dataset = dataset;
        this.R2 = R2;
        this.gamma = guessedGamma;
        this.gamma_2 = gamma / 2;
        this.hyperplane = new double[this.dataset.getDimensionality() + 1]; // extra dimension
    }

    public double[] run() throws HyperPlaneNotFoundException {
        //todo
        int maxIteration = 12 * (int) (Math.ceil(R2 / (gamma * gamma)));
        Set<Point> positivePoints = dataset.getPositivePoints();
        Set<Point> negativePoints = dataset.getNegativePoints();
        for (int i = 0; i < maxIteration; i++) {
            boolean update = false;
            for (Point p : positivePoints) {
                if (violate(p)) {
                    updateHyperplane(p);
                    update = true;
                    break;
                }
            }
            if (update) {
                continue;
            }
            for (Point p : negativePoints) {
                if (violate(p)) {
                    updateHyperplane(p);
                    update = true;
                    break;
                }
            }
            if (update) {
                continue;
            }
            return hyperplane;
        }
        throw new HyperPlaneNotFoundException();
    }

    private void updateHyperplane(Point point) {
        double[] coordinates = point.getCoordinates();
        if (point.getLabel()) {
            for (int i = 0; i < coordinates.length; i++) {
                hyperplane[i] = hyperplane[i] + coordinates[i];
            }
            // extra dimension
            hyperplane[hyperplane.length - 1] += 1;
        } else {
            for (int i = 0; i < coordinates.length; i++) {
                hyperplane[i] = hyperplane[i] - coordinates[i];
            }
            // extra dimension
            hyperplane[hyperplane.length - 1] -= 1;
        }

        this.normalization = 0;
        for (double aHyperplane : hyperplane) {
            this.normalization = this.normalization + aHyperplane * aHyperplane;
        }
        this.normalization = Math.sqrt(normalization);
    }

    private boolean violate(Point point) {
        double dotProduct = 0.0;
        double[] coordinates = point.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            dotProduct = dotProduct + hyperplane[i] * coordinates[i];
        }
        // extra dimension
        dotProduct = dotProduct + hyperplane[hyperplane.length - 1];
        if (this.normalization == 0) {
            return true;
        }
        dotProduct = dotProduct / this.normalization;
        if (dotProduct < gamma_2 && point.getLabel()) {
//            System.out.println("Gamma: " + gamma + " dotproduct: " + dotProduct + " label: " + point.getLabel());
            return true;
        }
        if (dotProduct > (-1 * gamma_2) && !point.getLabel()) {
//            System.out.println("Gamma: " + gamma + " dotproduct: " + dotProduct + " label: " + point.getLabel());
            return true;
        }
        return false;
    }

}
