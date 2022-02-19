package datastructures.concrete.dictionaries;

import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

/**
 * See IDictionary for more details on what this class should do
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;
    private int size;
    private int keyIndex;

    // You're encouraged to add extra fields (and helper methods) though!
    public ArrayDictionary() {
        pairs = makeArrayOfPairs(10);
        size = 0;
        keyIndex = -1;
    }
    
    private Pair<K, V>[] extendDictionary(Pair<K, V>[] old){
        Pair<K, V>[] output;
        
        if (old.length < 100) {
            output = makeArrayOfPairs(old.length*2);
        } else {
            output = makeArrayOfPairs(old.length + 100);
        }
        
        for (int i = 0; i < old.length; i++) {
            output[i] = old[i];
        }
        
        return output;
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain Pair<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);

    }

    @Override
    public V get(K key) {
        if (!this.containsKey(key)) {
            throw new NoSuchKeyException();
        }
        return pairs[keyIndex].value;
    }

    @Override
    public void put(K key, V value) {
        if (size >= pairs.length) {
            pairs = extendDictionary(pairs);
        }
        
        if (this.containsKey(key)) {
            pairs[keyIndex].value = value;
        } else {
            pairs[size] = new Pair<K, V>(key, value);
            size++;            
        }
    }

    @Override
    public V remove(K key) {
    	if (!this.containsKey(key)) {
    		throw new NoSuchKeyException();
    	}
        V valuePair = pairs[keyIndex].value;
        pairs[keyIndex] = pairs[size - 1];
        pairs[size -1] = null;
        size--;
        return valuePair;  
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
        	if (pairs[i].key == key || pairs[i].key.equals(key)) {
        		keyIndex = i;
        		return true;
        	}
        }
        keyIndex = -1;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}

