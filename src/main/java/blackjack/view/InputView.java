package blackjack.view;

import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return parseNames(input);
    }

    //파싱하는 객체는 별도로 분리하는게 나을까?
    private List<String> parseNames(final String input) {
        return Arrays.stream(input.split(","))
                .collect(Collectors.toList());
    }

    public String readReceiveMore(final Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
