import java.io.*;
import java.util.Scanner;

public class DataSet{
    //private static final char DELIMITER = ',';
    //private static final char QUOTE_MARK = '\'';
    private int numColumns;
    private int numRows;
    private String[] attributeNames;
    private String[][] matrix;

    public DataSet(String strFilename) throws Exception {
        
        this.calculateDimensions(strFilename);
        this.instantiateFromFile(strFilename);
    }

   
    public String getAttributeName(int column) {
       
        if (column >= this.numColumns) {
            return null;
        }

        return this.attributeNames[column];
       
    }

  
    public String getAttributeValue(int row, int column) {
        
        if (row >= this.numRows || column >= this.numColumns) {
            return null;
        }

        return this.matrix[row][column];

    }

    public int getNumberOfAttributes() {
        return numColumns;
    }

    public int getNumberOfDatapoints() {
        return numRows;
    }

    public String[] getUniqueAttributeValues(String attributeName) {

        int attributeIndex = 0;
        for (int i = 0; i < this.attributeNames.length; i++) {
            
            if (((this.attributeNames[i]).trim()).equalsIgnoreCase(attributeName)) {
                attributeIndex = i;
                break;
            }     
        }
        
        String[] uniqueValues = getUniqueAttributeValues(attributeIndex);
     
        return uniqueValues;
    
    }

    private String[] getUniqueAttributeValues(int column) {
        
        String[] tempArr = new String[this.numRows];    
        String[] uniqueArr = new String[this.numRows];  //to stores only the unique values
        
        //Changing null to an empty string and assigning that to the tempArr
        for (int index = 0; index < this.numRows; index++) {
            if (this.matrix[index][column] == null || this.matrix[index][column].length() == 0){
                tempArr[index] = "";
            } else {
                tempArr[index] = this.matrix[index][column];
            }
        }
        int count = 0;  //counting number of unique elements 
         // Picking all elements one by one 
        for (int rowIndex = 0; rowIndex < this.numRows; rowIndex++) {
            // Checking if the picked element is already stored 
            int columnIndex;
            for (columnIndex = 0; columnIndex < rowIndex; columnIndex++) {
                
                if (tempArr[columnIndex] == null || tempArr[rowIndex] == null) continue;
                String s1 = new String(tempArr[rowIndex]);
                String s2 = new String(tempArr[columnIndex]);
                if (s1.equals(s2))
                    break;
            }
           // If not stored earlier, then store it AND check if it's not null
            if (rowIndex == columnIndex && tempArr[rowIndex] != null) {
              
                uniqueArr[count++] = tempArr[rowIndex];
               
            }
        }
       
        String[] newUniqueArr = new String[count];
       
        for (int i =0; i < count; i++) { 
            newUniqueArr[i] = uniqueArr[i];
            if(newUniqueArr[i]=="\'"){
            newUniqueArr[i]= newUniqueArr[i].replace("\'","");
         }
        
    }
    return newUniqueArr;
    
    }

    public String metadataToString() {


        String separator = System.getProperty("line.separator");
        StringBuffer metaData = new StringBuffer();
    

        for (int i=0; i<this.numColumns; i++) {
            if (Util.isArrayNumeric(getUniqueAttributeValues(i)) == true) {
                metaData.append(separator + (i+1) + ") " + getAttributeName(i) + " (numeric): "
                 + Util.numericArrayToString(getUniqueAttributeValues(i)));
            }
            else {
                metaData.append(separator + (i+1) + ") " + getAttributeName(i) +" (nominal): " 
                + Util.nominalArrayToString(getUniqueAttributeValues(i)));
            }
        }
        return ("Number of attributes: " + getNumberOfAttributes() + separator + "Number of datapoints: "
                + getNumberOfDatapoints() + separator + "\n" + "* * * Attribute value sets * * *" + metaData);
    }
    public static void main(String[] args) throws Exception {

        System.out.print("Please enter the name of the CSV file to read: ");

        Scanner scanner = new Scanner(System.in);

        String strFilename = scanner.nextLine();
        
        DataSet dataset = new DataSet(strFilename);

        System.out.print(dataset.metadataToString());

        scanner.close();

    }


    private void calculateDimensions(String strFilename) throws Exception {

        Scanner scanner = new Scanner(new File(strFilename));

        this.numColumns = 0;
        this.numRows = 0;

        if (scanner.hasNextLine()) {
            String[] columns = scanner.nextLine().split(",(?=([^\']|\'[^\']*\')*$)");
            //(",(?=([^\"]|\"[^\"]*\")*$)")
            this.numColumns = columns.length;
           
        }

        BufferedReader reader = new BufferedReader(new FileReader(strFilename));

        while (reader.readLine() != null ) {
            
            this.numRows++;
        
        }

        scanner.close();

        this.numRows = this.numRows - 1;
        
        reader.close();
    
    }

   
    private void instantiateFromFile(String strFilename) throws Exception {

        Scanner scanner = new Scanner(new File(strFilename));
        int countBlankLines = 0;

        while (scanner.hasNextLine()) {
            String header = scanner.nextLine();
            this.attributeNames = header.split(",(?=([^\']|\'[^\']*\')*$)");
            for (int counter=0; counter < this.attributeNames.length; counter++) {
                this.attributeNames[counter] = this.attributeNames[counter].trim().replace("\'", "");
            }
           
            if(header.length() <= (this.attributeNames).length - 1){ 
                this.numRows = this.numRows - 1;
            }
            break;
        }
        
        this.matrix = new String[this.numRows][this.numColumns];
        while (scanner.hasNextLine()) {

            for (int i = 0; i < (this.numRows - countBlankLines); i++) {
                String line = scanner.nextLine();
                String[] lineBuffer = line.split(",(?=([^\']|\'[^\']*\')*$)");
                //linesplit(",(?=([^\"]|\"[^\"]*\")*$)")
                if (line.length() == 0 || (line.trim()).length() <= (this.attributeNames).length-1) {
                    i--;
                   
                    this.numRows = this.numRows-1;
                    continue;
                }
        
                for (int j = 0; j < lineBuffer.length; j++) {
                    this.matrix[i][j] = lineBuffer[j].trim().replace("\'", "");
                    
                }
            }
        }

        scanner.close();
       
    }
}
