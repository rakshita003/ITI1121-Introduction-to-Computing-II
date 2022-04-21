public class Q1Test {

	public static void testFirstConstructor(){
		LinkedDoublySkip<Integer> list1 = new LinkedDoublySkip<Integer>();
		LinkedDoublySkip<Integer> list2 = new LinkedDoublySkip<Integer>();

		list1.add(1); list1.add(2); list1.add(3); list1.add(4); 
		list2.add(1); list2.add(2); list2.add(3); list2.add(4); list2.add(5);
		System.out.println("-- first set of test cases: using the first constructor --");
		System.out.print("List 1 with toString		: ");
		System.out.println(list1);
		System.out.print("List 2 with toString		: ");
		System.out.println(list2);

		System.out.print("List 1 with getElementAt	: ");
		for(int i=0; i<list1.size();i++){
			System.out.print(list1.getElementAt(i));
			if(i!=(list1.size()-1))System.out.print(", ");
		}
		System.out.println();
		System.out.print("List 2 with getElementAt	: ");
		for(int i=0; i<list2.size();i++){
			System.out.print(list2.getElementAt(i));
			if(i!=(list2.size()-1))System.out.print(", ");
		}
		System.out.println();

		Iterator<Integer> iIterator = list1.iterator();
		StringBuffer iBuffer = new StringBuffer();
		System.out.print("List 1 with iIterator		: "+iBuffer);
		while (iIterator.hasNext()) {
			iBuffer.append(iIterator.next());
			if (iIterator.hasNext())
				iBuffer.append(", ");
		}
		System.out.println(iBuffer);
		iIterator = list2.iterator();
		iBuffer = new StringBuffer();
		System.out.print("List 2 with iIterator		: "+iBuffer);
		while (iIterator.hasNext()) {
			iBuffer.append(iIterator.next());
			if (iIterator.hasNext())
				iBuffer.append(", ");
		}
		System.out.println(iBuffer);
	}
	public static void testSecondConstructor(){

		Integer[] integerArray1 = { 5,2,7,3,1,9 };
		Integer[] integerArray2 = { 5,2,7,3,1,9,10 }; 

		LinkedDoublySkip<Integer> list1 = new LinkedDoublySkip<Integer>(integerArray1);
		LinkedDoublySkip<Integer> list2 = new LinkedDoublySkip<Integer>(integerArray2);

		System.out.println("-- second set of test cases: using the second constructor --");
		System.out.print("List 1 with toString		: ");
		System.out.println(list1);
		System.out.print("List 2 with toString		: ");
		System.out.println(list2);

		System.out.print("List 1 with getElementAt	: ");
		for(int i=0; i<list1.size();i++){
			System.out.print(list1.getElementAt(i));
			if(i!=(list1.size()-1))System.out.print(", ");
		}
		System.out.println();
		System.out.print("List 2 with getElementAt	: ");
		for(int i=0; i<list2.size();i++){
			System.out.print(list2.getElementAt(i));
			if(i!=(list2.size()-1))System.out.print(", ");
		}
		System.out.println();

		Iterator<Integer> iIterator = list1.iterator();
		StringBuffer iBuffer = new StringBuffer();
		System.out.print("List 1 with iIterator		: "+iBuffer);
		while (iIterator.hasNext()) {
			iBuffer.append(iIterator.next());
			if (iIterator.hasNext())
				iBuffer.append(", ");
		}
		System.out.println(iBuffer);
		iIterator = list2.iterator();
		iBuffer = new StringBuffer();
		System.out.print("List 2 with iIterator		: "+iBuffer);
		while (iIterator.hasNext()) {
			iBuffer.append(iIterator.next());
			if (iIterator.hasNext())
				iBuffer.append(", ");
		}
		System.out.println(iBuffer);
	}

	public static void main(String[] args) {
		testFirstConstructor();
		System.out.println();
		testSecondConstructor();
	}
}
