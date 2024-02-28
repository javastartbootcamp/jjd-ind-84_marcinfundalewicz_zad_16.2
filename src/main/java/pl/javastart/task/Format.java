package pl.javastart.task;

public enum Format {
    FORMAT_1("yyyy-MM-dd HH:mm:ss"),
    FORMAT_2("yyyy-MM-dd"),
    FORMAT_3("dd.MM.yyyy HH:mm:ss");

    private String pattern;

    Format(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
