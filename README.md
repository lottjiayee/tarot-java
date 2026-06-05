# tarot-java

A command-line Tarot card reading system built with Java. Draw cards, receive readings, and save your history — all from the terminal.

## Features

- **Daily Card** — Draw a single card for the day
- **Three-Card Spread** — Past, Present, and Future
- **Celtic Cross** — A full 10-card spread for in-depth readings
- **History** — All readings are saved to a local file and can be reviewed or cleared

## Project Structure

```
tarot/
├── src/
│   ├── TarotApp.java         # Main program — menu and flow
│   ├── TarotCard.java        # Card data model
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

  +-------------------------------+
  |  1. Daily Card                |
  |  2. Three-Card Spread         |
  |     (Past / Present / Future) |
  |  3. Celtic Cross (10 cards)   |
  |  4. View Reading History      |
  |  5. Clear History             |
  |  0. Exit                      |
  +-------------------------------+
  Your choice: 1

  -- Daily Card ---------------------------
  Take a deep breath and hold your question in mind...

  * The Star [Upright]
    Keyword: Hope
    Reading: After the darkness, a star of hope is lit once more.
             You are watched over by the universe — trust that healing
             and beauty are coming.

  Saved to history.
```

## Card Data

Cards are stored in `data/cards.txt` in a simple pipe-delimited format:

```
name|upright keyword|upright meaning|reversed keyword|reversed meaning
```

You can easily add or edit cards by modifying this file — no code changes needed.

## License

MIT
