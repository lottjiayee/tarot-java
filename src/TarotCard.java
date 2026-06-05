public class TarotCard {
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

    public void setReversed(boolean reversed) {
        this.isReversed = reversed;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public String getName() {
        return name;
    }

    public String getCurrentKeyword() {
        return isReversed ? reversedKeyword : uprightKeyword;
    }

    public String getCurrentMeaning() {
        return isReversed ? reversedMeaning : uprightMeaning;
    }

    @Override
    public String toString() {
        String orientation = isReversed ? "[Reversed]" : "[Upright]";
        return String.format("* %s %s\n  Keyword: %s\n  Reading: %s",
                name, orientation, getCurrentKeyword(), getCurrentMeaning());
    }
}
