
public class ArrayQueue<E> implements Queue<E> {

    private static final int MAX_QUEUE_SIZE = 10000;

    private E[] elems;
    
    private int front, rear, size;

    @SuppressWarnings( "unchecked" )

    public ArrayQueue() {
        elems = (E []) new Object[MAX_QUEUE_SIZE];
        front =  0;
        rear = -1;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_QUEUE_SIZE;
    }
    
    public void enqueue( E elem ) {

         if ( rear == ( MAX_QUEUE_SIZE -1 ) ) {
            
            int j=0;
            for ( int i=front; i<=rear; i++ ) {
                elems[ j++ ] = elems[ i ];
            }

            front = 0;
            rear = size - 1;

        }

        elems[ ++rear ] = elem;
        size++;
    }

    public E dequeue() {


        E saved = elems[ front ];
        elems[ front ] = null; 

        front++;
        size--;

        return saved;
    }

}
