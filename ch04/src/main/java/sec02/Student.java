package sec02;

public class Student extends Person implements Named {
    private final int id;

    public Student(String name, int id) { super(name); this.id = id; }
    public int getId() { return id; }
}