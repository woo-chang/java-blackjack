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

    //Input View 에서는 String 넘겨줘야할까? 아니면 파싱한걸 넘겨줘도 될까?
    //Argument Resolver 같은 경우 String 입력을 받아서 알맞은 파라미터로 변경하는데 그것과 같은 역할로 봐도 괜찮을까?
    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return parser.parseNames(input, NAME_DELIMITER);
    }

    //Input View 에서 HIT, STAND 알아도 되는걸까?
    public String readReceiveMore(final Player player) {
        System.out.println(format("{0}는 한장의 카드를 더 받겠습니까?(예는 {1}, 아니오는 {2})",
                player.getName(), HIT.getOption(), STAND.getOption()));
        return scanner.nextLine();
    }
}
