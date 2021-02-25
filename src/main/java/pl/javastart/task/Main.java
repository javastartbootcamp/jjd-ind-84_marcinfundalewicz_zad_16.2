package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj datę:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        ZonedDateTime parseDate = parseDate(s);
        if (parseDate != null) {
            displayDate(parseDate);
        } else {
            System.out.println("Nie udało się sparsować daty");
        }
    }

    private static ZonedDateTime parseDate(String dateAsString) {
        TemporalAccessor temporalAccessor;
        try {
            temporalAccessor = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(dateAsString);
            LocalDate localdate = LocalDate.from(temporalAccessor);
            return ZonedDateTime.of(localdate, LocalTime.of(0, 0), ZoneId.systemDefault());
        } catch (DateTimeParseException e) {
            // ignore
        }

        List<String> patterns = Arrays.asList("yyyy-MM-dd HH:mm:ss", "dd.MM.yyyy HH:mm:ss", "dd.MM.yyyy HH:mm");

        for (String pattern : patterns) {
            try {
                temporalAccessor = DateTimeFormatter.ofPattern(pattern).parse(dateAsString);
                LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
                return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            } catch (DateTimeParseException e) {
                // ignore
            }
        }

        return null;
    }

    private static void displayDate(ZonedDateTime parseDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Czas lokalny: " + parseDate.format(format));
        System.out.println("UTC: " + parseDate.withZoneSameInstant(ZoneId.of("UTC")).format(format));
        System.out.println("Londyn: " + parseDate.withZoneSameInstant(ZoneId.of("Europe/London")).format(format));
        System.out.println("Los Angeles: " + parseDate.withZoneSameInstant(ZoneId.of("America/Los_Angeles")).format(format));
        System.out.println("Sydney: " + parseDate.withZoneSameInstant(ZoneId.of("Australia/Sydney")).format(format));
    }

}
