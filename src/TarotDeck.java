import java.io.*;
import java.util.*;

public class TarotDeck {
    private List<TarotCard> cards = new ArrayList<>();
    private Random random = new Random();

    public TarotDeck(String dataFilePath) throws IOException {
        loadCards(dataFilePath);
    }

    private void loadCards(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            String[] parts = line.split("\\|");
            if (parts.length == 5) {
                cards.add(new TarotCard(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        }
        reader.close();
    }

    public int size() {
        return cards.size();
    }

    // Return a copy of all cards for browsing
    public List<TarotCard> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    // Draw one card with random orientation
    public TarotCard drawOne() {
        TarotCard card = cards.get(random.nextInt(cards.size()));
        card.setReversed(random.nextBoolean());
        return card;
    }

    // Draw multiple unique cards with random orientations
    public List<TarotCard> drawMany(int count) {
        List<TarotCard> deck = new ArrayList<>(cards);
        Collections.shuffle(deck, random);
        List<TarotCard> drawn = deck.subList(0, Math.min(count, deck.size()));
        for (TarotCard card : drawn) {
            card.setReversed(random.nextBoolean());
        }
        return new ArrayList<>(drawn);
    }
}
