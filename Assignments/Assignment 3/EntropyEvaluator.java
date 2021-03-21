
public class EntropyEvaluator {

	/**
	 * A static method that calculates the weighted-average entropy of a given set
	 * (array) of datasets. The assignment description provides a detailed
	 * explanation of this calculation. In particular, note that all logarithms are
	 * to base 2. For your convenience, we provide a log2 method. You can use this
	 * method wherever you need to take logarithms in this assignment.
	 * 
	 * @param partitions is the array of datasets to compute the entropy of
	 * @return Shannon's logarithmic entropy (to base 2) for the partitions
	 */
	public static double evaluate(DataSet[] partitions) {
	
		if ( partitions == null || partitions.length == 0 ){
			return -1 ; 

		}

		 // for storing the probablity

        double[] classArray = new double[2]; 

        //for strying the unique value in the last indexed attribute

       	String[] uniqueValueInClass = new String[2];

	   	// to calculate the average of entropy

		double  weightedAvgEntropy = 0.0; 

		// total size of the file

		int tSize = 0;

		double [] weightedEntropy = new double [partitions.length];

		// calculating the size and initializing the weightedEntropy

		for( int i = 0; i < partitions.length; i++ ){

			 tSize += partitions[i].getNumberOfDatapoints();
			 weightedEntropy[i] = 0.0;
		}
		
       for (int i = 0; i < partitions.length; i++) {
			
			int lastIndex = partitions[i].getNumberOfAttributes() - 1;
			
			uniqueValueInClass = partitions[i].getUniqueAttributeValues(lastIndex); //yes,no 

			int count = 0;		

           // checking the number yes or no 

			for ( int j = 0; j < partitions[i].getNumberOfDatapoints(); j++) {

           		 String classNum = partitions[i].getValueAt(j, lastIndex);

				if ( classNum.equals(uniqueValueInClass[0])){

					count ++; 
			    }
		    }

			for (int m = 0; m < classArray.length; m++){

				classArray[m]=0;

			   }
             
			int size = partitions[i].getNumberOfDatapoints();

			// if the probablity is 0 the weightedEntropy is 0

			if ( count == size || count == 0){

				weightedEntropy[i] =0;

			}

			else {

			 	classArray[0] = count / (double) size;  // p0

			 	classArray[1] = (size - count) / (double) size; //p1 = 1 - p0
		
			 	double [] entropy = new double [2];

			 	for (int l = 0; l < classArray.length; l++){

					entropy[l] = 0.0;

				}
	 
				for (int k = 0; k < classArray.length; k++) {
				
				 	entropy[k] = (classArray[k]*(log2(classArray[k]))); 

				  	weightedEntropy[i]  += (-1.0)*(entropy[k]) ;
				 }
	  		 } 
		}
			// calculating the weighted-Average of entropy 

		if ( partitions.length > 1){
				
		    for ( int i = 0; i < partitions.length; i++){
				  
				int p = partitions[i].getNumberOfDatapoints();
				  
				weightedAvgEntropy  += (p / (double) tSize)* weightedEntropy[i] ;
			}
				 
		    return(weightedAvgEntropy);
		}

		else {
			
			for ( int i = 0; i < partitions.length; i++)  
					
			weightedAvgEntropy  += weightedEntropy[i] ;

			return(weightedAvgEntropy);

			}
		
	}

	/**
	 * Calculate base-2 logarithm for a given number
	 * 
	 * @param x is the number to take the logarithm of
	 * @return base-2 logarithm for x
	 */
	public static double log2(double x) {
		return (Math.log(x) / Math.log(2));
	}
}

 // // Entropy
		// double weightedAvgEntropy = 0.0; 
        // double size    = (double) partitions.length;
        // double entropy = 0.0;
        // for (int i = 0; i < partitions.length; i++) {
        //     double p = 1.0* classArray[i]/size;
		// 	if ( classArray[i] > 0)
        //     entropy = entropy - (p*(log2(p))); 
		// 	weightedAvgEntropy  += p*entropy;
        //     }