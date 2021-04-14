/**
 * COPYRIGHTED MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Guy-Vincent Jourdan
 * @author Mehrdad Sabetzadeh 
 */

public class TestDeepCopy {
	
	public static void main(String[] args){

		B b1 = new B(new A(1,2,3), new A(4,5,6));
		B b2 = new B(new A(7,8,9), new A(10,11,12));

		System.out.println(b1.equals(b2)); // false
		System.out.println(b1.equals(b1.deepCopy())); // true
		System.out.println(b1==b1.deepCopy()); // false
		System.out.println(b1.getA1().equals(((B)(b1.deepCopy())).getA1())); //true
		System.out.println(b1.getA1()==(((B)(b1.deepCopy())).getA1())); //false

	}
}