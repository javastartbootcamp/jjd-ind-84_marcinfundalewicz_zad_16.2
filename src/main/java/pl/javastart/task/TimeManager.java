package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
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
            if (!formatsValue.isContainsTime()) {
                dateTimeFormatter = new DateTimeFormatterBuilder()
                        .appendPattern("yyyy-MM-dd")
                        .optionalStart()
                        .appendPattern(" HH:mm")
                        .optionalEnd()
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .toFormatter();

            } else {
                dateTimeFormatter = DateTimeFormatter.ofPattern(formatsValue.getPattern());
            }

            formatters.add(dateTimeFormatter);

        }

        return formatters;
    }

    public DateTimeFormatter getDateTimeFormatter(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = null;
        LocalDateTime localDateTime = null;
        List<DateTimeFormatter> dateTimeFormatters = initFormattersList();
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
                formatter = dateTimeFormatter;
                break;
            } catch (DateTimeParseException e) {
                //
            }
        }
        if (localDateTime == null) {
            throw new DateTimeParseException("Nieprawidłowy format daty", dateString, 0);
        }

        return formatter;
    }
}
