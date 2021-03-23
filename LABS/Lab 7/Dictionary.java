import java.util.NoSuchElementException;

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
       elems = new Pair[INITIAL_CAPACITY];
       count = 0;

    }


    public void put(String key, Integer value) {
        if(key == null || value == null){
            throw new NullPointerException();
        }
        if(count == elems.length){
            increaseCapacity();
        }
        elems[count] = new Pair(key,value);
        count++;

    }

    private void increaseCapacity() {
        Pair[] newarr;
        newarr = new Pair[elems.length+INCREMENT];
        for(int i =0;i<elems.length;i++){
            newarr[i] = elems[i];
        }
        elems = newarr;
    }



    public boolean contains(String key) {
        if(key == null){
            throw new NullPointerException();
        }
        boolean present = false;
        int index = count - 1;
        while (!present && index >= 0) {
            if (elems[index].getKey().equals(key)) {
                present = true;
            } else {
                index = index - 1;
            }
        }
        return present;
    }


    public Integer get(String key) {
        if(key == null){
            throw new NullPointerException();
        }

        int index = count - 1;
        Integer value = 0;
        while (index >= 0) {
            if (elems[index].getKey().equals(key)) {
                value = elems[index].getValue();
                break;
            }
            index--;
            }
        if(index == -1){
            throw new NoSuchElementException();
        }

        return value;
    }

    public void replace(String key, Integer value) {
        if(key == null || value == null){
            throw new NullPointerException();
        }
        boolean present = false;
        int index = count - 1;
        int pos = 0;
        while (!present && index >= 0) {
            if (elems[index].getKey().equals(key)) {
                pos = index;
                present = true;
                break;
            }
            index = index - 1;
            }
        if(present){
            value = elems[pos].getValue();
            elems[pos].setValue(value);
        }
        if(!present){
        throw new NoSuchElementException();
        }
    }



    public Integer remove(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        boolean present = false;
        int pos = 0;
        int index = count - 1;
        Integer value = 0;
        while (!present && index >= 0) {
            if (elems[index].getKey().equals(key)) {
                pos = index;
                count--;
                present = true;
                break;
            }
            index = index - 1;
        }
        if (present) {
            value = elems[pos].getValue();
            elems[pos] = null;
            while (pos < elems.length - 1) {
                elems[pos] = elems[pos + 1];
                pos++;
            }
            elems[elems.length - 1] = null;
        }
        if (!present) {
            throw new NoSuchElementException();

        }
        return value;
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