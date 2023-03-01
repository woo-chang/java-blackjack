package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.DrawOption;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final int PACK_COUNT = 1;
    private static final int INIT_CARD_COUNT = 2;
    private static final int DRAW_LIMIT = 21;
    public static final int DEALER_EXTRA_DRAW_LIMIT = 17;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = gatherParticipants();
        final Deck deck = DeckFactory.makeWithPacks(PACK_COUNT);

        deck.shuffle();
        dealInitCards(participants, deck);

        cardDraw(participants.getPlayers(), deck);
        cardDraw(participants.getDealer(), deck);

        printResult(participants);
    }

    //딜러와 플레이어는 하나의 리스트로 관리해도 괜찮지 않을까?
    private Participants gatherParticipants() {
        final List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        participants.addAll(createPlayers());
        return new Participants(participants);
    }

    private List<Player> createPlayers() {
        final List<String> playerNames = inputView.readPlayerNames();
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void dealInitCards(final Participants participants, final Deck deck) {
        participants.takeCard(deck, INIT_CARD_COUNT);
        final ParticipantDto dealer = ParticipantDto.from(participants.getDealer());
        final List<ParticipantDto> players = createPlayerDto(participants.getPlayers());
        outputView.printInitGame(dealer, players, INIT_CARD_COUNT);
    }

    private List<ParticipantDto> createPlayerDto(final List<Player> participants) {
        return participants.stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());
    }

    private void cardDraw(final List<Player> players, final Deck deck) {
        for (final Player player : players) {
            cardDraw(player, deck);
        }
    }

    private void cardDraw(final Player player, final Deck deck) {
        while (player.isDrawable(DRAW_LIMIT) && isWantToDraw(player)) {
            player.takeCard(deck.draw());
            outputView.printPlayerCards(ParticipantDto.from(player));
        }
    }

    private boolean isWantToDraw(final Player player) {
        final String input = inputView.readReceiveMore(player);
        final DrawOption option = DrawOption.from(input);
        if (option == DrawOption.HIT) {
            return true;
        }
        return false;
    }

    private void cardDraw(final Dealer dealer, final Deck deck) {
        if (dealer.isDrawable(DEALER_EXTRA_DRAW_LIMIT)) {
            dealer.takeCard(deck.draw());
        }
        outputView.printDealerHit(dealer);
    }

    private void printResult(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        final ParticipantDto dealerDto = ParticipantDto.from(dealer);
        final List<ParticipantDto> playerDto = createPlayerDto(players);

        outputView.printResult(dealerDto, playerDto);
        outputView.printWinOrLose(dealerDto, playerDto);
    }
}
