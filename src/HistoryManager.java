import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HistoryManager {
    private static final String HISTORY_FILE = "data/history.txt";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void saveReading(String type, List<TarotCard> cards) {
        try {
            File file = new File(HISTORY_FILE);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("=== " + LocalDateTime.now().format(FORMATTER) + " | " + type + " ===");
            writer.newLine();
            for (TarotCard card : cards) {
                String orientation = card.isReversed() ? "Reversed" : "Upright";
                writer.write(card.getName() + " [" + orientation + "] - " + card.getCurrentKeyword());
                writer.newLine();
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Warning: Could not save reading history - " + e.getMessage());
        }
    }

    public void showHistory() {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) {
            System.out.println("  No reading history found.");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Warning: Could not read history - " + e.getMessage());
        }
    }

    public void clearHistory() {
        File file = new File(HISTORY_FILE);
        if (file.exists()) {
            file.delete();
            System.out.println("  History cleared.");
        } else {
            System.out.println("  No history to clear.");
        }
    }
}
