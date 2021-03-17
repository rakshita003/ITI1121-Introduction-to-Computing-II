public class Dictionary implements Map<String, Integer> {

    private final static int INITIAL_CAPACITY = 10;
    private final static int INCREMENT = 5;
    private int count;

    private Pair[] elems;

    public int getCount() {
      return count;
    }

    public int getCapacity() {
      return elems.length;
    }

    public Dictionary() {
        /* Your code here */
        elems =  new Pair[INITIAL_CAPACITY];
        count = 0;
    }

    @Override
    public void put(String key, Integer value) {
        /* Your code here */
        if(count == elems.length){
            increaseCapacity();
        }
        elems[count] = new Pair(key, value);
        count++;
    }

    private void increaseCapacity() {
        /* Your code here.  Use this in put() where necessary. */
        Pair[] p;
        p = new Pair[elems.length + INCREMENT];
        for( int i = 0; i < elems.length; i++){
            p[i] = elems[i];
        }
        elems = p;
    }

    @Override
    public boolean contains(String key) {
        /* Your code here. */
        boolean found = false;
        int pos = count -1;
        while (pos >= 0 && ! found){
            if( elems[pos].getKey().equals(key)){
                found = true;
            }
            else {
                pos--;
            }
        }
        return found;
    }

    @Override
    public Integer get(String key) {
        /* Your code here. */
        boolean found = false;
        int pos = count-1;
        while ( pos >= 0 && !found) {
            if ( elems[pos].getKey().equals(key)){
                found = true;
            }
            else{
                pos--;
            }
        }
        return elems[pos].getValue();
    }

    @Override
    public void replace(String key, Integer value) {
        /* Your code here. */
        boolean found = false;
        int pos = count-1;
        while ( pos >= 0 && !found) {
            if( elems[pos].getKey().equals(key) ){
                found = true;
            }
            else {
                pos--;
            }
        }
        elems[pos].setValue(value);
    }

    @Override
    public Integer remove(String key) {
        /* Your code here. */
        boolean found = false; 
        int pos = count-1;
        while (pos >= 0 && !found){
            if (elems[pos].getKey().equals(key)){
                found = true;
            }
            else{
                pos--;
            }
        }
        Integer s = elems[pos].getValue();
        while (pos < count-1) {
            elems[pos] = elems[pos + 1];
            pos++;
        }
        count--;
        elems[count] = null;
        return s; 

    
    }

    @Override
    public String toString() {
      String res;
      res = "Dictionary: {elems = [";
      for (int i = count-1; i >= 0 ; i--) {
          res += elems[i];
          if(i > 0) {
              res += ", ";
          }
      }
      return res +"]}";
    }

}