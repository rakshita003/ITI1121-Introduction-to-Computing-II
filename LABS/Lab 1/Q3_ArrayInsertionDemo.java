public class Q3_ArrayInsertionDemo{

	public static int[] insertIntoArray(int[] beforeArray, int indexToInsert, int valueToInsert){
		int n= (beforeArray.length) +1;
		int afterarr[]= new int[n];
		for (int i=0; i<n;i++){
			if(i<=indexToInsert-1)
				afterarr[i]=beforeArray[i];
			else if (i== indexToInsert)
			    afterarr[i]=valueToInsert;
			    else
			        afterarr[i]= beforeArray[i-1]; 
		}
		return afterarr;
	}

	public static void main(String[] args){
		// Your code here
		int beforeArray[]={1,5,4,7,9,6};
		System.out.println("Array before insertion:");
		for(int k=0;k<beforeArray.length ;k++ )
		   System.out.println( beforeArray[k] );
		int valueToInsert =15;
		int indexToInsert= 3;
		beforeArray= insertIntoArray(beforeArray,indexToInsert,valueToInsert);
		System.out.println("Array after insertion of " +valueToInsert+ " at position " +indexToInsert +":");
		for(int j=0;j<beforeArray.length ;j++ )
			System.out.println(beforeArray[j] );
	}
}