/**
 * This class is used for representing an actual dataset, that is, a dataset
 * that holds a data matrix
 * 
 * @author Rakshita Mathur, University of Ottawa
 *
 */
public class ActualDataSet extends DataSet {

	private String[][] matrix;

   	/**
	 * The source identifier for the data. When the data source is a file, sourceId
	 * will be the name and location of the source file
	 */
	private String dataSourceId;

	private Attribute[] attributesData;
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
		//this.attributes = new Attribute[this.numAttributes];
		//populate this.attributes using getUniqueAttributeValues
		attributesData = new Attribute[this.numAttributes];
		AttributeType typeOfAtt;
		String[] uniqueValues;
		String[] namesOfAttributes;

		for(int i=0; i < this.numAttributes; i++) {
			uniqueValues = super.getUniqueAttributeValues(i);
			namesOfAttributes = reader.getAttributeNames();
	
			if (Util.isArrayNumeric(uniqueValues) == true) {
				typeOfAtt = AttributeType.NUMERIC;
			}
			else {
				typeOfAtt = AttributeType.NOMINAL;
				}

			attributesData[i] = new Attribute(namesOfAttributes[i], i, typeOfAtt, uniqueValues);
		}
		this.attributes = attributesData;
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
	
			return this.dataSourceId;
	}
    /**
	 * Returns a virtual dataset over this (actual) dataset
	 * 
	 * @return a virtual dataset spanning the entire data in this (actual) dataset
	 */
	public VirtualDataSet toVirtual() {

		//ActualDataSet actualdataset = new ActualDataSet((DataReader) this);
		
		Attribute[] attribute = new Attribute[attributes.length];
		for(int i = 0; i < attributes.length; i++){

			attribute[i] = this.attributes[i];
		}
		
        int[] maprows = new int[this.numRows];
		for(int i = 0; i < this.numRows; i++){

			maprows[i] = i;
		}

		return (new VirtualDataSet(this, maprows ,attribute));
	}
    /**
	 * Override of toString() in DataSet
	 * 
	 * @return a string representation of this (actual) dataset.
	 */
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Actual dataset (" + getSourceId() + ") with " + getNumberOfAttributes() + " attribute(s) and "
				+ getNumberOfDatapoints() + " row(s) \n " );
		buffer.append(super.toString());

		return buffer.toString();
	} 
}
