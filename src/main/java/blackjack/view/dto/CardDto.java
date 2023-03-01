package blackjack.view.dto;

import blackjack.domain.card.Cards;
import java.util.List;
import java.util.stream.Collectors;

public class CardDto {

    //List<Card>를 넘겨주는게 맞을까?
    //DTO가 필요한 정보로 가공해서 넘겨주는 역할인데 List<Card>를 넘겨주면 뷰에서 다시 가공해야하는 것이 아닌가?
    private final int score;
    private final List<String> cardInfos;

    private CardDto(final int score, final List<String> cardInfos) {
        this.score = score;
        this.cardInfos = cardInfos;
    }

    public static CardDto from(final Cards cards) {
        final List<String> cardInfos = cards.getCards().stream()
                .map(card -> card.getNumber() + card.getSuitName())
                .collect(Collectors.toList());
        return new CardDto(cards.getScore(), cardInfos);
    }

    public int getScore() {
        return score;
    }

    public List<String> getCardInfos() {
        return cardInfos;
    }
}
