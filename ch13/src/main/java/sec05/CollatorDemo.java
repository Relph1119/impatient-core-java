package sec05;

import java.text.Collator;
import java.text.Normalizer;
import java.util.*;

public class CollatorDemo {
    public static void main(String[] args) {
        // 以英文排序
        Locale locale = Locale.forLanguageTag("en");
        Collator coll = Collator.getInstance(locale);
        var words = new ArrayList<>(
                List.of("Athens", "Ångström", "Zulu", "able", "zebra"));
        words.sort(coll);
        System.out.println(words);

        coll = Collator.getInstance(locale);
        coll.setStrength(Collator.PRIMARY);
        var set = new TreeSet<>(coll);
        set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
        System.out.println(set);

        coll = Collator.getInstance(locale);
        coll.setStrength(Collator.SECONDARY);
        set = new TreeSet<>(coll);
        set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
        System.out.println(set);

        coll = Collator.getInstance(locale);
        coll.setStrength(Collator.TERTIARY);
        set = new TreeSet<>(coll);
        set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
        System.out.println(set);

        coll = Collator.getInstance(locale);
        coll.setStrength(Collator.IDENTICAL);
        coll.setDecomposition(Collator.NO_DECOMPOSITION);
        set = new TreeSet<>(coll);
        set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
        System.out.println(set);

        coll = Collator.getInstance(locale);
        set = new TreeSet<>(coll);
        set.addAll(List.of("JavaTM", "Java\u2122"));
        System.out.println(set);

        coll = Collator.getInstance(locale);
        coll.setDecomposition(Collator.FULL_DECOMPOSITION);
        set = new TreeSet<>(coll);
        set.addAll(List.of("JavaTM", "Java\u2122"));
        System.out.println(set);

        System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFC).codePoints().mapToObj("%04X"::formatted).toArray()));
        System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFD).codePoints().mapToObj("%04X"::formatted).toArray()));
        System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFKC).codePoints().mapToObj("%04X"::formatted).toArray()));
        System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFKD).codePoints().mapToObj("%04X"::formatted).toArray()));
    }
}
