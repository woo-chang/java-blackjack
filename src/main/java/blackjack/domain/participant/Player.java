package blackjack.domain.participant;

public class Player extends Participant {

    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    public boolean isDrawable() {
        if (getScore() < 21) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name.getValue();
    }
}
