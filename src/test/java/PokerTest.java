import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void should_return_playerA_lose_when_playerA_vs_playerB_given_playerA_2H_3D_5S_8D_9C_playerB_2H_3D_5S_KD_9C() {
        // given
        Player playerA = new Player("playerA",
                Arrays.asList(new Poker("2", "H"), new Poker("3", "D"), new Poker("5", "S"), new Poker("8", "D"), new Poker("9", "C")));
        Player playerB = new Player("playerB",
                Arrays.asList(new Poker("2", "H"), new Poker("3", "D"), new Poker("5", "S"), new Poker("K", "D"), new Poker("9", "C")));

        // when
        String res = playerA.vs(playerB);

        // then
        assertEquals("playerA Lose", res);
    }

}