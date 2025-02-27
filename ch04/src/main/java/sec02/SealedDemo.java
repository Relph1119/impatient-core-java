package sec02;

/*

Compile and run with

javac --enable-preview --release 17 ch04/sec02/SealedDemo.java
java --enable-preview ch04.sec02.SealedDemo 
 
*/

import java.util.ArrayList;
import java.util.HashMap;

enum JSONBoolean implements JSONPrimitive {
    FALSE, TRUE;

    public String toString() {
        return super.toString().toLowerCase();
    }
}

enum JSONNull implements JSONPrimitive {
    INSTANCE;

    public String toString() {
        return "null";
    }
}

sealed interface JSONValue permits JSONArray, JSONObject, JSONPrimitive {
    default String type() {
        return switch (this) {
            case JSONArray
                j -> "array";
            case JSONNumber
                j -> "number";
            case JSONString
                j -> "string";
            case JSONBoolean
                j -> "boolean";
            case JSONObject
                j -> "object";
            case JSONNull
                j -> "null";
                // No default needed here
        };
    }
}

sealed interface JSONPrimitive extends JSONValue permits JSONNumber, JSONString, JSONBoolean, JSONNull {
}

final class JSONArray extends ArrayList<JSONValue> implements JSONValue {
}

final class JSONObject extends HashMap<String, JSONValue> implements JSONValue {
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (Entry<String, JSONValue> entry : entrySet()) {
            if (result.length() > 1)
                result.append(",");
            result.append(" \"");
            result.append(entry.getKey());
            result.append("\": ");
            result.append(entry.getValue());
        }
        result.append(" }");
        return result.toString();
    }
}

final record JSONNumber(double value) implements JSONPrimitive {
    public String toString() {
        return "" + value;
    }
}

final record JSONString(String value) implements JSONPrimitive {
    public String toString() {
        return "\"" + value.translateEscapes() + "\"";
    }
}

public class SealedDemo {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("name", new JSONString("Harry"));
        obj.put("salary", new JSONNumber(90000));
        obj.put("married", JSONBoolean.FALSE);
        JSONArray arr = new JSONArray();
        arr.add(new JSONNumber(13));
        arr.add(JSONNull.INSTANCE);

        obj.put("luckyNumbers", arr);
        System.out.println(obj);
        System.out.println(obj.type());
    }
}
