package blackjack.domain.participant;

import static blackjack.domain.participant.Participant.DEALER_NAME;

public class Name {

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (value.length() > 10) {
            throw new IllegalArgumentException();
        }
        if (value.equals(DEALER_NAME)) {
            throw new IllegalArgumentException();
        }
    }

    public String getValue() {
        return value;
    }
}
