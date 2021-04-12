
/**
 * This class enables the construction of a decision tree
 * 
 * @author Rakshita Mathur, University of Ottawa
 *
 */

public class DecisionTree {

	private static class Node<E> {
		E data;
		Node<E>[] children;

		Node(E data) {
			this.data = data;
		}
	}

	Node<VirtualDataSet> root;

	/**
	 * @param data is the training set (instance of ActualDataSet) over which a
	 *             decision tree is to be built
	 */
	public DecisionTree(ActualDataSet data) {
		if (data == null) {
			throw new IllegalArgumentException("data cannot be null");
		}
		root = new Node<VirtualDataSet>(data.toVirtual());
		build(root);
	}

	/**
	 * The recursive tree building function
	 * 
	 * @param node is the tree node for which a (sub)tree is to be built
	 */
	@SuppressWarnings("unchecked")
	private void build(Node<VirtualDataSet> node) {

		// 1) Edge Cases check
		if (node == null) {
			throw new IllegalArgumentException("node cannot be null");
			//return;
		}
		if (node.data == null) {
			throw new NullPointerException("Can't have a Node reference to null data");
		}
		if ( node.data.getNumberOfAttributes() < 1 ) {
			throw new NullPointerException("data must have at least one attribute");
		}
		if ( node.data.getNumberOfDatapoints() < 1 ) {
			throw new NullPointerException("data must have at least one datapoint");
		}
		
		// 2) Base Cases Check
		//2.1
		int numberOfAttributes = node.data.getNumberOfAttributes();
		
		if(numberOfAttributes == 1){
			//attribute is "class" attribute WE CAN'T SPLIT further
			return;
		}

		//2.2
		if ( node.data.getUniqueAttributeValues(numberOfAttributes - 1).length < 1 ){
			throw new NullPointerException("Must have at least one class attribute value");
		}
		if ( node.data.getUniqueAttributeValues(numberOfAttributes - 1).length == 1 ){
			// only yes or only no
			// No uncertaniny don't have to split any further
			return;
		}

		//2.3
		int count = 0;
		int i;

		// find out how many attributes have only one unique value
		for ( i = 0;  i < numberOfAttributes-1; i++ ){
			if( node.data.getUniqueAttributeValues(i).length == 1 )
				count++;
		}
		// if unique values == number of attributes-1
		if ( count == i ){
			// there is nothing left to split.
			return;
		}

		// 3) Recursive Cases

		// a_max = best information gain
		
		GainInfoItem a_max = InformationGainCalculator.calculateAndSortInformationGains(node.data)[0];

		if (a_max.getGainValue() == 0.0) // No split when there is no gain
            return;

		VirtualDataSet[] partitions; 

		if ( a_max.getAttributeType() == AttributeType.NOMINAL){
			// string
			//  partitions = partition by nominal
			int attributeIndex  = node.data.getAttributeIndex(a_max.getAttributeName()) ;

			partitions = node.data.partitionByNominallAttribute(attributeIndex); 
			
		} 
		else {
			// number
			// partitions =  call partition by numeric
			int attributeIndex  = node.data.getAttributeIndex(a_max.getAttributeName()) ;
		
			String[] values = node.data.getAttribute(attributeIndex).getValues();

			int indexForValue = -1;

			for (int p = 0; p < values.length; p++) {
				if (values[p].equals(a_max.getSplitAt())) {
					indexForValue = p;
					break;
				}
			}

			partitions = node.data.partitionByNumericAttribute(attributeIndex, indexForValue);
			
		}

		node.children = (Node<VirtualDataSet>[]) new Node[partitions.length];

		for ( int p = 0; p < partitions.length; p++ ){
			node.children[p] = new Node<VirtualDataSet>(partitions[p]);
		}
		
		for (int j = 0; j < node.children.length; j++){
			build(node.children[j]);
			
		}
		
			
	}

	
	@Override
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * The recursive toString function
	 * 
	 * @param node        is the tree node for which an if-else representation is to
	 *                    be derived
	 * @param indentDepth is the number of indenting spaces to be added to the
	 *                    representation
	 * @return an if-else representation of node
	 */
	private String toString(Node<VirtualDataSet> node, int indentDepth) {

		if(node == null) {
			throw new IllegalArgumentException("Can't have node as null");
		}

		if (node.children == null){
			return "";
		}

		for ( int i = 0; i < node.children.length; i++){

			DecisionTree.Node<VirtualDataSet> child = node.children[i];
			String conditions = child.data.getCondition();

			if ( child != null){

				if ( i == 0 ){
					System.out.println(createIndent(indentDepth) +  "if ("+conditions+") {");
					
					if ( child.children == null) {
						//choosing the first unique attribute value for class if there is more than 1 : child.data.getUniqueAttributeValues(classIndex)[0]
						int classIndex = child.data.getNumberOfAttributes() - 1;
						System.out.println(createIndent(indentDepth+ 2) + child.data.getAttribute(classIndex).getName() +
								   " = " + child.data.getUniqueAttributeValues(classIndex)[0]); 
					}
				
				}
				else {
					System.out.println(createIndent(indentDepth) +  "else if ("+conditions+") {");

					if ( child.children == null) {
						//choosing the first unique attribute value for class if there is more than 1 : child.data.getUniqueAttributeValues(classIndex)[0]
						int classIndex = child.data.getNumberOfAttributes() - 1;
						System.out.println(createIndent(indentDepth+ 2) + child.data.getAttribute(classIndex).getName() +
								   " = "+ child.data.getUniqueAttributeValues(classIndex)[0]);	
					}
				}
			
				toString( child, indentDepth + 4);

				if( indentDepth == 0 || child != null ){
					System.out.println( createIndent(indentDepth) + "}");
				}
			}		
		}
		return "";
	}

	/**
	 * @param indentDepth is the depth of the indentation
	 * @return a string containing indentDepth spaces; the returned string (composed
	 *         of only spaces) will be used as a prefix by the recursive toString
	 *         method
	 */
	private static String createIndent(int indentDepth) {
		if (indentDepth <= 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < indentDepth; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
	
		//StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java DecisionTree <file name>");
			return;
		}

		String strFilename = args[0];

		ActualDataSet data = new ActualDataSet(new CSVReader(strFilename));

		DecisionTree dtree = new DecisionTree(data);

		System.out.println(dtree);
	}
}
