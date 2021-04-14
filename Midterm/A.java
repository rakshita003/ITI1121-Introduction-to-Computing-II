/**
 * COPYRIGHTED MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Guy-Vincent Jourdan
 * @author Mehrdad Sabetzadeh 
 */


public class A implements DeepCopyable { // complete the class declaration as required
	
	private int a , b, c;

	public A(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean equals(Object o){
		if(o == null) {
            return false;
        }

        if(getClass() != o.getClass()) {
            return false;
        }

        A other = (A)o;
		
		return this.a == other.a && this.b == other.b && this.c == other.c;
	}

	public DeepCopyable deepCopy(){
		return new A(a,b,c);
		
	}
	


}
