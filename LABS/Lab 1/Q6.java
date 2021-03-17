import java.util.*;
public class Q6{
  public static void main (String[]args)
  {

    //your code here
    Scanner scanner = new Scanner (System.in);
    double avg, median;
    int passed, failed;
    int i;
    int n;
    System.out.println("Enter the value of n: ");
    n = scanner.nextInt();
    double[] marks = new double[n];
    System.out.println ("Enter the Marks of 10 Students (out of 100):");
    i = 0;
    while (i < n) {
	    marks[i] = scanner.nextDouble();
	    i++;
    }
    
    avg = calculateAverage(marks);
    median = calculateMedian(marks);
    failed = calculateNumberFailed(marks);
    passed = calculateNumberPassed(marks);
    System.out.println ("The average is: " + avg + "\nThe median is: " +
			median + "\nThe Number of Failed Student(s) is: " +
			failed + "\nThe Number of Passed Student(s) is: " +
			passed);
  }

  public static double calculateAverage (double []notes)
  {
    //your code here
    int n = notes.length;
    double average = 0.0;
    for (int i = 0; i < n; i++) {
      average += notes[i];
    }
    average /= n;
    return average;
  }

  public static double calculateMedian (double []notes) {
    //your code here
    double m = 0;
    int n = notes.length;
    
    Arrays.sort(notes);
   
    // check for even case
    if (n % 2 != 0)
        return (double)notes[n / 2];
 
    return (double)(notes[(n - 1) / 2] + notes[n / 2]) / 2.0;

  }

  public static int calculateNumberFailed (double[]notes)
  {
    //your code here
    int fail = 0;
    int n = notes.length;
    for (int i = 0; i < n; i++) {
	    if (notes[i] < 50.0)
	        fail++;
    }
    return fail;
  }

  public static int calculateNumberPassed (double[]notes)
  {
    //your code here
    int pass = 0;
    int n = notes.length;
    for (int i = 0; i < n; i++) {
	if (notes[i] >= 50.0)
	  pass++;
    }
    return pass;
  }
}