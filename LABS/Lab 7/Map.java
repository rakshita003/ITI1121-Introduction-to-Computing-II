public interface Map< K, V> {
    public V get(K key);
    public boolean contains(K key);
    public void put(K key, V value);
    public void replace(K key, V value);
    public V remove(K key);
}