import java.util.NoSuchElementException;

public class BitList {

 
    public static final int ZERO = 0;
    public static final int ONE = 1;

    private Node first;

    public BitList() {  
        first = null;
    }

    public BitList( String s ) {
    	BitListIterator b = new BitListIterator();
    	
    	for(int i = s.length()-1; i >= 0; i--) {
    		
    		if(s.charAt(i) == '0') {
    			b.add(ZERO);
    		}
    		else if(s.charAt(i) == '1'){
    			b.add(ONE);
    		}
    		else {
    			b.add(s.charAt(i));
    		}
        }
    }

    public void addFirst( int bit ) {

        if ( ( bit != ZERO ) && ( bit != ONE ) ) {
            throw new IllegalArgumentException( Integer.toString( bit ) );
        }

        first = new Node( bit, first );
    }

    public int removeFirst() {

        if ( first == null ) {
            throw new NoSuchElementException();
        }

        int saved = first.bit;

        first = first.next;

        return saved;
    }

    public Iterator iterator() {

        return new BitListIterator();
    }

    public String toString() {

        String str = "";

        if ( first == null ) {

            str += ZERO;

        } else {

            Node r = first;

            while ( r != null ) {
                str = r.bit + str; 
                r = r.next;
            }

        }
        return str;
    }

    // The implementation of the nodes (static nested class)

    private static class Node {

        private int bit; // <- NEW
        private Node next;

        private Node( int bit, Node next ) { // <- ACCORDINGLY ...
            this.bit = bit;
            this.next  = next;
        }
    }

    // The implementation of the iterators (inner class)

    private class BitListIterator implements Iterator {

        private Node current = null;

        private BitListIterator() {

            current = null;
        }

        public boolean hasNext() {

            return ( ( current == null && first != null ) || ( current != null && current.next != null ) );
        }

        public int next() {

            if ( current == null ) {
                current = first ;
            } 
            else {
                current = current.next ; 
            }

            if ( current == null ) {

                throw new NoSuchElementException() ;
            }

            return current.bit ;
        }

        public void add( int bit ) {

            if ( ( bit != ZERO ) && ( bit != ONE ) ) {

                throw new IllegalArgumentException( Integer.toString( bit ) );
            }

            if ( current == null ) {

                first = new Node( bit, first );

                current = first;

            } else {

                current.next = new Node( bit, current.next );

                current = current.next;
            }
        }
    }
}