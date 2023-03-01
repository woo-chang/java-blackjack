package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        final List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public void addCard(final Deck deck) {
        for (final Player player : players) {
            player.addCard(deck.draw());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
