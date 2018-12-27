import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Dataset dataset = new DataReader().readDataset();
        // compute R^2
        dataset.computeR2();
        System.out.println("dimensionality: " + dataset.getDimensionality());
        System.out.println("#positive points: " + dataset.getPositivePoints().size());
        System.out.println("#negative points: " + dataset.getNegativePoints().size());
        IncrementalAlgo incrementalAlgo = new IncrementalAlgo(dataset);
        incrementalAlgo.run();
        if (incrementalAlgo.getHyperplane() == null) {
            System.out.println(" No hyperplane with margin larger than " + incrementalAlgo.getGamma()
                    + " in " + String.valueOf(dataset.getDimensionality() + 1) + "d space.");
        } else {
            System.out.println(" Hyperplane found: " + Arrays.toString(incrementalAlgo.getHyperplane())
                    + " with margin larger than " + incrementalAlgo.getGamma()
                    + " in " + String.valueOf(dataset.getDimensionality() + 1) + "d space.");
            CheckHyperPlane varify = new CheckHyperPlane();
            Boolean trueHyperPlane = varify.checkHyperPlane(incrementalAlgo,dataset);
            if (trueHyperPlane){
                Double realMargin = varify.getMaxMargin();
                System.out.println("After verification, this hyper plane is true for all test data! \n " +
                        "And the real margin is "+realMargin);
            }
        }
    }
}
