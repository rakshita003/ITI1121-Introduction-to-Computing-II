public class Q2{
	public static void main( String[] args) {
	 for(int i=1; i<=30; i++){
	 if(i%15==0)
	  System.out.print( i+" FizzBuzz\n");
	  else if (i% 3 == 0)
	    System.out.print( i+" Fizz\n");
	    else if (i% 5 == 0)
	    System.out.print(i+" Buzz\n");  
	 }
	}
}