package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.ParticipantDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    //컨트롤러에서 하는 역할이 너무 많다. 나눌 수 없을까?
    public void run() {
        //딜러와 플레이어는 하나의 리스트로 관리해도 괜찮지 않을까?
        final Dealer dealer = new Dealer();
        final Players players = Players.from(inputView.readPlayerNames());
        final Deck deck = DeckFactory.createWithCount(1);
        initGame(dealer, players, deck);
        decideCardDraw(players, deck);
        decideCardDraw(dealer, deck);
        final Result result = calculateResult(dealer, players);
        outputView.printResult(dealer, result);
        outputView.printWinOrLose(dealer, players, result);
    }

    private void initGame(final Dealer dealer, final Players players, final Deck deck) {
        deck.shuffle();
        final int count = 2;
        for (int i = 0; i < count; i++) {
            dealer.addCard(deck.draw());
            players.addCard(deck);
        }
        outputView.printInitGame(dealer, players, count);
        printCards(dealer, players);
    }

    private void printCards(final Dealer dealer, final Players players) {
        outputView.printCards(new ParticipantDto(dealer.getName(), dealer.getCards()));
        for (final Player player : players.getPlayers()) {
            outputView.printCards(new ParticipantDto(player.getName(), player.getCards()));
        }
    }

    private void decideCardDraw(final Players players, final Deck deck) {
        final List<Player> decidePlayers = players.getPlayers();
        for (final Player decidePlayer : decidePlayers) {
            decideCardDraw(decidePlayer, deck);
        }
    }

    private void decideCardDraw(final Player player, final Deck deck) {
        while (player.isDrawable() && isWantToHit(player)) {
            player.addCard(deck.draw());
            outputView.printCards(new ParticipantDto(player.getName(), player.getCards()));
        }
    }

    private boolean isWantToHit(final Player player) {
        final String input = inputView.readReceiveMore(player);
        if (input.equals("y")) {
            return true;
        }
        return false;
    }

    private void decideCardDraw(final Dealer dealer, final Deck deck) {
        if (dealer.getScore() <= 16) {
            dealer.addCard(deck.draw());
        }
        outputView.printIsDealerHit(dealer);
    }

    private Result calculateResult(final Dealer dealer, final Players players) {
        final int dealerScore = dealer.getScore();
        final Map<Player, Integer> playerScore = new LinkedHashMap<>();

        for (final Player player : players.getPlayers()) {
            playerScore.put(player, player.getScore());
        }

        return new Result(dealerScore, playerScore);
    }
}
