#!/bin/bash
# Compile and run the Tarot Card Reading System

mkdir -p out
javac -d out src/TarotCard.java src/HistoryManager.java src/TarotDeck.java src/TarotApp.java

if [ $? -eq 0 ]; then
    echo "Compiled successfully! Starting program..."
    java -cp out TarotApp
else
    echo "Compilation failed. Please check the errors above."
fi
