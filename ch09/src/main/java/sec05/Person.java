package sec05;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Serial
    private Object writeReplace() {
        return new PersonProxy(id);
    }

    public String toString() {
        return "%s[id=%d,name=%s]".formatted(getClass().getName(), id, name);
    }
}
