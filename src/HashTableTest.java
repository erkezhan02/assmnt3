import java.util.Random;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random random = new Random();

        // Add 10000 random elements to the hash table
        for (int i = 0; i < 10000; i++) {
            int randomNumber = random.nextInt(1000); // Generate a random number
            MyTestingClass key = new MyTestingClass(randomNumber);
            table.put(key, randomNumber);
        }

        // Print the number of elements in each bucket
        for (int i = 0; i < table.chainArray.length; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, Integer> currentNode = table.chainArray[i];
            while (currentNode != null) {
                count++;
                currentNode = currentNode.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}