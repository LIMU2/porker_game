import org.junit.Test;

import static org.junit.Assert.*;

public class PokerTest {
    @Test
    public void should_return_1_when_compare_single_poker_given_porkerA_4H_porkerB_3H() {
        // given
        Poker pokerA = new Poker("5", "H");
        Poker pokerB = new Poker("3", "S");

        // when
        int res = pokerA.compareToSinglePoker(pokerB);

        // then
        assertEquals(1, res);
    }
}