import java.util.Objects;

public class MyHashTable<K, V> {
    private static final int DEFAULT_SIZE = 11;

    protected static class HashNode<K, V> {
        protected K key;
        protected V value;
        protected HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    protected HashNode<K, V>[] chainArray;
    private int size;

    public MyHashTable() {
        this(DEFAULT_SIZE);
    }

    public MyHashTable(int M) {
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    // Calculates the index for the given key using hash code modulo array length.
    private int hash(K key) {
        return Objects.hashCode(key) % chainArray.length;
    }

    // Inserts or updates a key-value pair in the hash table.
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> currentNode = chainArray[index];
            while (currentNode.next != null && !currentNode.key.equals(key)) {
                currentNode = currentNode.next;
            }
            if (currentNode.key.equals(key)) {
                currentNode.value = value; // Updates the value if the key already exists
            } else {
                currentNode.next = newNode; // Adds a new node if the key doesn't exist yet
            }
        }
        size++;
    }

    // Retrieves the value associated with the given key from the hash table.
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = chainArray[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null; // Key not found
    }

    // Removes the key-value pair associated with the given key from the hash table.
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = chainArray[index];
        HashNode<K, V> prevNode = null;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prevNode != null) {
                    prevNode.next = currentNode.next;
                } else {
                    chainArray[index] = currentNode.next;
                }
                size--;
                return currentNode.value;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return null; // Key not found
    }

    // Checks if the hash table contains the given value.
    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (Objects.equals(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    // Retrieves the key associated with the given value from the hash table.
    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (Objects.equals(node.value, value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }
}
