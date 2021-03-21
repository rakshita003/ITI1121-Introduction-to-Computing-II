   /**
 * This class enables the calculation and sorting of information gain values
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class InformationGainCalculator {

	/**
	 * @param dataset is the dataset whose attributes we want to analyze and sort
	 *                according to information gain
	 * @return an array of GainInfoItem instances sorted in descending order of gain
	 *         value
	 */
	public static GainInfoItem[] calculateAndSortInformationGains(VirtualDataSet dataset) {
		// WRITE YOUR CODE HERE!
		DataSet[] datasetOriginal = new DataSet[1];
		datasetOriginal[0] = dataset;
		double entropyOverall = EntropyEvaluator.evaluate(datasetOriginal);
		VirtualDataSet[] partitions;
		double gain;
		GainInfoItem[] gainOfAttribute = new GainInfoItem[dataset.getNumberOfAttributes()-1];
		
		//To loop over the number of attributes-1 (minus class attribute), for weather, 4 attributes since class attribute doesn't count
		for(int i=0; i<dataset.getNumberOfAttributes()-1; i++) {

			if(dataset.getAttribute(i).getType() == AttributeType.NOMINAL) {
				//splitting over the unique attribute value, such as "sunny" -- not needed
				
				partitions = dataset.partitionByNominallAttribute(i);
				gain = entropyOverall - EntropyEvaluator.evaluate(partitions);
				gainOfAttribute[i] = new GainInfoItem(dataset.getAttribute(i).getName(), AttributeType.NOMINAL, gain, null);
				}
				
			else {
				//Have to create a new array inside this else statement to store double gain values. Then take the max value of the gain from each partition, and assign that to GainInfoItem
				GainInfoItem[] gainDiffVal = new GainInfoItem[dataset.getUniqueAttributeValues(i).length];

				for (int j=0; j<dataset.getUniqueAttributeValues(i).length ; j++) {
					
					partitions = dataset.partitionByNumericAttribute( i, j);
					if (partitions[0] != null && partitions[1] != null) {
						gain = entropyOverall - EntropyEvaluator.evaluate(partitions);
					}
					else {
						gain = 0;
					}
					
					gainDiffVal[j] = new GainInfoItem(dataset.getAttribute(i).getName(), AttributeType.NUMERIC, gain, dataset.getUniqueAttributeValues(i)[j]);
				}
				GainInfoItem.reverseSort(gainDiffVal);
				gainOfAttribute[i] = gainDiffVal[0];
 			
			}

		}
		
		GainInfoItem.reverseSort(gainOfAttribute);
		return gainOfAttribute;
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java InformationGainCalculator <file name>");
			return;
		}

		String strFilename = args[0];

		ActualDataSet actual = new ActualDataSet(new CSVReader(strFilename));

		// System.out.println(actual);

		VirtualDataSet virtual = actual.toVirtual();

		// System.out.println(virtual);

		GainInfoItem[] items = calculateAndSortInformationGains(virtual);

		// Print out the output
		System.out.println(
				" *** items represent (attribute name, information gain) in descending order of gain value ***");
		System.out.println();

		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}
}
