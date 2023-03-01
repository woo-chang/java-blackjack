package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        final var inputView = new InputView();
        final var outputView = new OutputView();

        final var blackJackController = new BlackJackController(inputView, outputView);
        blackJackController.run();
    }
}
