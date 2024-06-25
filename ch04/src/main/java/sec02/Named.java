package sec02;

public interface Named {
    default String getName() {
        return "";
    }
}