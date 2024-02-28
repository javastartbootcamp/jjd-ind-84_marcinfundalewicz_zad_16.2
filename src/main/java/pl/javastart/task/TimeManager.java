package pl.javastart.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeManager {

    public void printOptions() {
        System.out.println("Podaj datę zgodną z jednym z poniższych formatów");
        for (Format timeFormat : Format.values()) {
            System.out.println(timeFormat.getPattern());
        }
    }

    public String readDate(Scanner scanner) {
        System.out.println("Podaj datę");
        return scanner.nextLine();
    }

    public LocalDateTime createDateTime(String date, DateTimeFormatter formatter) {
        return LocalDateTime.parse(date, formatter);
    }

    public String getTimeForZone(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter, String timeZone) {
        ZonedDateTime localDateTimeZone = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime timeZoned = localDateTimeZone.withZoneSameInstant(ZoneId.of(timeZone));
        return timeZoned.toLocalDateTime().format(dateTimeFormatter);
    }

    private List<DateTimeFormatter> initFormattersList() {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter;
        Format[] formatsValues = Format.values();
        for (Format formatsValue : formatsValues) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(formatsValue.getPattern());
            formatters.add(dateTimeFormatter);
        }
        return formatters;
    }

    public DateTimeFormatter getDateTimeFormatter(String date) throws DateTimeException {
        DateTimeFormatter formatter = null;
        LocalDateTime localDateTime = null;
        List<DateTimeFormatter> dateTimeFormatters = initFormattersList();
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
            formatter = dateTimeFormatter;
            break;
        }
        if (localDateTime == null) {
            throw new DateTimeException("Nieprawidłowy format");
        }
        return formatter;
    }
}
