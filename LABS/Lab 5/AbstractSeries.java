public abstract class AbstractSeries implements Series {

    public double[] take(int k) {

        // implement the method
        double[] abs;
        abs = new double[k];
        for(int i = 0; i < k; i++ ){
            abs[i] = next();
        }
        return abs;
        
    }

}