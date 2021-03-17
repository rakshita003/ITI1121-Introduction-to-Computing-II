import java.io.File;
import java.util.Scanner;
/**
 * This class provides an implementation of the DataReader interface for CSV
 * files
 * 
 * @author Rakshita Mathur, University of Ottawa
 *
 */

public class CSVReader implements DataReader {
	
	private static final char DELIMITER = ',';

	private String fileID;

	private static final char QUOTE_MARK = '\'';

	private int numColumns;

	private int numRows;

	private String[] attributeNames;

	private String[][] matrix;
 
	/**
	 * Constructs a dataset by loading a CSV file
	 * 
	 * @param strFilename is the name of the file
	 */
	public CSVReader(String filePath) throws Exception {
	
		
		this.fileID = filePath;

		calculateDimensions(filePath);

		attributeNames = new String[numColumns];

		matrix = new String[numRows][numColumns];

		instantiateFromFile(filePath);
	}

	public String[] getAttributeNames() {
		
		return this.attributeNames;
	}

	public String[][] getData() {
		
		return this.matrix;
	}

	public String getSourceId() {

		return this.fileID;
	}

	public int getNumberOfColumns() {
	
		return this.numColumns;
	}

	public int getNumberOfDataRows() {
	
		return this.numRows;
	}

	private void calculateDimensions(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		boolean firstLine = true;

		while (scanner.hasNext()) {
			String str = scanner.nextLine();

			if (!str.trim().isEmpty()) {
				if (firstLine) {
					numColumns = countColumns(str);
					firstLine = false;
				} else {
					numRows++;
				}
			}
		}

		scanner.close();
	}

	private void instantiateFromFile(String strFilename) throws Exception {
		
		Scanner scanner = new Scanner(new File(strFilename));

		boolean firstLine = true;

		int rowNum = 0;

		while (scanner.hasNext()) {
			String str = scanner.nextLine();

			if (!str.trim().isEmpty()) {

				if (firstLine) {
					firstLine = false;
					populateAttributeNames(str);

				} else {
					populateRow(str, rowNum++);
				}
			}
		}

		scanner.close();
	}

	private String[] populateAttributeNames(String str) {

		if (str == null || str.isEmpty()) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();

		boolean isInQuote = false;

		int position = 0;

		char[] chars = str.toCharArray();
		char ch;
		
		for (int i = 0; i < chars.length; i++) {

			ch = chars[i];

			if (isInQuote) {
				if (ch == QUOTE_MARK) {
					isInQuote = false;
				} else {
					buffer.append(ch);
				}

			} else if (ch == QUOTE_MARK) {
				isInQuote = true;
			} else if (ch == DELIMITER) {
				attributeNames[position++] = buffer.toString().trim();
				buffer.delete(0, buffer.length());
			} else {
				buffer.append(ch);
			}
		}

		if (buffer.toString().trim().length() > 0) { // deal with last attribute name
			attributeNames[position++] = buffer.toString().trim();
		}
		return attributeNames;
	}

	private void populateRow(String str, int currentRow) {

		if (str == null || str.isEmpty()) {
			return;
		}

		StringBuffer buffer = new StringBuffer();

		boolean isInQuote = false;

		int position = 0;

		char[] chars = str.toCharArray();
		char ch;

		for (int i = 0; i < chars.length; i++) {

			ch = chars[i];

			if (isInQuote) {
				if (ch == QUOTE_MARK) {
					isInQuote = false;
				} else {
					buffer.append(ch);
				}

			} else if (ch == QUOTE_MARK) {
				isInQuote = true;
			} else if (ch == DELIMITER) {
				matrix[currentRow][position++] = buffer.toString().trim();
				buffer.delete(0, buffer.length());
			} else {
				buffer.append(ch);
			}
		}

		if (buffer.toString().trim().length() > 0) { // deal with last attribute value
			matrix[currentRow][position++] = buffer.toString().trim();
		} else if (chars[chars.length - 1] == ',') {// deal with potentially missing last attribute value
			matrix[currentRow][position++] = "";
		}
	}

	private static int countColumns(String str) {

		int count = 0;

		if (str == null || str.isEmpty()) {
			return count;
		}

		char[] chars = str.toCharArray();
		boolean isInQuote = false;
		char ch;

		for (int i = 0; i < chars.length; i++) {
			ch = chars[i];

			if (isInQuote) {
				if (ch == QUOTE_MARK) {
					isInQuote = false;
				}
			} else if (ch == QUOTE_MARK) {
				isInQuote = true;
			} else if (ch == DELIMITER) {
				count++;
			}
		}

		return count + 1;
	}
}
