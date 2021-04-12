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

		if ( dataset == null){
			throw new IllegalArgumentException("dataset can't be null");
		}
		int n = dataset.getNumberOfAttributes() - 1; // Minus 1 because we assume the last attribute is the class (to predict)

		if (n <= 0) {
			// System.out.println("No attributes to split on!");
			// return null;
			throw new ArrayIndexOutOfBoundsException();
		}

		GainInfoItem[] items = new GainInfoItem[n];

		VirtualDataSet[] partitions = new VirtualDataSet[1];

		partitions[0] = dataset;

		double beforeSplit = EntropyEvaluator.evaluate(partitions);

		for (int i = 0; i < n; i++) {

			Attribute attribute = dataset.getAttribute(i);

			if (attribute.getType() == AttributeType.NOMINAL) {

				partitions = dataset.partitionByNominallAttribute(i);

				// EXAMPLE GENERATOR: BEGIN
				// if (attribute.getName().equals("outlook")) {
				// for (int q = 0; q < partitions.length; q++)
				// System.out.println(partitions[q]);
				// }
				// EXAMPLE GENERATOR: END

				double afterSplit = EntropyEvaluator.evaluate(partitions);

				double gain = beforeSplit - afterSplit;

				items[i] = new GainInfoItem(attribute.getName(), attribute.getType(), gain, "");

			} else { // Attribute is numeric

				String[] values = attribute.getValues();

				int bestValueIndex = 0;
				double bestGain = 0d;

				for (int j = 0; j < values.length; j++) {

					partitions = dataset.partitionByNumericAttribute(i, j);

					// EXAMPLE GENERATOR: BEGIN
					// if (attribute.getName().equals("humidity") && values[j].equals("80")) {
					// for (int q = 0; q < partitions.length; q++)
					// System.out.println(partitions[q]);
					// }
					// EXAMPLE GENERATOR: END

					double afterSplit = EntropyEvaluator.evaluate(partitions);
					double gain = beforeSplit - afterSplit;

					if (gain > bestGain) {
						bestValueIndex = j;
						bestGain = gain;
					}
				}

				items[i] = new GainInfoItem(attribute.getName(), attribute.getType(), bestGain, values[bestValueIndex]);
			}
		}

		GainInfoItem.reverseSort(items);

		return items;
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
