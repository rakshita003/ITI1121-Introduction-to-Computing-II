public class Geometric extends AbstractSeries {

    // instance variables
    private double sum = 0.0;
    private double index =  0.0;
    
    public double next() {
        sum = sum + 1.0/Math.pow( 2.0,index);
        index = index + 1.0;
        return sum;

        // implement the method

    }
}