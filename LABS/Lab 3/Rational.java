public class Rational {

    private int numerator;
    private int denominator;

    // constructors

    public Rational(int numerator) {
	  this.denominator=1;
    this.numerator = numerator;

    }

    public Rational(int numerator, int denominator) {
	     // Your code here
    if (denominator == 0)
       denominator = 1;

    if (denominator< 0)
    {
       numerator = numerator * -1;
       denominator = denominator * -1;
    }

    this.numerator = numerator;
    this.denominator = denominator;

    reduce();
    }

    // getters

    public int getNumerator() {
	     return numerator;
    }

    public int getDenominator() {
	     return denominator;
    }

    // instance methods

    public Rational plus(Rational other) {
	     
       

        int x = gcd(this.numerator, other.numerator);
        int y = gcd(this.denominator, other.denominator);

        int deno= this.denominator * (other.denominator/gcd(this.denominator,other.denominator));
        int num=(this.numerator / x) * (other.denominator / y) + (other.numerator / x) * (this.denominator / y);
        Rational z = new Rational(num,deno);

        z.numerator *= x;
        return z;

    }

    public static Rational plus(Rational a, Rational b) {
    	// Your code here
      
      int newNumerator = a.numerator * b.denominator + a.denominator * b.numerator;
      int newDenominator = a.denominator * b.denominator;
      Rational newRational = new Rational(newNumerator, newDenominator);

  return newRational;
    

    }

    

    private void reduce() {
     
      if (numerator != 0) {
        int commonFactor = gcd(Math.abs(numerator), denominator);

        this.numerator = numerator / commonFactor;
        this.denominator = denominator / commonFactor;
      }

    }

    // Euclid's algorithm for calculating the greatest common divisor
    private int gcd(int a, int b) {
    
      if (a < 0) 
        a = -a;
      if (b < 0) 
        b = -b;
      if (0 == a) 
        return a;
      else{	while (a != b)
    	    if (a > b)
    		     a = a - b;
    	    else
    		     b = b - a;
    	return a; }
      
    }

    public int compareTo(Rational other) {
      
        int left = this.getNumerator() * other.getDenominator();
        int right = this.getDenominator() * other.getNumerator();
        if (left < right) 
            return -1;
        if (left > right) 
           return +1;

        return 0;
    }
     

    public boolean equals(Rational other) {
      
    
      if (other == null) 
           return false;
       
       
            return compareTo(other) == 0;
     
     
     
    }

    public String toString() {
    	String result;
    
      if (numerator == 0)
         result = "0";
      else
         if (denominator == 1)
            result = numerator + "";
         else
            result = numerator + "/" + denominator;
    
      return result;
    }

    public Rational() {
    }

}