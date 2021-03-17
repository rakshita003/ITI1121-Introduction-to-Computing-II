public class Arithmetic extends AbstractSeries {

    // instance variables
    private double sum = 0.0;
    private double index = 1.0;


    public double next() {
        sum += index;
        index += 1.0;
        return sum;

        // implement the method
        
    }
}