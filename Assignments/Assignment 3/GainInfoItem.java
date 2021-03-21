

public class GainInfoItem {

	/**
	 * name of the attribute
	*/
	private String attributeName;

	/**
	 * type of the attribute
	 */
	private AttributeType attributeType;

	/**
	 * gain value calculated for splitting over the attribute
	 */
	private double gainValue;

	/**
	 * For numeric attributes, splitAt is the value at which a binary split yields
	 * the optimal information gain. Although splitAt is always a number, we treat
	 * it as a string. Doing so is helpful because we do not want to lose track of
	 * exactly how a numeric value was written in the source data (for example, was
	 * it written as 10 or 10.0?). To not have to bother with distinguishing integer
	 * and decimal-point numbers, the splitAt value is treated as a string.
	 */
	private String splitAt;

	/**
	 * Constructor for GainInfoItem
	 * 
	 * @param attributeName is the name of the attribute
	 * @param attributeType is the type of the attribute
	 * @param gainValue     is the information gain value
	 * @param splitAt       is the number at which splitting a numeric attribute
	 *                      produces the best (highest) information gain
	 */
	public GainInfoItem(String attributeName, AttributeType attributeType, double gainValue, String splitAt) {
		// WRITE YOUR CODE HERE!
		this.attributeName = attributeName;
		this.attributeType = attributeType;
		this.gainValue = gainValue;
		this.splitAt = splitAt;	
	}

	/**
	 * @return attribute name
	 */
	public String getAttributeName() {
		// WRITE YOUR CODE HERE!
		
		//Remove the following line when this method has been implemented
		return this.attributeName;
	}

	/**
	 * @return attribute type
	 */
	public AttributeType getAttributeType() {
		
		return this.attributeType;
	}

	/**
	 * @return gain value
	 */
	public double getGainValue() {
		
		return this.gainValue;
	}

	/**
	 * @return for numeric attributes, the value at which we the split is optimal
	 */
	public String getSplitAt() {
		
		return this.splitAt;
	}

	/**
	 * String representation of a GainInfoItem instance
	 */
	public String toString() {
		if (this.attributeType == AttributeType.NOMINAL) {
			return "(" + this.attributeName + ", " + String.format("%.6f", this.gainValue) + ")";
		}

		return "(" + this.attributeName + " [split at <= " + this.splitAt + "], "
				+ String.format("%.6f", this.gainValue) + ")";
	}

	/**
	 * Sort function for an array of GainInfoItem instances. This is a reverse sort,
	 * that is, the gain values are sorted in a descending order (highest gain comes
	 * first)
	 * 
	 * @param items is an array of GainInfoItem instances
	 */
	public static void reverseSort(GainInfoItem[] items) {
		// WRITE YOUR CODE HERE!
		
		int i, j, indMax;
        GainInfoItem tmp;

        for (i=0 ; i < items.length; i++){
            indMax = i;
            for (j = i+1; j < items.length; j++){
                if (items[j].gainValue > items[indMax].gainValue) {
                    indMax = j;
                }
            }
            tmp = items[indMax];
            items[indMax] = items[i];
            items[i] = tmp;
        }
		
	}
}		