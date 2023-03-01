package blackjack.domain.participant;

public class Dealer extends Participant {

    private final String name = DEALER_NAME;

    public boolean isHit() {
        //2를 외부에서 받아서 사용해야 할까?
        if (cards.count() > 2) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
