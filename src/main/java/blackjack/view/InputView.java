package blackjack.view;

import static blackjack.domain.card.DrawOption.HIT;
import static blackjack.domain.card.DrawOption.STAND;
import static java.text.MessageFormat.format;

import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);
    private final Parser parser = new Parser();
    private final String NAME_DELIMITER = ",";

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return parser.parseNames(input, NAME_DELIMITER);
    }

    public String readReceiveMore(final Player player) {
        System.out.println(format("{0}는 한장의 카드를 더 받겠습니까?(예는 {1}, 아니오는 {2})",
                player.getName(), HIT.getOption(), STAND.getOption()));
        return scanner.nextLine();
    }
}
