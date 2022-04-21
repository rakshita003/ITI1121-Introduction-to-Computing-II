public interface Queue {
    boolean isEmpty();
    void enqueue(Object newElement);
    Object dequeue();
}

