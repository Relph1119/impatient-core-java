package sec03;

import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        var badWords = new HashSet<>();
        badWords.add("sex");
        badWords.add("drugs");
        badWords.add("c++");

        var in = new Scanner(System.in);
        System.out.print("Please choose a user name: ");
        String username = in.next();
        if (badWords.contains(username.toLowerCase()))
            System.out.println("Please choose a different user name");
        else
            System.out.println("Registered " + username + " since it wasn't one of " + badWords);

        var countries = new TreeSet<String>((u, v) ->
                u.equals(v) ? 0
                        : u.equals("USA") ? -1
                        : v.equals("USA") ? 1
                        : u.compareTo(v));

        countries.add("Bahrain");
        countries.add("Australia");
        countries.add("USA");
        countries.add("Canada");
        System.out.println(countries);
    }
}
