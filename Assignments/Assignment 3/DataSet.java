/**
 * This abstract class factors out code that is common to both actual and
 * virtual datasets
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public abstract class DataSet {
	/**
	 * the number of attributes in this dataset
	 */
	protected int numAttributes;

	/**
	 * the number of datapoints in this dataset
	 */
	protected int numRows;

	/**
	 * array of attributes. Notice that in A2 and A3, attributes are no longer
	 * represented as name labels (strings). Instead, an attribute is an instance of
	 * the Attribute class.
	 */
	protected Attribute[] attributes;

	/**
	 * Returns the number of attributes
	 * 
	 * @return number of attributes
	 */
	public int getNumberOfAttributes() {
		return numAttributes;
	}

	/**
	 * Returns the number of datapoints
	 * 
	 * @return number of datapoints
	 */
	public int getNumberOfDatapoints() {
		return numRows;
	}

	/**
	 * Returns, for a given attribute name, a COPY of the corresponding Attribute
	 * instance. If the attribute name is not found, the method returns null. To
	 * create a copy of an Attribute instance, we call the clone() method in
	 * Attribute.
	 * 
	 * @param attributeName is the name of the attribute of interest
	 * @return (copy of) Attribute instance whose name field is equal to
	 *         attributeName
	 */
	public Attribute getAttribute(String attributeName) {
		if (attributeName == null) {
			return null;
		}

		for (int i = 0; i < numAttributes; i++)
			if (attributes[i].getName().equals(attributeName)) {
				return attributes[i].clone();
			}

		return null;
	}

	/**
	 * Returns, for a given position index, a COPY of the Attribute at that
	 * position. If the position index is out of range, the method returns null. To
	 * create a copy of an Attribute instance, we call the clone() method in
	 * Attribute.
	 * 
	 * @param attributeIndex is the index of the attribute of interest
	 * @return (copy of) Attribute instance at position attributeIndex
	 */
	public Attribute getAttribute(int attributeIndex) {
		if (attributeIndex < 0 || attributeIndex >= numAttributes) {
			return null;
		}
		return attributes[attributeIndex].clone();
	}

	/**
	 * Finds the index of the attribute with a given name
	 * 
	 * @param attributeName is the name of the attribute of interest
	 * @return index of the attribute whose name is attributeName; -1 if there is no
	 *         attribute whose name is attributeName
	 */
	public int getAttributeIndex(String attributeName) {
		if (attributeName == null || attributeName.isEmpty()) {
			return -1;
		}

		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].getName().equals(attributeName)) {
				return i;
			}
		}

		return -1;

	}

	/**
	 * Abstract method whose implementation should return the value at a given
	 * attribute index of a given datapoint. ActualDataSet and VirtualDataSet will
	 * implement this abstract method differently.
	 * 
	 * @param row            is the row index (valid range: 0 to numRows-1)
	 * @param attributeIndex is the attribute index (valid range: 0 to
	 *                       numAttributes-1)
	 * @return the value of the attributeIndex
	 */
	public abstract String getValueAt(int row, int attributeIndex);

	/**
	 * Returns a reference to an array containing the unique values that an
	 * attribute can assume
	 * 
	 * @param attributeName is the name of the attribute whose unique values must be
	 *                      returned
	 * @return String[] reference to the unique values of the the attribute with the
	 *         given name
	 */
	public String[] getUniqueAttributeValues(String attributeName) {

		if (attributeName == null) {
			return null;
		}

		for (int i = 0; i < attributes.length; i++) {
			if (attributeName.equals(attributes[i].getName())) {
				return getUniqueAttributeValues(i);
			}
		}
		return null;
	}

	/**
	 * Returns a reference to an array containing the unique values that the
	 * attribute at a certain index can assume. Notice that the method calls the
	 * abstract getValueAt(...) method. There is no data matrix in DataSet anymore.
	 * It is also important to notice that, for a virtual dataset, the
	 * attributeIndex, supplied as a parameter here, is relative to how many
	 * attributes there are in that virtual dataset (as opposed to the total number
	 * of attributes in an actual dataset)
	 * 
	 * @param attributeIndex is the index for the attribute whose unique values must
	 *                       be returned
	 * @return a String array containing the unique values of the attribute at the
	 *         given index
	 */
	public String[] getUniqueAttributeValues(int attributeIndex) {

		String[] tempValues = new String[numRows];

		int count = 0;

		for (int i = 0; i < numRows; i++) {
			boolean found = false;

			for (int j = 0; j < count; j++) {
				if (getValueAt(i, attributeIndex).equals(tempValues[j])) {
					found = true;
					break;
				}
			}

			if (!found) {
				tempValues[count++] = getValueAt(i, attributeIndex);
			}
		}

		String[] values = new String[count];

		for (int i = 0; i < count; i++) {
			values[i] = tempValues[i];
		}

		return values;
	}

	/**
	 * @return a string representation of the dataset. ActualDataSet and
	 *         VirtualDataSet will override this method to add additional
	 *         information (while still using this implementation in the DataSet
	 *         class by calling super.toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" - Metadata for attributes:").append(System.lineSeparator());

		for (int i = 0; i < getNumberOfAttributes(); i++)
			buffer.append(getAttribute(i)).append(System.lineSeparator());
		return buffer.toString();
	}
}