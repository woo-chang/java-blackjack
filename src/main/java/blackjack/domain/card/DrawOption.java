package blackjack.domain.card;

import java.util.Arrays;

public enum DrawOption {

    HIT("y"),
    STAND("n");

    private final String option;

    DrawOption(final String option) {
        this.option = option;
    }

    public static DrawOption from(final String option) {
        return Arrays.stream(DrawOption.values())
                .filter(drawOption -> option.equals(drawOption.getOption()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String getOption() {
        return option;
    }
}
