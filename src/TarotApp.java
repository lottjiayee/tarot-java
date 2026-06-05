import java.util.*;

public class TarotApp {
    private static final String DATA_FILE = "data/cards.txt";
    private static TarotDeck deck;
    private static HistoryManager history = new HistoryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            deck = new TarotDeck(DATA_FILE);
        } catch (Exception e) {
            System.out.println("Error: Could not load card data - " + e.getMessage());
            System.out.println("Please make sure data/cards.txt exists.");
            return;
        }

        printBanner();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> drawDailyCard();
                case "2" -> drawThreeCards();
                case "3" -> drawCelticCross();
                case "4" -> history.showHistory();
                case "5" -> clearHistory();
                case "0" -> {
                    System.out.println("  May the stars guide your journey. Farewell.");
                    running = false;
                }
                default -> System.out.println("  Please enter a number between 0 and 5.");
            }
        }
        scanner.close();
    }

    static void printBanner() {
        System.out.println();
        System.out.println("  +====================================+");
        System.out.println("  |     *  Tarot Card Reading  *       |");
        System.out.println("  |   Listen to what the universe says |");
        System.out.println("  +====================================+");
        System.out.println();
    }

    static void printMenu() {
        System.out.println("  +-------------------------------+");
        System.out.println("  |  1. Daily Card                |");
        System.out.println("  |  2. Three-Card Spread         |");
        System.out.println("  |     (Past / Present / Future) |");
        System.out.println("  |  3. Celtic Cross (10 cards)   |");
        System.out.println("  |  4. View Reading History      |");
        System.out.println("  |  5. Clear History             |");
        System.out.println("  |  0. Exit                      |");
        System.out.println("  +-------------------------------+");
        System.out.print("  Your choice: ");
    }

    static void drawDailyCard() {
        System.out.println("  -- Daily Card ---------------------------");
        System.out.println("  Take a deep breath and hold your question in mind...");
        pause();
        TarotCard card = deck.drawOne();
        System.out.println();
        System.out.println("  " + card);
        System.out.println();
        history.saveReading("Daily Card", List.of(card));
        System.out.println("  Saved to history.");
        System.out.println();
    }

    static void drawThreeCards() {
        System.out.println("  -- Three-Card Spread ---------------------");
        System.out.println("  Focus on your question and feel the cards...");
        pause();
        List<TarotCard> cards = deck.drawMany(3);
        String[] positions = {"Past", "Present", "Future"};
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println("  [" + positions[i] + "]");
            System.out.println("  " + cards.get(i).toString().replace("\n", "\n  "));
            System.out.println();
        }
        history.saveReading("Three-Card Spread", cards);
        System.out.println("  Saved to history.");
        System.out.println();
    }

    static void drawCelticCross() {
        System.out.println("  -- Celtic Cross --------------------------");
        System.out.println("  This is the most complete spread. Clear your mind...");
        pause();
        List<TarotCard> cards = deck.drawMany(10);
        String[] positions = {
            "Present Situation", "Challenge / Obstacle", "Subconscious Root", "Recent Past",
            "Possible Future", "Near Future", "Your Inner State", "External Influences",
            "Hopes and Fears", "Final Outcome"
        };
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("  [%2d] %s%n", i + 1, positions[i]);
            System.out.println("  " + cards.get(i).toString().replace("\n", "\n  "));
            System.out.println();
        }
        history.saveReading("Celtic Cross", cards);
        System.out.println("  Saved to history.");
        System.out.println();
    }

    static void clearHistory() {
        System.out.print("  Are you sure you want to clear all history? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            history.clearHistory();
        } else {
            System.out.println("  Cancelled.");
        }
        System.out.println();
    }

    static void pause() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException ignored) {}
    }
}
