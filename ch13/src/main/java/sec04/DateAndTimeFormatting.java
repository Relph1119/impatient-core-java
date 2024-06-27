package sec04;

import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateAndTimeFormatting {
    public static void main(String[] args) {
        ZonedDateTime appointment = ZonedDateTime.of(2015, 7, 16, 9, 30, 0, 0,
                ZoneId.of("America/New_York"));

        // 日期以LONG格式显示，时间以SHORT格式显示
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG, FormatStyle.SHORT);
        String formatted = formatter.format(appointment);
        System.out.println(formatted);
        // 显示法语格式
        formatted = formatter.withLocale(Locale.FRENCH).format(appointment);
        System.out.println(formatted);
        // 显示英语格式
        LocalTime time = LocalTime.parse("9:32 AM",
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ENGLISH));
        System.out.println("Parsed time: " + time);

        // 使用芬兰语格式打印每个月的完整名称和独立名称
        Locale locale = Locale.forLanguageTag("fi");
        for (Month m : Month.values())
            System.out.printf("%-15s%-15s\n",
                    m.getDisplayName(TextStyle.FULL, locale),
                    m.getDisplayName(TextStyle.FULL_STANDALONE, locale));
    }
}
