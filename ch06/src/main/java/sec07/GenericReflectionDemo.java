package sec07;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericReflectionDemo {
    public static void main(String[] args) throws ReflectiveOperationException {
        TypeVariable<Class<ArrayList>>[] vars = ArrayList.class.getTypeParameters();
        String name = vars[0].getName(); // "E"
        System.out.println(name);

        Method m = Collections.class.getMethod("sort", List.class);
        TypeVariable<Method>[] vars2 = m.getTypeParameters();
        name = vars2[0].getName(); // "T"
        System.out.println(name);

        Type[] bounds = vars2[0].getBounds();
        if (bounds[0] instanceof ParameterizedType p) { // Comparable<? super T>
            Type[] typeArguments = p.getActualTypeArguments();
            if (typeArguments[0] instanceof WildcardType t) { // ? super T
                Type[] upper = t.getUpperBounds(); // ? extends ... & ...
                Type[] lower = t.getLowerBounds(); // ? super ... & ...
                if (lower.length > 0) {
                    String description = lower[0].getTypeName(); // "T"
                    System.out.println(description);
                }
            }
        }
    }
}
