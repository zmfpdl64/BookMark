package woojin.bookmaker.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static LocalDateTime now() {
        return LocalDateTime.now().plusHours(9);
    }
    public static String dateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(FORMAT);
    }
}
