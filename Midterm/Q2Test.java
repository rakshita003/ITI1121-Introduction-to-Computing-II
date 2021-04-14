/**
 * COPYRIGHTED MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Guy-Vincent Jourdan
 * @author Mehrdad Sabetzadeh 
 */

public class Q2Test {

	public static void main(String[] args) {

		UniquifiableArrayStack<Integer> integerStack = new UniquifiableArrayStack<Integer>();
		integerStack.push(0);
		integerStack.push(1);
		integerStack.push(1);
		integerStack.push(1);
		integerStack.push(2);
		integerStack.push(2);
		integerStack.push(3);
		integerStack.push(3);
		integerStack.push(3);
		integerStack.push(4);

		System.out.println("Original Integer stack: " + integerStack);
		System.out.println("Integer stack without immediately consecutive duplicates: " + integerStack.uniquify());
		System.out.println("Original Integer stack (after uniquify): " + integerStack);

		System.out.println();

		UniquifiableArrayStack<String> stringStack = new UniquifiableArrayStack<String>();
		stringStack.push("a");
		stringStack.push("a");
		stringStack.push("b");
		stringStack.push("b");
		stringStack.push("b");
		stringStack.push("c");
		stringStack.push("d");
		stringStack.push("d");
		stringStack.push("d");
		stringStack.push("e");
		stringStack.push("e");

		System.out.println("Original String stack: " + stringStack);		
		System.out.println("String stack without immediately consecutive duplicates: " + stringStack.uniquify());
		System.out.println("Original String stack (after uniquify): " + stringStack);
		
		System.out.println();
		System.out.println("---- Now, testing with some non-consecutive duplicates ----- ");
		System.out.println();

		
		UniquifiableArrayStack<String> stringStackWithNonConsecutiveDuplicates = new UniquifiableArrayStack<String>();
		stringStackWithNonConsecutiveDuplicates.push("a");
		stringStackWithNonConsecutiveDuplicates.push("b");
		stringStackWithNonConsecutiveDuplicates.push("b");
		stringStackWithNonConsecutiveDuplicates.push("c");
		stringStackWithNonConsecutiveDuplicates.push("a");
		stringStackWithNonConsecutiveDuplicates.push("d");
		stringStackWithNonConsecutiveDuplicates.push("d");
		stringStackWithNonConsecutiveDuplicates.push("e");
		stringStackWithNonConsecutiveDuplicates.push("e");
		stringStackWithNonConsecutiveDuplicates.push("d");
		stringStackWithNonConsecutiveDuplicates.push("d");
		stringStackWithNonConsecutiveDuplicates.push("d");
		stringStackWithNonConsecutiveDuplicates.push("d");
		stringStackWithNonConsecutiveDuplicates.push("b");		
		
		System.out.println("Original String stack: " + stringStackWithNonConsecutiveDuplicates);		
		System.out.println("String stack without immediately consecutive duplicates: " +
		  stringStackWithNonConsecutiveDuplicates.uniquify());
		System.out.println("Original String stack (after uniquify): " + stringStackWithNonConsecutiveDuplicates);

		
	}
}
