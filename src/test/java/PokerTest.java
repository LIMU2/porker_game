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

    @Test
    public void should_return_playerA_win_when_playerA_vs_playerB_given_playerA_2H_2C_3H_4H_5H_playerB_2S_7D_3H_4H_5H() {
        // given
        Player playerA = new Player("playerA",
                Arrays.asList(new Poker("2", "H"), new Poker("2", "C"), new Poker("3", "H"), new Poker("4", "H"), new Poker("5", "H")));
        Player playerB = new Player("playerB",
                Arrays.asList(new Poker("2", "S"), new Poker("7", "D"), new Poker("3", "H"), new Poker("4", "H"), new Poker("5", "H")));

        // when
        String res = playerA.vs(playerB);

        // then
        assertEquals("playerA Win", res);
    }

    @Test
    public void should_return_playerA_lose_when_playerA_vs_playerB_given_playerA_2H_2C_4H_5H_6H_playerB_3S_3D_4H_5H_6H() {
        // given
        Player playerA = new Player("playerA",
                Arrays.asList(new Poker("2", "H"), new Poker("2", "C"), new Poker("4", "H"), new Poker("5", "H"), new Poker("6", "H")));
        Player playerB = new Player("playerB",
                Arrays.asList(new Poker("3", "S"), new Poker("3", "D"), new Poker("4", "H"), new Poker("5", "H"), new Poker("6", "H")));

        // when
        String res = playerA.vs(playerB);

        // then
        assertEquals("playerA Lose", res);
    }

    @Test
    public void should_return_playerA_win_when_playerA_vs_playerB_given_playerA_3H_3D_5S_9C_5D_playerB_3H_3D_5S_9C_KD() {
        // given
        Player playerA = new Player("playerA",
                Arrays.asList(new Poker("3", "H"), new Poker("3", "D"), new Poker("5", "S"), new Poker("9", "C"), new Poker("5", "D")));
        Player playerB = new Player("playerB",
                Arrays.asList(new Poker("3", "H"), new Poker("3", "D"), new Poker("5", "S"), new Poker("9", "C"), new Poker("K", "D")));

        // when
        String res = playerA.vs(playerB);

        // then
        assertEquals("playerA Win", res);
    }

    @Test
    public void should_return_playerA_lose_when_playerA_vs_playerB_given_playerA_3H_3D_5S_9C_5D_playerB_3H_3D_5S_9C_3D() {
        // given
        Player playerA = new Player("playerA",
                Arrays.asList(new Poker("3", "H"), new Poker("3", "D"), new Poker("5", "S"), new Poker("9", "C"), new Poker("5", "D")));
        Player playerB = new Player("playerB",
                Arrays.asList(new Poker("3", "H"), new Poker("3", "D"), new Poker("5", "S"), new Poker("9", "C"), new Poker("3", "D")));

        // when
        String res = playerA.vs(playerB);

        // then
        assertEquals("playerA Lose", res);
    }
}