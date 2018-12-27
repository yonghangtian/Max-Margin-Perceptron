import java.util.Iterator;

/**
 * @author tianyh
 * this part is to verify the hyperplane we found after Margin Perceptron algo.
 * use this part if we found hyperplane.
 */
public class CheckHyperPlane {
    /**
     * this margin is the real biggest distance from all points to hyperplane
     */
    private double maxMargin = 0;

    public double getMaxMargin() {
        return maxMargin;
    }

    /**
     * checkHyperPlane = true iff no points from dataset violates this hyperplane
     */
    public boolean checkHyperPlane(IncrementalAlgo incrementalAlgo,Dataset dataset){
        double label = 0;
        double margin = 0;
        this.maxMargin = dataset.getR2();
        double[] hyperPlane = incrementalAlgo.getHyperplane();
        // magnitude of hyper plane verctor
        double magnitude = computeVectorMagnitude(hyperPlane);
        if(hyperPlane.length != (dataset.getDimensionality()+1)){
            System.out.println("Dimensionality Error! ");
            return false;
        }
        else{
            Iterator<Point> iterator = dataset.getPositivePoints().iterator();
            while (iterator.hasNext()){
                double[] coordinates = iterator.next().getCoordinates();
                int length = hyperPlane.length;
                for (int i = 0; i < length-1; i++) {
                    //every time add one dimensionality.
                    label = label + hyperPlane[i]*coordinates[i];
                }
                label += hyperPlane[length-1];
                if (label < 0){
                    return false;
                }
                else {
                    margin = label/magnitude;
                    if(margin < this.maxMargin){
                        this.maxMargin = margin;
                    }
                    label = 0;
                }
            }
            iterator = dataset.getNegativePoints().iterator();
            while (iterator.hasNext()){
                double[] coordinates = iterator.next().getCoordinates();
                int length = hyperPlane.length;
                for (int i = 0; i < length-1; i++) {
                    //every time add the product of one dimensionality.
                    label = label + hyperPlane[i]*coordinates[i];
                }
                label +=hyperPlane[length-1];
                if (label > 0){
                    return false;
                }
                else {
                    margin = (-1)*label/magnitude;
                    if (margin < this.maxMargin){
                        this.maxMargin = margin;
                    }
                    label = 0;
                }
            }
        }
        return true;
    }
    private double computeVectorMagnitude(double[] vector){
        double result = 0;
        for(double coordinate:vector){
            result = result + coordinate*coordinate;
        }
        result = Math.sqrt(result);
        return result;
    }
}

