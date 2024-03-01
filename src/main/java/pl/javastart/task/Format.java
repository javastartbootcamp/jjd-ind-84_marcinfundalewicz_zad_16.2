package pl.javastart.task;

public enum Format {
    FORMAT_1("yyyy-MM-dd HH:mm:ss", true),
    FORMAT_2("yyyy-MM-dd", false),
    FORMAT_3("dd.MM.yyyy HH:mm:ss", true);

    private String pattern;
    private boolean containsTime;

    Format(String pattern, boolean containsTime) {
        this.pattern = pattern;
        this.containsTime = containsTime;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isContainsTime() {
        return containsTime;
    }
}
