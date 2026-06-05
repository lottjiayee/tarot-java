# tarot-java

A command-line Tarot card reading system built with Java. Draw cards, receive color-coded readings, save your history — all from the terminal. Features shuffle animations and randomized opening messages on every launch.

## Features

- **Daily Card** — Draw a single card for the day
- **Three-Card Spread** — Past, Present, and Future
- **Celtic Cross** — A full 10-card spread for in-depth readings
- **Browse Card Meanings** — View all 22 Major Arcana cards with full upright and reversed meanings
- **Question Input** — Enter your question before each reading; saved alongside your results
- **Color-Coded Output** — Upright cards in green, reversed cards in red
- **History** — All readings saved to a local file, viewable and clearable anytime

## Project Structure

```
tarot/
├── src/
│   ├── TarotApp.java         # Main program — menu and flow
│   ├── TarotCard.java        # Card model with ANSI color support
│   ├── TarotDeck.java        # Load deck, shuffle, draw logic
│   └── HistoryManager.java   # Save and read reading history
├── data/
│   └── cards.txt             # 22 Major Arcana cards with meanings
├── .gitignore
└── run.sh                    # One-command compile and run
```

## Requirements

- Java 9 or higher

## Getting Started

```bash
# Clone the repo
git clone https://github.com/lottjiayee/tarot-java.git
cd tarot-java

# Compile and run
bash run.sh
```

Or compile manually:

```bash
mkdir -p out
javac -d out src/TarotCard.java src/HistoryManager.java src/TarotDeck.java src/TarotApp.java
java -cp out TarotApp
```

## Example Output

```
  +====================================+
  |     *  Tarot Card Reading  *       |
  |   Listen to what the universe says |
  +====================================+

  +--------------------------------+
  |  1. Daily Card                 |
  |  2. Three-Card Spread          |
  |     (Past / Present / Future)  |
  |  3. Celtic Cross (10 cards)    |
  |  4. Browse Card Meanings       |
  |  5. View Reading History       |
  |  6. Clear History              |
  |  0. Exit                       |
  +--------------------------------+
  Your choice: 1

  What is your question for the Daily Card? (Press Enter to skip)
  Will things get better?

  Take a deep breath and hold your question in mind...

  +----------------------------------+
  The Star [Upright]
  Keyword: Hope
  Reading: After the darkness, a star of hope is lit once more.
           You are watched over by the universe — trust that
           healing and beauty are coming.
  +----------------------------------+

  Your question: Will things get better?
  Saved to history.
```

## Card Data

Cards are stored in `data/cards.txt` in a simple pipe-delimited format:

```
name|upright keyword|upright meaning|reversed keyword|reversed meaning
```

You can add or edit cards by modifying this file — no code changes needed.

## License

MIT
