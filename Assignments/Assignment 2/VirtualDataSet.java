/**
 * This class is used for representing a virtual dataset, that is, a dataset
 * that is a view over an actual dataset. A virtual dataset has no data matrix
 * of its own.
 * 
 * @author Rakshita Mathur, University of Ottawa
 *
 */

import java.util.LinkedList;


public class VirtualDataSet extends DataSet {

	private ActualDataSet source;

	private int[] map;

	public VirtualDataSet(ActualDataSet source, int[] rows, Attribute[] attributes) {
		
		this.source = source;
		
		int[] rowsCopy = new int[rows.length];
		
		for (int i = 0; i < rowsCopy.length; i++) {
			
			rowsCopy[i] = rows[i];
		}
		
		this.map = rowsCopy;
		
		Attribute[] attributesVirtual = new Attribute[attributes.length];
		
		for (int i = 0; i < attributesVirtual.length; i++) {
			
			attributesVirtual[i] = attributes[i].clone();
			int absoluteIndexOfAttribute = attributesVirtual[i].getAbsoluteIndex();
			attributesVirtual[i].replaceValues(getUniqueAttributeValuesVirtual(absoluteIndexOfAttribute));
		}
		
		this.attributes = attributesVirtual;

		this.numAttributes = this.attributes.length;
		this.numRows = this.map.length;
	}

	private String[] getUniqueAttributeValuesVirtual(int column) {

		String[] tempValues = new String[map.length];

		int count = 0;

		for (int i = 0; i < map.length; i++) {
			boolean found = false;
			
			for (int j = 0; j < i; j++) {

				String attributeValue = this.source.getValueAt(map[i], column);
				if (attributeValue.equals(tempValues[j])) {
					found = true;
					break;
				}
			}

			if (!found) {
				String attributeValueNew = this.source.getValueAt(map[i], column);
				
				tempValues[count++] = attributeValueNew;
			}
		}

		String[] uniqueValues = new String[count];

		for (int i = 0; i < count; i++) {
			uniqueValues[i] = tempValues[i];
		}

		return uniqueValues;
	}


	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Vritual dataset with " + this.attributes.length
				+ " attribute(s) and "
				+ this.map.length + " row(s)" + System.lineSeparator());
		buffer.append(" - Dataset is a view over " + this.source.getSourceId() + System.lineSeparator() 
		+ " - Row indices in this dataset (w.r.t. its source dataset): [");
		for (int i=0; i<this.map.length; i++) {
			if (i < this.map.length-1) {
				buffer.append(this.map[i] + ", ");
			}
			else {
				buffer.append(this.map[i] + "] \n");
			}
		}
		buffer.append(super.toString());
		return buffer.toString();
		
	}


	public String getValueAt(int row, int attributeIndex) {
		
	if (attributeIndex >= this.attributes.length || attributeIndex < 0) {
			return null;
		}

		int actualIndex = this.attributes[attributeIndex].getAbsoluteIndex();
		String value = source.getValueAt(map[row], actualIndex);
		return value;
	}


	public ActualDataSet getSourceDataSet() {
		
		return this.source;
	}

	public VirtualDataSet[] partitionByNominallAttribute(int attributeIndex) {
		

		if (attributeIndex >= this.attributes.length || attributeIndex < 0) {
			return null;
		}

		int actualInd = (this.attributes[attributeIndex]).getAbsoluteIndex();

		String[] uniqueValues = (this.attributes[actualInd]).getValues();

		VirtualDataSet[] partitions = new VirtualDataSet[uniqueValues.length];

		for (int i = 0; i < partitions.length; i++) {  
			
			LinkedList<Integer> rowsList = new LinkedList<>();

			for (int k = 0; k < this.map.length; k++) {

				String attributeValue = this.getValueAt(k, attributeIndex);
				if (attributeValue.equals(uniqueValues[i])) {
					rowsList.add(map[k]);
				}
			}
		
			Object[] partitionRows = rowsList.toArray();
			int[] rowsNew = new int[rowsList.size()];
			
			for (int l = 0; l < partitionRows.length; l++) {
				
				rowsNew[l] = (int) partitionRows[l];
			}

			Attribute[] attributesParted = new Attribute[this.attributes.length - 1];
			int count = 0;
			for (int j = 0; j < this.attributes.length; j++) {
				if (j != attributeIndex) {
					attributesParted[count] = this.attributes[j];
					count++;
				}
			}
			
			partitions[i] = new VirtualDataSet(this.source, rowsNew, attributesParted);
		}
		return partitions;
	}
	
	public VirtualDataSet[] partitionByNumericAttribute(int attributeIndex, int valueIndex) {

		if (attributeIndex >= this.attributes.length || attributeIndex < 0) {
			return null;
		}

		//getting the absolute index in the int actualIndex.
        int actualIndex = (this.attributes[attributeIndex]).getAbsoluteIndex();

		//getting the unique values present in at the actualIndex.
		String[] uniqueValues = (this.attributes[actualIndex]).getValues();

		//making int[] for parsing the string values of uniqueValues to integers for comparision.
		int[] uniqueValuesInt = new int[uniqueValues.length];

		//making a partitions virtial dataset array for 2 partitions(less-than-equal-to and greater-than)
		VirtualDataSet[] partitions = new VirtualDataSet[2];
		
		//getting the value at the index provided. The target value for partition
		String value = this.getValueAt(valueIndex, attributeIndex); 

		//parsing the string value to int for comapring. 	
		int valueInt = Integer.parseInt(value);

		//getting the attributes.
		Attribute[] attributesParted = new Attribute[this.attributes.length];

		int count = 0;
		for (int j = 0; j < this.attributes.length; j++) {

				attributesParted[count] = this.attributes[j];
				count++;
			
			}

		//creating integer linked list for storing the values in dynamic state.
		//lList for the values less than and equal to the target value.	
		LinkedList<Integer> lList = new LinkedList<>();
		//gList for the value greater than the target value.
		LinkedList<Integer> gList= new LinkedList<>();
		
		// ckecking if the value with the attribute values and adding that to the suitable list	
		for (int k = 0; k < this.map.length; k++) {

			String attributeValue = this.getValueAt(k, attributeIndex);
			int atvalueInt = Integer.parseInt(attributeValue);

			if ( atvalueInt <= valueInt) {

				lList.add(map[k]);
			}
			else{  

				gList.add(map[k]); 
			}
			
		}

		//converting the list into object arrany and that to integer array.
		Object[] partition1 = lList.toArray();
		int[] attVal = new int[lList.size()];

		Object[] partition2 = gList.toArray();
		int[] attVal2 = new int[gList.size()];

		int rows[] = new int[uniqueValues.length];

		//assinging the index of the partitions and 
		//calling the virtial constructor according to the condition 
		for(int g = 0; g < uniqueValues.length; g++){

			uniqueValuesInt[g] = Integer.parseInt(uniqueValues[g]); 

			if ( uniqueValuesInt[g] <= valueInt ){
				for (int l = 0; l < partition1.length; l++){
					attVal[l] = (int) partition1[l];
		 		    rows= attVal;
				}
				partitions[0] = new VirtualDataSet(this.source, rows, attributesParted);
				
			}
			else {		   
				for (int m = 0; m < partition2.length; m++){
					attVal2[m] = (int) partition2[m];
				    rows = attVal2;
			    }
				partitions[1] = new VirtualDataSet(this.source, rows, attributesParted);
				
		    }
		}
		return partitions;
	}		

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		System.out.println("============================================");
		System.out.println("THE WEATHER-NOMINAL DATASET:");
		System.out.println();

		ActualDataSet figure5Actual = new ActualDataSet(new CSVReader("weather-nominal.csv"));

		System.out.println(figure5Actual);

		VirtualDataSet figure5Virtual = figure5Actual.toVirtual();

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 5:");
		System.out.println();

		VirtualDataSet[] figure5Partitions = figure5Virtual
				.partitionByNominallAttribute(figure5Virtual.getAttributeIndex("outlook"));

		for (int i = 0; i < figure5Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure5Partitions[i]);

		System.out.println("============================================");
		System.out.println("THE WEATHER-NUMERIC DATASET:");
		System.out.println();

		ActualDataSet figure9Actual = new ActualDataSet(new CSVReader("weather-numeric.csv"));

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

	}
}
