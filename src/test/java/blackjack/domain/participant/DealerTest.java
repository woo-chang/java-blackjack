package blackjack.domain.participant;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.FIVE;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.SEVEN;
import static blackjack.domain.card.Number.SIX;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.participant.Result.DRAW;
import static blackjack.domain.participant.Result.LOSE;
import static blackjack.domain.participant.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @Nested
    class isDrawable_메서드는 {

        @Test
        void 카드가_2장_이하이고_점수가_16점_이하라면_true_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(SIX, HEART));
            //16점

            assertThat(dealer.isDrawable()).isTrue();
        }

        @Test
        void 카드가_2장_이하이고_점수가_16점_초과라면_false_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(SEVEN, HEART));
            //17점

            assertThat(dealer.isDrawable()).isFalse();
        }

        @Test
        void 카드가_2장_초과라면_false_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(TWO, CLOVER));
            dealer.drawCard(new Card(SIX, HEART));
            dealer.drawCard(new Card(SEVEN, DIAMOND));
            //15점

            assertThat(dealer.isDrawable()).isFalse();
        }
    }

    @Nested
    class drawCard_메서드는 {

        @Test
        void 카드를_받을_수_없는_상태라면_예외를_던진다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(ACE, HEART));
            //21점

            assertThatThrownBy(() -> dealer.drawCard(new Card(TWO, DIAMOND))).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 카드를_받을_수_있는_상태라면_카드를_받는다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(SIX, HEART));
            //17점

            dealer.drawCard(new Card(TWO, DIAMOND));
            //19점

            assertThat(dealer.isDrawable()).isFalse();
        }
    }

    @Test
    void 점수를_확인한다() {
        final Dealer dealer = new Dealer();
        dealer.drawCard(new Card(TWO, CLOVER));
        dealer.drawCard(new Card(SIX, HEART));
        dealer.drawCard(new Card(SEVEN, DIAMOND));
        //15점

        assertThat(dealer.getScore()).isEqualTo(15);
    }

    @Test
    void 딜러는_딜러이다() {
        final Dealer dealer = new Dealer();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    void 딜러의_이름은_딜러이다() {
        final Dealer dealer = new Dealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 딜러가_카드를_뽑을_수_있는_최대_점수는_16점이다() {
        final Dealer dealer = new Dealer();

        assertThat(dealer.getMaximumDrawableScore()).isEqualTo(16);
    }

    @Nested
    class showResult_메서드는 {

        @Nested
        class 플레이어_점수가_블랙잭_점수를_초과하면 {

            @Test
            void LOSE_반환한다() {
                final Dealer dealer = new Dealer();

                assertThat(dealer.showResult(22)).isEqualTo(LOSE);
            }
        }

        @Nested
        class 딜러_점수가_블랙잭_점수를_초과하면 {

            @Test
            void 플레이어_점수가_블랙잭_점수_이하라면_WIN_반환한다() {
                final Dealer dealer = new Dealer();

                assertThat(dealer.showResult(21)).isEqualTo(WIN);
            }
        }

        @Nested
        class 딜러와_플레이어_점수_모두_블랙잭_점수_이하라면 {

            @Test
            void 딜러_점수가_플레이어_점수보다_낮으면_WIN_반환한다() {
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(ACE, HEART));
                dealer.drawCard(new Card(FIVE, DIAMOND));
                //16점

                assertThat(dealer.showResult(17)).isEqualTo(WIN);
            }

            @Test
            void 딜러_점수가_플레이어_점수보다_높으면_LOSE_반환한다() {
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(ACE, HEART));
                dealer.drawCard(new Card(FIVE, DIAMOND));
                //16점

                assertThat(dealer.showResult(15)).isEqualTo(LOSE);
            }

            @Test
            void 점수_같으면_DRAW_반환한다() {
                final Dealer dealer = new Dealer();
                dealer.drawCard(new Card(ACE, HEART));
                dealer.drawCard(new Card(FIVE, DIAMOND));
                //16점

                assertThat(dealer.showResult(16)).isEqualTo(DRAW);
            }
        }
    }

    @Test
    void 마지막_카드는_숨겨진_카드목록을_확인한다() {
        final Dealer dealer = new Dealer();
        dealer.drawCard(new Card(ACE, HEART));
        dealer.drawCard(new Card(FIVE, DIAMOND));

        final List<Card> hiddenCards = dealer.getHiddenCards();

        assertThat(hiddenCards).hasSize(1);
    }
}
