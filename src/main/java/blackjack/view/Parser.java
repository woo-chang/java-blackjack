package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public List<String> parseNames(final String value, final String delimieter) {
        return Arrays.stream(value.split(delimieter))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
