/**
 * An interface that provides methods for reading a raw (unprocessed) dataset.
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public interface DataReader {

	/**
	 * @return the number of columns in the dataset
	 */
	int getNumberOfColumns();

	/**
	 * @return the number of rows (datapoints) in the dataset
	 */
	int getNumberOfDataRows();

	/**
	 * @return the names of the dataset's attributes
	 */
	String[] getAttributeNames();

	/**
	 * @return the data matrix of the dataset
	 */
	String[][] getData();

	/**
	 * @return a string identifier for the data source (for example, the name and
	 *         location of the data source if the source happens to be a file).
	 */
	String getSourceId();
}