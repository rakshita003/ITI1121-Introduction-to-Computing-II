public class DynamicArrayStack<E> implements Stack<E> {

    // Instance variables

    private E[] elems;  // Used to store the elements of this ArrayStack
    private int top;    // Designates the first free cell
    private static final int DEFAULT_INC = 25;   //Used to store default increment / decrement

    @SuppressWarnings( "unchecked" )

    // Constructor
    public DynamicArrayStack( int capacity ) {
        // Your code here.
        top = 0;
        if( capacity > DEFAULT_INC ) {
            elems = (E[]) new Object [ capacity ];
        }
        else{
            elems = (E[]) new Object [ DEFAULT_INC ];
        }
    }

    // Gets current capacity of the array
    public int getCapacity() {
        return elems.length;
    }

    // Returns true if this DynamicArrayStack is empty
    public boolean isEmpty() {
        return ( top == 0 );
    }

    // Returns the top element of this ArrayStack without removing it
    public E peek() {
        return elems[ top-1 ];
    }

    @SuppressWarnings( "unchecked" )

    // Removes and returns the top element of this stack
    public E pop() {
        // Your code here.
        E saved = elems[ -- top ];
        elems[ top ] = null;
        if((elems.length - ( top+1 )) == DEFAULT_INC){
            E[] tempArray = (E[]) new Object[elems.length - DEFAULT_INC];
            for( int j = 0; j < top; j++){
                tempArray[j] = elems[j];
            }
            elems = tempArray;
        }
        return saved;
    }

    @SuppressWarnings( "unchecked" )

    // Puts the element onto the top of this stack.
    public void push( E element ) {
        // Your code here.
        elems[top++] = element;
        if (top == elems.length){
            E[] tempArray = (E[]) new Object[elems.length + DEFAULT_INC];
            for (int i = 0; i < top; i++){
                tempArray[i] =elems[i];
            }
            elems = tempArray;
        }
    }

    @SuppressWarnings( "unchecked" )

    public void clear() {
        // Your code here.
        elems = (E[]) new Object[ DEFAULT_INC ];
        top = 0;
    }

}