package blackjack.domain;

import blackjack.domain.participant.Player;
import java.util.Map;

//사실 참가자 정보를 전달할 때 점수 정보도 같이 계산되어야 하는거 아닌가?
public class Result {

    private final int dealerScore;
    private final Map<Player, Integer> playerScore;

    public Result(final int dealerScore, final Map<Player, Integer> playerScore) {
        this.dealerScore = dealerScore;
        this.playerScore = playerScore;
    }

    public int getDealerWinCount() {
        int count = 0;
        for (final Integer score : playerScore.values()) {
            if (dealerScore > score) {
                count++;
            }
        }
        return count;
    }

    public int getDealerDrawCount() {
        int count = 0;
        for (final Integer score : playerScore.values()) {
            if (dealerScore == score) {
                count++;
            }
        }
        return count;
    }

    public int getDealerLoseCount() {
        int count = 0;
        for (final Integer score : playerScore.values()) {
            if (dealerScore < score) {
                count++;
            }
        }
        return count;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerScore(final Player player) {
        return playerScore.get(player);
    }
}
