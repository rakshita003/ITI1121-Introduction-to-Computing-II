/**
 * This is a utility class to simplify the implementation of metadataToString()
 * in the DataSet class.
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class Util {

	/**
	 * Checks if str represents a numeric value
	 * 
	 * @param str is the string to be checked
	 * @return true if str represents a numeric value and false otherwise
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("-?\\d+(\\.\\d+)?");
	}

	/**
	 * Checks an array of strings and returns true if and only if the array is
	 * non-empty and all its elements represent numeric values
	 * 
	 * @param array is the array of elements to be checked
	 * @return true if all array elements represent numbers, and false otherwise.
	 */
	public static boolean isArrayNumeric(String[] array) {

		if (array == null || array.length == 0)
			return false;

		for (int i = 0; i < array.length; i++)
			if (!isNumeric(array[i])) {
				return false;
			}

		return true;
	}

	/**
	 * Produces a string representation of an array of nominals. All nominal labels
	 * will be embraced with single quotes.
	 * 
	 * @param array contains the elements to be combined into a single string
	 *              representation
	 * @return concatenation of the the elements in the array
	 */
	public static String nominalArrayToString(String[] array) {

		if (array == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for (int i = 0; i < array.length; i++) {

			buffer.append('\'').append(array[i]).append('\'');

			if (i < array.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');

		return buffer.toString();
	}

	/**
	 * Produces a string representation of an array of numerics (captured as
	 * strings).
	 * 
	 * @param array contains the (numeric) elements to be combined into a single
	 *              string representation
	 * @return concatenation of the the elements in the array
	 */
	public static String numericArrayToString(String[] array) {

		if (array == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for (int i = 0; i < array.length; i++) {

			buffer.append(array[i]);

			if (i < array.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');

		return buffer.toString();
	}

	/**
	 * Produces a string representation of an array of integers.
	 * 
	 * @param array contains the integer numbers to be combined into a single string
	 *              representation
	 * @return concatenation of the the elements in the array
	 */
	public static String intArrayToString(int[] array) {

		if (array == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for (int i = 0; i < array.length; i++) {

			buffer.append(array[i]);

			if (i < array.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');

		return buffer.toString();
	}
}