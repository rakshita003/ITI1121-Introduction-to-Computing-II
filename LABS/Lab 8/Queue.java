
public interface Queue<E> {

    public abstract void enqueue( E obj );
    public abstract E dequeue();
    public abstract boolean isEmpty();
    public abstract int size();

}
