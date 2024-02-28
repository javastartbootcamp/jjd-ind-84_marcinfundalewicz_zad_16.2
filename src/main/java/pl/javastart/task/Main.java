package pl.javastart.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static pl.javastart.task.Format.FORMAT_1;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        try {
            TimeManager timeManager = new TimeManager();
            timeManager.printOptions();
            String userDate = timeManager.readDate(scanner);
            DateTimeFormatter dateTimeFormatter = timeManager.getDateTimeFormatter(userDate);
            LocalDateTime dateTime = timeManager.createDateTime(userDate, dateTimeFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(FORMAT_1.getPattern());

            var zones = List.of(
                    new TimeZoneToDisplay("Czas lokalny", ZoneId.systemDefault().getId()),
                    new TimeZoneToDisplay("UTC", "UTC"),
                    new TimeZoneToDisplay("Londyn", "Europe/London"),
                    new TimeZoneToDisplay("Los Angeles", "America/Los_Angeles"),
                    new TimeZoneToDisplay("Sydney", "Australia/Sydney")
            );
            for (TimeZoneToDisplay zone : zones) {
                System.out.printf("%s: %s%n", zone.display(), timeManager.getTimeForZone(dateTime, outputFormatter, zone.zoneName()));
            }
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
