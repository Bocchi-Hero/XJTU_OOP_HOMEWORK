package hw02.cardgames;

public class Deck {
    private Card[] cards;
    private int cardCount;

    public Deck() {
        cards = new Card[52];
        String[] suits = {"黑桃","红桃","方块", "梅花"};
        int count = 0;

        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[count++] =new Card(rank, suit);
            }
        }
        cardCount = 0;
    }

    public void shuffle() {
        for (int i = 51; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        cardCount =  0;
    }

    public Card dealCard() {
        if (cardCount == 52) {
            throw new IllegalStateException("牌发完了。");
        }
        return cards[cardCount++];
    }
}
