package blackjack.domain.card;

public class Card {

    private final Suit suit;
    private final Number number;

    public Card(final Suit suit, final Number number) {
        this.suit = suit;
        this.number = number;
    }

    public int getScore() {
        return number.getScore();
    }

    @Override
    public String toString() {
        return number.getName() + suit.getName();
    }
}
