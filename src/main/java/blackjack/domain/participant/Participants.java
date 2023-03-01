package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public void takeCard(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            for (final Participant participant : participants) {
                participant.takeCard(deck.draw());
            }
        }
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Dealer.class::isInstance)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }
}
