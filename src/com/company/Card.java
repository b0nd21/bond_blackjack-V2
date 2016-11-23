package com.company;

/**
 * Created by student3 on 10.11.16.
 */
public class Card {
    Suit suit;
    Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value +" OF " + suit;
    }

    public int getScore() {
        return this.value.score;
    }
}
