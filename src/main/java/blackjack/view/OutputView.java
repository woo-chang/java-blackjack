package blackjack.view;

import static java.lang.String.format;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.dto.ParticipantDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitGame(final Dealer dealer, final Players players, final int count) {
        System.out.println(format("%s와 %s에게 %d장을 나누었습니다.", dealer.getName(), getFormattedNames(players), count));
    }

    private String getFormattedNames(final Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCards(final ParticipantDto dto) {
        System.out.println(getParticipantCardsMessage(dto.getName(), dto.getCards()));
    }

    private String getParticipantCardsMessage(final String name, final List<Card> cards) {
        return format("%s: %s", name, getFormattedCards(cards));
    }

    private String getFormattedCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    //딜러를 받는게 나을까? 여부만 받는게 나을까? 여부만 받게 되면 딜러 이름을 별도로 받아야 한다.
    public void printIsDealerHit(final Dealer dealer) {
        System.out.println(getDealerHitMessage(dealer));
    }

    private String getDealerHitMessage(final Dealer dealer) {
        if (dealer.isHit()) {
            return format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        }
        return format("%s는 17이상이라 한장의 카드를 더 받지 않았습니다.", dealer.getName());
    }

    public void printResult(final Participant participant, final Result result) {
        System.out.println(getResultMessage(participant, result));
    }

    public String getResultMessage(final Participant participant, final Result result) {
        final String participantCardsMessage = getParticipantCardsMessage(
                participant.getName(), participant.getCards()
        );
        if (participant instanceof Dealer) {
            return format("%s - 결과: %d", participantCardsMessage, result.getDealerScore());
        }
        return format("%s - 결과: %d", participantCardsMessage, result.getPlayerScore((Player) participant));
    }

    public void printWinOrLose(final Dealer dealer, final Players players, final Result result) {
        System.out.println("## 최종 승패");
        printDealerWinOrLose(dealer, result);
        printPlayersWinOrLose(players, result);
    }

    private void printDealerWinOrLose(final Dealer dealer, final Result result) {
        final String win = getWinOrLoseMessage("승", result.getDealerWinCount());
        final String draw = getWinOrLoseMessage("무", result.getDealerDrawCount());
        final String lose = getWinOrLoseMessage("패", result.getDealerLoseCount());
        System.out.println(format("%s: %s %s %s", dealer.getName(), win, draw, lose));
    }

    private String getWinOrLoseMessage(final String target, final int count) {
        if (count == 0) {
            return "";
        }
        return count + target;
    }

    private void printPlayersWinOrLose(final Players players, final Result result) {
        for (final Player player : players.getPlayers()) {
            System.out.println(format("%s: %s", player.getName(), getWinOrLoseMessage(player, result)));
        }
    }

    private String getWinOrLoseMessage(final Player player, final Result result) {
        final int dealerScore = result.getDealerScore();
        final int playerScore = result.getPlayerScore(player);
        if (playerScore > dealerScore) {
            return "승";
        }
        if (playerScore < dealerScore) {
            return "패";
        }
        return "무";
    }
}
