/**
 * This class is used for representing an actual dataset, that is, a dataset
 * that holds a data matrix
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class ActualDataSet extends DataSet {
	/**
	 * The data matrix
	 */
	private String[][] matrix;

	/**
	 * The source identifier for the data. When the data source is a file, sourceId
	 * will be the name and location of the source file
	 */
	private String dataSourceId;

	/**
	 * Constructor for ActualDataSet. In addition to initializing dataSourceId,
	 * numAttributes, numRows and matrix, the constructor needs to create an array of
	 * attributes (instance of the Attribute class) and initialize the "attributes"
	 * instance variable of DataSet.
	 * 
	 * 
	 * @param reader is the DataReader instance to read data from.
	 */
	public ActualDataSet(DataReader reader) {

		this.dataSourceId = reader.getSourceId();
		this.numAttributes = reader.getNumberOfColumns();
		this.numRows = reader.getNumberOfDataRows();
		this.matrix = reader.getData();

		createAttributes(reader.getAttributeNames());

	}

	/**
	 * Implementation of DataSet's abstract getValueAt method for an actual dataset
	 */
	public String getValueAt(int row, int attributeIndex) {
		if (row < 0 || row >= numRows)
			return null;

		if (attributeIndex < 0 || attributeIndex >= numAttributes)
			return null;

		return matrix[row][attributeIndex];
	}

	/**
	 * @return the sourceId of the dataset.
	 */
	public String getSourceId() {
		return dataSourceId;
	}

	private void createAttributes(String[] attributeNames) {
		this.attributes = new Attribute[numAttributes];

		for (int i = 0; i < numAttributes; i++) {
			String[] values = getUniqueAttributeValues(i);
			if (Util.isArrayNumeric(values))
				this.attributes[i] = new Attribute(attributeNames[i], i, AttributeType.NUMERIC, values);

			else
				this.attributes[i] = new Attribute(attributeNames[i], i, AttributeType.NOMINAL, values);
		}
	}

	/**
	 * Returns a virtual dataset over this (actual) dataset
	 * 
	 * @return a virtual dataset spanning the entire data in this (actual) dataset
	 */
	public VirtualDataSet toVirtual() {
		int[] rows = new int[numRows];

		for (int i = 0; i < numRows; i++) {
			rows[i] = i;
		}

		return new VirtualDataSet(this, rows, attributes, "");
	}

	/**
	 * Override of toString() in DataSet
	 * 
	 * @return a string representation of this (actual) dataset.
	 */
	public String toString() {
		return "Actual dataset (" + getSourceId() + ") with " + numAttributes + " attribute(s) and " + numRows + " row(s)"
				+ System.lineSeparator() + super.toString();
	}
}