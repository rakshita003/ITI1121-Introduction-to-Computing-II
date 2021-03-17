public interface Map< K, V> {

    /* Make the necessary abstract method definitions */
    public abstract V get(K key);
    public abstract boolean contains (K key);
    public abstract void put (K key , V value);
    public abstract void replace( K key, V values);
    public abstract V remove(K key);
    

}