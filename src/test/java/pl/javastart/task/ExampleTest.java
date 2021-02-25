package pl.javastart.task;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Timeout(5)
public class ExampleTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Main main;

    @DisplayName("2022-10-23 14:15:51")
    @Test
    void shouldWorkForExerciseExample() {
        // given

        Scanner scanner = provideInput("2022-10-23 14:15:51");

        // when
        main.run(scanner);

        // then
        assertThat(outContent.toString()).contains("Czas lokalny: 2022-10-23 14:15:51");
        assertThat(outContent.toString()).contains("UTC: 2022-10-23 12:15:51");
        assertThat(outContent.toString()).contains("Londyn: 2022-10-23 13:15:51");
        assertThat(outContent.toString()).contains("Los Angeles: 2022-10-23 05:15:51");
        assertThat(outContent.toString()).contains("Sydney: 2022-10-23 23:15:51");
    }

    @DisplayName("2022-10-23 10:00:00")
    @Test
    void shouldWorkForOtherPatternInput() {
        // given
        Scanner scanner = provideInput("2022-10-23 10:00:00");

        // when
        main.run(scanner);

        // then
        assertThat(outContent.toString()).contains("Czas lokalny: 2022-10-23 10:00:00");
        assertThat(outContent.toString()).contains("UTC: 2022-10-23 08:00:00");
        assertThat(outContent.toString()).contains("Londyn: 2022-10-23 09:00:00");
        assertThat(outContent.toString()).contains("Los Angeles: 2022-10-23 01:00:00");
        assertThat(outContent.toString()).contains("Sydney: 2022-10-23 19:00:00");
    }

    @DisplayName("2022-10-23")
    @Test
    void shouldWorkForWithoutHour() {
        // given
        Scanner scanner = provideInput("2022-10-23");

        // when
        main.run(scanner);

        // then
        assertThat(outContent.toString()).contains("Czas lokalny: 2022-10-23 00:00:00");
        assertThat(outContent.toString()).contains("UTC: 2022-10-22 22:00:00");
        assertThat(outContent.toString()).contains("Londyn: 2022-10-22 23:00:00");
        assertThat(outContent.toString()).contains("Los Angeles: 2022-10-22 15:00:00");
        assertThat(outContent.toString()).contains("Sydney: 2022-10-23 09:00:00");
    }

    @DisplayName("05.12.2015 22:00:00")
    @Test
    void shouldWorkForOtherFormat() {
        // given
        Scanner scanner = provideInput("05.12.2015 22:00:00");

        // when
        main.run(scanner);

        // then
        assertThat(outContent.toString()).contains("Czas lokalny: 2015-12-05 22:00:00");
        assertThat(outContent.toString()).contains("UTC: 2015-12-05 21:00:00");
        assertThat(outContent.toString()).contains("Londyn: 2015-12-05 21:00:00");
        assertThat(outContent.toString()).contains("Los Angeles: 2015-12-05 13:00:00");
        assertThat(outContent.toString()).contains("Sydney: 2015-12-06 08:00:00");
    }


    @BeforeEach
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
    }

    private Scanner provideInput(String... lines) {
        String input = String.join("\r\n", lines);
        input += "\r\n";

        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        return new Scanner(testIn);
    }

}
