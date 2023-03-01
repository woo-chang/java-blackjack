package blackjack.view;

import static blackjack.controller.BlackJackController.DEALER_EXTRA_DRAW_LIMIT;
import static java.text.MessageFormat.format;

import blackjack.domain.participant.Dealer;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.ParticipantDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String CARD_INFO_FORMAT = "{0}카드: {1}";

    public void printInitGame(final ParticipantDto dealer, final List<ParticipantDto> players, final int count) {
        System.out.println(format("{0}와 {1}에게 {2}장을 나누었습니다.", dealer.getName(), getFormattedNames(players), count));
        printDealerCards(dealer);
        for (final ParticipantDto player : players) {
            printPlayerCards(player);
        }
    }

    private String getFormattedNames(final List<ParticipantDto> players) {
        return players.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(", "));
    }

    //인덱스를 사용하는데 카드가 1장도 없는 경우를 고려해야할까?
    private void printDealerCards(final ParticipantDto dealer) {
        final CardDto cardDto = dealer.getCardDto();
        final List<String> cardInfos = cardDto.getCardInfos();
        System.out.println(format(CARD_INFO_FORMAT, dealer.getName(), cardInfos.get(0)));
    }

    public void printPlayerCards(final ParticipantDto player) {
        final CardDto cardDto = player.getCardDto();
        final List<String> cardInfos = cardDto.getCardInfos();
        System.out.println(format(CARD_INFO_FORMAT, player.getName(), getFormattedCards(cardInfos)));
    }

    private String getFormattedCards(final List<String> cardInfos) {
        return cardInfos.stream()
                .collect(Collectors.joining(", "));
    }

    //딜러를 받는게 나을까? 여부만 받는게 나을까? 여부만 받게 되면 딜러 이름을 별도로 받아야 한다.
    public void printDealerHit(final Dealer dealer) {
        System.out.println(getDealerHitMessage(dealer));
    }

    private String getDealerHitMessage(final Dealer dealer) {
        //딜러 카드 제한 상수를 관리할 수 있는 방법이 없을까?
        if (dealer.isHit()) {
            return format("{0}는 {1}이하라 한장의 카드를 더 받았습니다.", dealer.getName(), DEALER_EXTRA_DRAW_LIMIT - 1);
        }
        return format("{0}는 {1}이상이라 한장의 카드를 더 받지 않았습니다.", dealer.getName(), DEALER_EXTRA_DRAW_LIMIT);
    }

    public void printResult(final ParticipantDto dealer, final List<ParticipantDto> players) {
        printParticipantResult(dealer);
        for (final ParticipantDto player : players) {
            printParticipantResult(player);
        }
    }

    private void printParticipantResult(final ParticipantDto participant) {
        final CardDto cardDto = participant.getCardDto();
        final List<String> cardInfos = cardDto.getCardInfos();
        System.out.println(format("{0}카드: {1} - 결과: {2}",
                participant.getName(), getFormattedCards(cardInfos), cardDto.getScore()));
    }

    public void printWinOrLose(final ParticipantDto dealer, final List<ParticipantDto> players) {
        System.out.println("## 최종 승패");
        printDealerWinOrLose(dealer, players);
        for (final ParticipantDto player : players) {
            printPlayerWinOrLose(dealer, player);
        }
    }

    //view에서 계산을 하는게 맞을까?
    private void printDealerWinOrLose(final ParticipantDto dealer, final List<ParticipantDto> players) {
        final int dealerScore = dealer.getCardDto().getScore();
        final List<Integer> playerScores = getPlayerScores(players);
        final int win = getWinCount(dealerScore, playerScores);
        final int draw = getDrawCount(dealerScore, playerScores);
        final int lose = getLoseCount(dealerScore, playerScores);
        System.out.println(format("{0}: {1}승 {2}무 {3}패", dealer.getName(), win, draw, lose));
    }

    private static List<Integer> getPlayerScores(final List<ParticipantDto> players) {
        return players.stream()
                .map(ParticipantDto::getCardDto)
                .map(CardDto::getScore)
                .collect(Collectors.toList());
    }

    private int getWinCount(final int dealerScore, final List<Integer> playerScores) {
        return (int) playerScores.stream()
                .filter(score -> dealerScore > score)
                .count();
    }

    private int getDrawCount(final int dealerScore, final List<Integer> playerScores) {
        return (int) playerScores.stream()
                .filter(score -> dealerScore == score)
                .count();
    }

    private int getLoseCount(final int dealerScore, final List<Integer> playerScores) {
        return (int) playerScores.stream()
                .filter(score -> dealerScore < score)
                .count();
    }

    private void printPlayerWinOrLose(final ParticipantDto dealer, final ParticipantDto player) {
        final int dealerScore = dealer.getCardDto().getScore();
        final int playerScore = player.getCardDto().getScore();
        final String result = getPlayerResult(dealerScore, playerScore);
        System.out.println(format("{0}: {1}", player.getName(), result));
    }

    //view 에서 계산을 하는게 맞을까?
    //TODO: view 에서 진행한다면 터졌을 때 승패 여부도 여기서 해야함
    private String getPlayerResult(final int dealerScore, final int playerScore) {
        if (playerScore > dealerScore) {
            return "승";
        }
        if (playerScore < dealerScore) {
            return "패";
        }
        return "무";
    }
}
