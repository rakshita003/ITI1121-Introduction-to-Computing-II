import java.util.LinkedList;

/**
 * This class is used for representing a virtual dataset, that is, a dataset
 * that is a view over an actual dataset. A virtual dataset has no data matrix
 * of its own.
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class VirtualDataSet extends DataSet {

	/**
	 * reference to the source dataset (instance of ActualDataSet)
	 */
	private ActualDataSet source;

	/**
	 * array of integers mapping the rows of this virtual dataset to the rows of its
	 * source (actual) dataset
	 */
	private int[] map;

	/**
	 * the split condition that induced the dataset
	 */
	private String condition;

	/**
	 * Constructor for VirtualDataSet. There are two important considerations here:
	 * (1) Make sure that you keep COPIES of the "rows" and "attributes" passed as
	 * formal parameters. Do not, for example, say this.map = rows. Instead, create
	 * a copy of rows before assigning that copy to this.map. (2) Prune the value
	 * sets of the attributes. Since a virtual dataset is only a subset of an actual
	 * dataset, it is likely that some or all of its attributes may have smaller
	 * value sets.
	 * 
	 * @param source     is the source dataset (always an instance of ActualDataSet)
	 * @param rows       is the set of rows from the source dataset that belong to
	 *                   this virtual dataset
	 * @param attributes is the set of attributes belonging to this virtual dataset.
	 *                   IMPORTANT: you need to recalculate the unique value sets
	 *                   for these attributes according to the rows. Why? Because
	 *                   this virtual set is only a subset of the source dataset and
	 *                   its attributes potentially have fewer unique values.
	 * @param condition  The split condition (if any) that induced the virtual dataset
	 */
	public VirtualDataSet(ActualDataSet source, int[] rows, Attribute[] attributes, String condition) {
		this.source = source;

		this.numRows = rows.length;

		this.condition = condition;

		this.map = new int[this.numRows];

		for (int i = 0; i < this.numRows; i++) {
			this.map[i] = rows[i];
		}

		this.numAttributes = attributes.length;

		this.attributes = new Attribute[numAttributes];

		for (int i = 0; i < numAttributes; i++) {
			this.attributes[i] = attributes[i].clone();
		}

		pruneAttributeValues();
	}

	/**
	 * String representation of the virtual dataset.
	 */
	public String toString() {
		return "Virtual dataset with " + numAttributes + " attribute(s) and " + numRows + " row(s)"
				+ System.lineSeparator() + " - Dataset is a view over " + source.getSourceId() + System.lineSeparator()
				+ " - Split condition: " + condition + System.lineSeparator()
				+ " - Row indices in this dataset (w.r.t. its source dataset): " + Util.intArrayToString(map)
				+ System.lineSeparator() + super.toString();
	}

	/**
	 * Returns the split condition that induced the dataset
	 * 
	 * @return the split condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Implementation of DataSet's getValueAt abstract method for virtual datasets.
	 * Hint: You need to call source.getValueAt(...). What you need to figure out is
	 * with what parameter values that method needs to be called.
	 */
	public String getValueAt(int row, int attributeIndex) {
		if (row < 0 || row >= numRows)
			return null;

		if (attributeIndex < 0 || attributeIndex >= numAttributes)
			return null;

		return source.getValueAt(map[row], attributes[attributeIndex].getAbsoluteIndex());
	}

	/**
	 * @return reference to source dataset
	 */
	public ActualDataSet getSourceDataSet() {
		return source;
	}

	private void pruneAttributeValues() {
		for (int i = 0; i < numAttributes; i++) {
			attributes[i].replaceValues(getUniqueAttributeValues(i));
		}
	}

	/**
	 * This method splits the virtual dataset over a nominal attribute. This process
	 * has been discussed and exemplified in detail in the assignment description.
	 * 
	 * @param attributeIndex is the index of the nominal attribute over which we
	 *                       want to split.
	 * @return a set (array) of partitions resulting from the split. The partitions
	 *         will no longer contain the attribute over which we performed the
	 *         split.
	 */
	public VirtualDataSet[] partitionByNominallAttribute(int attributeIndex) {

		if (attributeIndex < 0 || attributeIndex >= this.getNumberOfAttributes())
			return null;

		Attribute attribute = getAttribute(attributeIndex);

		LinkedList<LinkedList<Integer>> partitions;

		partitions = new LinkedList<LinkedList<Integer>>();

		for (int i = 0; i < numRows; i++) {
			String attrValue = getValueAt(i, attributeIndex);

			boolean isAllocated = false;

			for (int j = 0; j < partitions.size(); j++) {
				if (attrValue.equals(source.getValueAt(partitions.get(j).get(0), attribute.getAbsoluteIndex()))) {
					partitions.get(j).add(map[i]);
					isAllocated = true;
					break;
				}
			}
			if (!isAllocated) {
				partitions.add(new LinkedList<Integer>());
				partitions.get(partitions.size() - 1).add(map[i]);
				continue;
			}
		}

		// Now, we have the partitions; build the DataSets
		// Note that nominal attribute has to be dropped
		VirtualDataSet[] datasets = new VirtualDataSet[partitions.size()];
		Attribute[] reducedAttributes = new Attribute[attributes.length - 1];

		int index = 0;

		for (int i = 0; i < attributes.length; i++) {
			if (i != attributeIndex) {
				reducedAttributes[index++] = attributes[i];
			}
		}

		for (int i = 0; i < datasets.length; i++) {
			int[] rows = new int[partitions.get(i).size()];
			for (int z = 0; z < rows.length; z++) {
				rows[z] = partitions.get(i).get(z);
			}

			datasets[i] = new VirtualDataSet(source, rows, reducedAttributes, attributes[attributeIndex].getName()
					+ " is \'" + source.getValueAt(partitions.get(i).get(0), attribute.getAbsoluteIndex()) +"\'");
		}

		return datasets;
	}

	/**
	 * This method splits the virtual dataset over a given numeric attribute at a
	 * specific value from the value set of that attribute. This process has been
	 * discussed and exemplified in detail in the assignment description.
	 * 
	 * @param attributeIndex is the index of the numeric attribute over which we
	 *                       want to split.
	 * @param valueIndex     is the index of the value (in the value set of the
	 *                       attribute of interest) to use for splitting
	 * @return a pair of partitions (VirtualDataSet array of length two) resulting
	 *         from the two-way split. Note that the partitions will retain the
	 *         attribute over which we perform the split. This is in contrast to
	 *         splitting over a nominal, where the split attribute disappears from
	 *         the partitions.
	 */
	public VirtualDataSet[] partitionByNumericAttribute(int attributeIndex, int valueIndex) {

		if (attributeIndex < 0 || attributeIndex >= this.getNumberOfAttributes())
			return null;

		Attribute attribute = this.getAttribute(attributeIndex);

		if (attribute.getType() != AttributeType.NUMERIC) {
			return null;
		}

		String[] values = attribute.getValues();

		if (values == null || values.length == 0) {
			return null;
		}

		if (valueIndex < 0 || valueIndex >= values.length) {
			return null;
		}

		double threshold = Double.parseDouble(values[valueIndex]);

		LinkedList<LinkedList<Integer>> partitions;

		partitions = new LinkedList<LinkedList<Integer>>();

		// There will be two partitions only
		partitions.add(new LinkedList<Integer>());
		partitions.add(new LinkedList<Integer>());

		for (int i = 0; i < numRows; i++) {
			double attrValue = Double.parseDouble(getValueAt(i, attributeIndex));

			if (attrValue <= threshold) {
				partitions.get(0).add(map[i]);
			} else {
				partitions.get(1).add(map[i]);
			}
		}

		// Now we have the partitions; build the DataSets
		// No attributes will be dropped in numeric partitioning
		VirtualDataSet[] datasets = new VirtualDataSet[partitions.size()];

		for (int i = 0; i < datasets.length; i++) {
			int[] rows = new int[partitions.get(i).size()];
			for (int z = 0; z < rows.length; z++) {
				rows[z] = partitions.get(i).get(z);
			}

			datasets[i] = new VirtualDataSet(source, rows, attributes,
					(i == 0) ? attribute.getName() + " <= " + values[valueIndex]
							: attribute.getName() + " > " + values[valueIndex]);
		}

		return datasets;
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		System.out.println("============================================");
		System.out.println("THE WEATHER-NOMINAL DATASET:");
		System.out.println();

		ActualDataSet figure5Actual = new ActualDataSet(new CSVReader(
				"/Users/mehrdad/Dropbox/ITI1121/2021/assignments/A2/a2_3000000_3000001/datasets/weather-nominal.csv"));

		System.out.println(figure5Actual);

		VirtualDataSet figure5Virtual = figure5Actual.toVirtual();

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 5:");
		System.out.println();

		VirtualDataSet[] figure5Partitions = figure5Virtual
				.partitionByNominallAttribute(figure5Virtual.getAttributeIndex("outlook"));

		for (int i = 0; i < figure5Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure5Partitions[i]);

		/////// NOW SPLIT A PARTITION!

		System.out.println("Now, let\'s split Partition 0 from above over humidity:");
		System.out.println();

		VirtualDataSet[] nextLevel = figure5Partitions[0]
				.partitionByNominallAttribute(figure5Partitions[0].getAttributeIndex("humidity"));

		for (int i = 0; i < nextLevel.length; i++)
			System.out.println("Partition " + i + ": " + nextLevel[i]);

		System.out.println("And last, let\'s split Partition 0 from above over windy:");
		System.out.println();

		VirtualDataSet[] twoLevelsAfter = nextLevel[0]
				.partitionByNominallAttribute(nextLevel[0].getAttributeIndex("windy"));

		for (int i = 0; i < twoLevelsAfter.length; i++)
			System.out.println("Partition " + i + ": " + twoLevelsAfter[i]);

		///////

		System.out.println("============================================");
		System.out.println("THE WEATHER-NUMERIC DATASET:");
		System.out.println();

		ActualDataSet figure9Actual = new ActualDataSet(new CSVReader(
				"/Users/mehrdad/Dropbox/ITI1121/2021/assignments/A2/a2_3000000_3000001/datasets/weather-numeric.csv"));

		System.out.println(figure9Actual);

		VirtualDataSet figure9Virtual = figure9Actual.toVirtual();

		// Now let's figure out what is the index for humidity in figure9Virtual and
		// what is the index for "80" in the value set of humidity!

		int indexForHumidity = figure9Virtual.getAttributeIndex("humidity");

		Attribute humidity = figure9Virtual.getAttribute(indexForHumidity);

		String[] values = humidity.getValues();

		int indexFor80 = -1;

		for (int i = 0; i < values.length; i++) {
			if (values[i].equals("80")) {
				indexFor80 = i;
				break;
			}
		}

		if (indexFor80 == -1) {
			System.out.println("Houston, we have a problem!");
			return;
		}

		VirtualDataSet[] figure9Partitions = figure9Virtual.partitionByNumericAttribute(indexForHumidity, indexFor80);

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 9:");
		System.out.println();

		for (int i = 0; i < figure9Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure9Partitions[i]);

		/////// NOW SPLIT A PARTITION!

		System.out.println("Now let\'s split Partition 0 from above over windy:");
		System.out.println();

		nextLevel = figure9Partitions[0].partitionByNominallAttribute(figure9Partitions[0].getAttributeIndex("windy"));

		for (int i = 0; i < nextLevel.length; i++)
			System.out.println("Partition " + i + ": " + nextLevel[i]);

		///////
	}
}