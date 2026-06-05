import java.util.*;

public class TarotApp {
    private static final String DATA_FILE = "data/cards.txt";
    private static TarotDeck deck;
    private static HistoryManager history = new HistoryManager();
    private static Scanner scanner = new Scanner(System.in);

    // Shorthand color refs
    private static final String R  = TarotCard.RESET;
    private static final String B  = TarotCard.BOLD;
    private static final String C  = TarotCard.CYAN;
    private static final String Y  = TarotCard.YELLOW;

    private static final String[] OPENING_MESSAGES = {
        "The cards have been waiting for you...",
        "Close your eyes. The universe is listening.",
        "Every card drawn is a mirror of your soul.",
        "The answers you seek are already within you.",
        "Trust the cards. Trust yourself.",
        "The veil between worlds grows thin tonight.",
        "Fate is not fixed — the cards show possibilities.",
        "Breathe. The cosmos has something to tell you.",
        "What is hidden shall be revealed.",
        "The stars have aligned. Are you ready?"
    };

    public static void main(String[] args) {
        try {
            deck = new TarotDeck(DATA_FILE);
        } catch (Exception e) {
            System.out.println("Error: Could not load card data - " + e.getMessage());
            System.out.println("Please make sure data/cards.txt exists.");
            return;
        }

        printBanner();
        printOpeningMessage();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> drawDailyCard();
                case "2" -> drawThreeCards();
                case "3" -> drawCelticCross();
                case "4" -> browseMeanings();
                case "5" -> history.showHistory();
                case "6" -> clearHistory();
                case "0" -> {
                    System.out.println("  May the stars guide your journey. Farewell.");
                    running = false;
                }
                default -> System.out.println("  Please enter a number between 0 and 6.");
            }
        }
        scanner.close();
    }

    static void printOpeningMessage() {
        Random rand = new Random();
        String msg = OPENING_MESSAGES[rand.nextInt(OPENING_MESSAGES.length)];
        System.out.println("  " + TarotCard.BOLD + msg + R);
        System.out.println();
        pause();
    }

    static void printBanner() {
        System.out.println();
        System.out.println(C + "  +====================================+" + R);
        System.out.println(C + "  |" + R + B + "     *  Tarot Card Reading  *      " + R + C + " |" + R);
        System.out.println(C + "  |" + R + "   Listen to what the universe says" + C + " |" + R);
        System.out.println(C + "  +====================================+" + R);
        System.out.println();
    }

    static void printMenu() {
        System.out.println(C + "  +--------------------------------+" + R);
        System.out.println(C + "  |" + R + "  1. Daily Card                  " + C + "|" + R);
        System.out.println(C + "  |" + R + "  2. Three-Card Spread           " + C + "|" + R);
        System.out.println(C + "  |" + R + "     (Past / Present / Future)   " + C + "|" + R);
        System.out.println(C + "  |" + R + "  3. Celtic Cross (10 cards)     " + C + "|" + R);
        System.out.println(C + "  |" + R + "  4. Browse Card Meanings        " + C + "|" + R);
        System.out.println(C + "  |" + R + "  5. View Reading History        " + C + "|" + R);
        System.out.println(C + "  |" + R + "  6. Clear History               " + C + "|" + R);
        System.out.println(C + "  |" + R + "  0. Exit                        " + C + "|" + R);
        System.out.println(C + "  +--------------------------------+" + R);
        System.out.print("  Your choice: ");
    }

    static String askQuestion(String spreadName) {
        System.out.print(Y + "  What is your question for the " + spreadName + "? " + R);
        System.out.print("(Press Enter to skip) ");
        String question = scanner.nextLine().trim();
        System.out.println();
        return question;
    }

    static void drawDailyCard() {
        System.out.println(B + "  -- Daily Card ---------------------------" + R);
        String question = askQuestion("Daily Card");
        System.out.println("  Take a deep breath and hold your question in mind...");
        shuffleAnimation();
        TarotCard card = deck.drawOne();
        System.out.println();
        System.out.println("  " + card.toString().replace("\n", "\n  "));
        System.out.println();
        if (!question.isEmpty()) {
            System.out.println(Y + "  Your question: " + R + question);
            System.out.println();
        }
        history.saveReading("Daily Card", question, List.of(card));
        System.out.println("  Saved to history.");
        System.out.println();
    }

    static void drawThreeCards() {
        System.out.println(B + "  -- Three-Card Spread ---------------------" + R);
        String question = askQuestion("Three-Card Spread");
        System.out.println("  Focus on your question and feel the cards...");
        shuffleAnimation();
        List<TarotCard> cards = deck.drawMany(3);
        String[] positions = {"Past", "Present", "Future"};
        System.out.println();
        if (!question.isEmpty()) {
            System.out.println(Y + "  Question: " + R + question);
            System.out.println();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(B + "  [" + positions[i] + "]" + R);
            System.out.println("  " + cards.get(i).toString().replace("\n", "\n  "));
            System.out.println();
        }
        history.saveReading("Three-Card Spread", question, cards);
        System.out.println("  Saved to history.");
        System.out.println();
    }

    static void drawCelticCross() {
        System.out.println(B + "  -- Celtic Cross --------------------------" + R);
        String question = askQuestion("Celtic Cross");
        System.out.println("  This is the most complete spread. Clear your mind...");
        shuffleAnimation();
        List<TarotCard> cards = deck.drawMany(10);
        String[] positions = {
            "Present Situation", "Challenge / Obstacle", "Subconscious Root", "Recent Past",
            "Possible Future",   "Near Future",          "Your Inner State",  "External Influences",
            "Hopes and Fears",   "Final Outcome"
        };
        System.out.println();
        if (!question.isEmpty()) {
            System.out.println(Y + "  Question: " + R + question);
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            System.out.printf(B + "  [%2d] %s" + R + "%n", i + 1, positions[i]);
            System.out.println("  " + cards.get(i).toString().replace("\n", "\n  "));
            System.out.println();
        }
        history.saveReading("Celtic Cross", question, cards);
        System.out.println("  Saved to history.");
        System.out.println();
    }

    static void browseMeanings() {
        System.out.println(B + "  -- Card Meanings (" + deck.size() + " cards) ---------------" + R);
        System.out.println();
        List<TarotCard> allCards = deck.getAllCards();
        for (int i = 0; i < allCards.size(); i++) {
            System.out.printf(Y + "  (%d/%d)" + R + "%n", i + 1, allCards.size());
            System.out.println("  " + allCards.get(i).toDetailString().replace("\n", "\n  "));
            System.out.println();
            if ((i + 1) % 3 == 0 && i + 1 < allCards.size()) {
                System.out.print("  Press Enter to continue...");
                scanner.nextLine();
                System.out.println();
            }
        }
        System.out.println("  End of card list.");
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

    static void shuffleAnimation() {
        String[] frames = { "Shuffling .  ", "Shuffling .. ", "Shuffling ..." };
        try {
            for (int i = 0; i < 6; i++) {
                System.out.print("\r  " + C + frames[i % 3] + R);
                Thread.sleep(350);
            }
            System.out.print("\r  Drawing your card...   \n");
            Thread.sleep(400);
        } catch (InterruptedException ignored) {}
    }

    static void pause() {
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }
}
