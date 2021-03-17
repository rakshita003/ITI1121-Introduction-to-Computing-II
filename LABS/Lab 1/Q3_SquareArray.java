public class Q3_SquareArray{

	public static int[] createArray(int size) {
		int[] SquareArray;
		SquareArray = new int[size];
		for (int i=0;i< SquareArray.length ; i++){
			SquareArray[i] = i*i;

		}
       return SquareArray;
	}

	public static void main(String[] args){
		// Your code here
        int[] arr= createArray(13);
        for(int i=0;i< arr.length ;i++){
        	System.out.println("The square of " + i +" is : "+arr[i] );
        }
	}
}