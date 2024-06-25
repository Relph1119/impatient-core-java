package sec05;

import java.io.*;

public class Employee implements Serializable {
    private final String name;
    private double salary;
    private Manager boss;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public void setBoss(Manager boss) {
        this.boss = boss;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return "%s[name=%s,salary=%.2f,boss=%s]".formatted(
                getClass().getName(), name, salary, boss);
    }

    public void validateObject() throws InvalidObjectException {
        System.out.println("validateObject");
        if (salary < 0) {
            throw new InvalidObjectException("salary < 0");
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.registerValidation((ObjectInputValidation) this, 0);
        in.defaultReadObject();
    }
}
