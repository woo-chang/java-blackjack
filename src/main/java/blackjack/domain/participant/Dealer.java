package blackjack.domain.participant;

public class Dealer extends Participant {

    private final String name = DEALER_NAME;

    public boolean isHit() {
        if (cards.count() > 2) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
