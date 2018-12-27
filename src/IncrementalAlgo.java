import java.util.Iterator;

/**
 * The incremental algorithm in lecture 5.
 * We don't explicitly convert the dataset (d-dim) to a new one (d+1-dim),
 * instead, when it comes to related computation, we add an extra dimension
 * with coordinate 1.
 **/

public class IncrementalAlgo {

    /**
     *   default maximum number of halving guessed gamma
     **/
    private static final int MAX_DECREMENT = 10;

    /**
     * todo
     **/
    private double gamma;

    /**
     *  separation plane
     **/
    private double[] hyperplane;

    /**
     * customized max number of halving guessed gamma
     **/
    private int maxDecrement;

    private Dataset dataset;

    public IncrementalAlgo(Dataset dataset, int maxDecrement) {
        this.maxDecrement = maxDecrement;
        this.dataset = dataset;
    }

    public IncrementalAlgo(Dataset dataset) {
        this(dataset, MAX_DECREMENT);
    }


    public void run() {
        double R2 = dataset.getR2();
        double guessedGamma = Math.sqrt(R2);
        for (int i = 0; i <= maxDecrement; i++) {
            try {
                System.out.println("Try gamma: " + guessedGamma);
                // return a separation plane with margin larger than gamma
                this.hyperplane = new MarginPerceptron(dataset, guessedGamma, R2).run();
                this.gamma = guessedGamma / 2;
                //end this function since we have found the hyperplane we want.
                return;
            } catch (HyperPlaneNotFoundException e) {
                guessedGamma = guessedGamma / 2;
            }
        }
        // no separation plane with margin larger than this gamma
        this.gamma = guessedGamma * 2;
    }

    public double getGamma() {
        return gamma;
    }

    public double[] getHyperplane() {
        return hyperplane;
    }
}
