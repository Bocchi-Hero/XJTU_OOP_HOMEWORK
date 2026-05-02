package hw02.cardgames;

public class Card {
    private int rank;
    private String suit;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        return rank;
    }

    @Override
    public String toString() {
        return suit + rank;
    }
}
