public class TarotCard {

    // ANSI color codes
    public static final String RESET   = "[0m";
    public static final String GREEN   = "[32m";
    public static final String RED     = "[31m";
    public static final String YELLOW  = "[33m";
    public static final String CYAN    = "[36m";
    public static final String BOLD    = "[1m";

    private String name;
    private String uprightKeyword;
    private String uprightMeaning;
    private String reversedKeyword;
    private String reversedMeaning;
    private boolean isReversed;

    public TarotCard(String name, String uprightKeyword, String uprightMeaning,
                     String reversedKeyword, String reversedMeaning) {
        this.name = name;
        this.uprightKeyword = uprightKeyword;
        this.uprightMeaning = uprightMeaning;
        this.reversedKeyword = reversedKeyword;
        this.reversedMeaning = reversedMeaning;
        this.isReversed = false;
    }

    public void setReversed(boolean reversed) { this.isReversed = reversed; }
    public boolean isReversed()               { return isReversed; }
    public String getName()                   { return name; }

    public String getCurrentKeyword() {
        return isReversed ? reversedKeyword : uprightKeyword;
    }

    public String getCurrentMeaning() {
        return isReversed ? reversedMeaning : uprightMeaning;
    }

    // Colored display for readings
    @Override
    public String toString() {
        String color       = isReversed ? RED : GREEN;
        String orientation = isReversed ? "[Reversed]" : "[Upright]";
        return String.format(
            "%s+----------------------------------+%s\n" +
            "  %s%s %s %s%s\n" +
            "  %sKeyword:%s %s\n" +
            "  %sReading:%s %s\n" +
            "%s+----------------------------------+%s",
            CYAN, RESET,
            BOLD, name, color + orientation, RESET, "",
            YELLOW, RESET, getCurrentKeyword(),
            YELLOW, RESET, getCurrentMeaning(),
            CYAN, RESET
        );
    }

    // Full detail display for the meanings browser
    public String toDetailString() {
        return String.format(
            "%s+----------------------------------+%s\n" +
            "  %s%s%s\n\n" +
            "  %s[Upright]%s  %s%s%s\n" +
            "  %s\n\n" +
            "  %s[Reversed]%s %s%s%s\n" +
            "  %s\n" +
            "%s+----------------------------------+%s",
            CYAN, RESET,
            BOLD + YELLOW, name, RESET,
            BOLD + GREEN, RESET, BOLD, uprightKeyword, RESET,
            uprightMeaning,
            BOLD + RED, RESET, BOLD, reversedKeyword, RESET,
            reversedMeaning,
            CYAN, RESET
        );
    }
}
