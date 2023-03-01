package blackjack.domain.participant;

public class Player extends Participant {

    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getValue();
    }
}
