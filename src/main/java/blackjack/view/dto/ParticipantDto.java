package blackjack.view.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Participant;

public class ParticipantDto {

    private final String name;
    private final CardDto cardDto;

    private ParticipantDto(final String name, final CardDto cardDto) {
        this.name = name;
        this.cardDto = cardDto;
    }

    public static ParticipantDto from(final Participant participant) {
        final String name = participant.getName();
        final Cards cards = participant.getCards();
        return new ParticipantDto(name, CardDto.from(cards));
    }

    public String getName() {
        return name;
    }

    public CardDto getCardDto() {
        return cardDto;
    }
}
