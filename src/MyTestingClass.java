public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        // A simple example of a custom hashCode implementation
        return id % 10; // Modulo 10 to limit the hash value to a range of 0-9
    }

    @Override
    public String toString() {
        return "MyTestingClass{" +
                "id=" + id +
                '}';
    }
}