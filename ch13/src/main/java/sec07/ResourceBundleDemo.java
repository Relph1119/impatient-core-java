package sec07;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) {
        System.out.println(Locale.getDefault(Locale.Category.DISPLAY));
        System.out.println(Locale.getDefault(Locale.Category.FORMAT));

        Locale displayLocale = Locale.forLanguageTag("en");
        Locale formatLocale = Locale.forLanguageTag("fr-FR");
        ResourceBundle res = ResourceBundle.getBundle("messages", displayLocale);
        ResourceBundle resFrFr = ResourceBundle.getBundle("messages", formatLocale);

        String priceTemplate = resFrFr.getString("price");
        System.out.println(MessageFormat.format(priceTemplate, 19.95));
        System.out.println(res.getString("greeting"));
        System.out.println(res.getString("farewell"));
    }
}
